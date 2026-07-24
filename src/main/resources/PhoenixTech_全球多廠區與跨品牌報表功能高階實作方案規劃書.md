# PhoenixTech 全球多廠區與跨品牌報表功能高階實作方案規劃書
*(High-Level Global Multi-Site & Cross-Brand Reports Implementation Plan)*

---

## 📑 目錄與章節索引 (Table of Contents)

* [一、 背景與系統安全原則](#一-背景與系統安全原則)
* [二、 需求一：深圳 URL 統一入口與站點無縫切換](#二-需求一深圳-url-統一入口與站點無縫切換)
  * [1. 現有架構與現況說明](#1-現有架構與現況說明)
    * [1.1 站點與伺服器拓撲](#11-站點與伺服器拓撲)
    * [1.2 高階主管跨區無縫跳轉設計](#12-高階主管跨區無縫跳轉設計)
  * [2. 實作規劃方案](#2-實作規劃方案)
    * [2.1 方案一：跨區域資料庫帳號與權限同步（⭐️ 推薦方案）](#方案一跨區域資料庫帳號與權限同步-推薦方案)
    * [2.2 方案二：各地區獨立手動維護](#方案二各地區獨立手動維護)
* [三、 需求二：通用報表增加 "All" 客戶選項](#三-需求二通用報表增加-all-客戶選項)
  * [1. 現有架構與現況說明](#1-現有架構與現況說明-1)
    * [1.1 報表分類與品牌適用性分析](#11-報表分類與品牌適用性分析)
    * [1.2 前端 UI 與報表服務配合](#12-前端-ui-與報表服務配合)
  * [2. 實作規劃方案](#2-實作規劃方案-1)
    * [2.1 方案一：後端權限攔截](#21-方案一後端權限攔截)
    * [2.2 方案二：前端必填約束 + 後端集合查詢（⭐️ 推薦方案）](#22-方案二前端必填約束--後端集合查詢-推薦方案)
* [四、 需求三：實現 "All" 客戶的權限控制功能](#四-需求三實現-all-客戶的權限控制功能)
  * [1. 現有架構與現況說明](#1-現有架構與現況說明-2)
    * [1.1 權限判定流](#11-權限判定流)
    * [1.2 報表端安全校驗與防禦機制](#12-報表端安全校驗與防禦機制)
  * [2. 實作方案](#2-實作方案)
    * [2.1 方案一：新建菜單與品牌關聯表](#方案一新建菜單與品牌關聯表)
    * [2.2 方案二：手動帳戶菜單權限維護（⭐️ 推薦方案）](#方案二手動帳戶菜單權限維護-推薦方案)
* [五、 附錄](#五-附錄)
  * [附錄 A：系統報表與品牌對照表 (55 個)](#附錄-a系統報表與品牌對照表-55-個)

---

## 一、 背景與系統安全原則

在製造執行（MES）與數據統計平台中，多租戶隔離與數據隱私是安全合規的根本。
* **物理與邏輯隔離原則**：為滿足 SOC 2 審計與 GDPR 等法規，B 客戶與 A 客戶的生產及維修數據在資料庫層面必須物理隔離。目前 PhoenixTech 採用「物理隔離（不同資料庫實體） + 租戶垂直隔離（Tenant_ID RLS 邏輯隔離）」的複合架構。
* **高階主管跨區需求**：在不打破底層資料庫物理隔離的前提下，平臺需為 L0/L1 級高階主管（如區域主管、決策看板使用者）建立統一入口，提供**跨廠區快速切換**與**通用報表跨品牌查詢**功能。

經評估，平臺對數據架構進行了重大調整，**放棄高成本的「數據中台（原 PPT 方案 1.x）」方案，改採「本地優化 + 統一跳轉（原 PPT 方案 2.1 + 2.2）」的組合策略**。
* **棄用方案 1（數據中台）的原因**：
  * **成本極高**：搭建大數據平台需採購至少 2 台高性能實體伺服器（硬體成本約 36 萬 RMB），或購買昂貴的軟體授權（約 18 萬 RMB/年），且數據 ETL 與權限整合的開發成本高昂。
  * **合規風險高**：跨國集中存儲數據會直接打破第一章的「物理隔離原則」，大幅增加歐盟 GDPR、中國 PIPL 等跨境監管的合規風險。
* **採用方案 2.1 + 2.2 組合的優勢**：
  * **零硬體成本**：直接利用各廠區現有的伺服器與資料庫 Schema 進行就地優化，免去平台購置與軟體授權費。
  * **高體驗與高可用**：
    * **2.1（統一入口）讓高階主管在正常網路下**一站式單點登入**。**
    * **2.2（獨立自登錄）** 作為備用方案。當網路波動或統一入口不可用時，各地區（中國、印度、北美）支援獨立登入並查詢當前租戶的 "ALL" 客戶數據，確保業務不中斷（High Availability）。

---

## 二、 需求一：深圳 URL 統一入口與站點無縫切換
*(Unified Global Entrance & Seamless Site Switching)*

### 1. 現有架構與現況說明

#### 1.1 站點與伺服器拓撲
全球目前有 3 個獨立部署的實體站點（對應 3 個獨立的門戶 URL），各自擁有獨立的伺服器、應用服務與資料庫：
1. **深圳 URL (中國門戶)**：覆蓋中國區的所有廠區租戶，包括：**福田、昆山、廈門、上海、清湖**。
2. **印度 URL (印度門戶)**：服務於**印度**廠區租戶與當地資料庫。
3. **北美 URL (休斯頓門戶)**：服務於**休斯頓**廠區租戶與當地資料庫。

#### 1.2 高階主管跨區無縫跳轉需求
主管帳號在中國區資料庫建檔，統一登入「深圳 URL」門戶。切換廠區（至印度或北美）時，前端需在同一個瀏覽器分頁（Same Tab）中無縫跳轉，禁止彈出新視窗或要求使用者重新登入。

### 2. 實作規劃方案

為維持跨區域認證數據與菜單列表的一致性，我們規劃了以下兩套實作方案：

#### 方案一：跨區域資料庫帳號與權限同步（⭐️ 推薦方案）
* **實作機制**：
  * **資料庫多向增量同步**：三地（深圳、印度、北美）的 MySQL 資料庫是對等架構。當任何一處變更系統配置時，透過 MySQL Binlog 在背景將增量更新同步至另外兩處，確保三地帳號、角色與權限數據一致。
  * **僅同步 MySQL 系統配置庫**：此同步機制**僅同步**儲存系統配置、OAuth2 權限與菜單的 MySQL 資料庫（涉及 `SysUser`、`SysRole`、`SysMenu`、`SysUserRole`、`SysRoleMenu` 等表）。**所有業務相關的 SQL Server 資料庫（BLL 資料庫）保持本地物理隔離，不進行跨區同步**，確保客戶生產數據的絕對隱私與安全。
  * **無感認證與菜單同步**：高階主管在深圳入口申請 SSO Ticket 並跳轉時，目標站點的 Gateway 及 `oauth2-admin` 可直接在本地對等的 MySQL 中完成校驗，確保前端顯示的菜單列表完全一致。
  * **配套前端與網關跳轉機制**：在 MySQL 帳號與權限同步的基礎上，我們規劃了以下兩種前端與網關跳轉機制以實現無縫跨區切換：
    * **方式 A：同分頁重定向與 SSO 憑證傳遞（優先推薦）**
      1. 使用者在深圳門戶點擊「切換廠區」選單，選擇「休斯頓」。
      2. 前端發起請求，經 Gateway 轉發至平台 `oauth2-admin` 認證中心，申請一次性加密的 SSO 憑證（SSO Ticket）。
      3. 前端在同分頁（Same Tab）重定向至北美 URL，並在參數中攜帶此憑證：`https://houston.phoenixtech.com/login?sso_ticket=XXXXX`。
      4. 北美 Gateway 攔截請求，向 `oauth2-admin` 發起 Feign 呼叫驗證憑證。
      5. 驗證通過後，北美站點自動寫入會話（Session/Cookie），完成授權登入並載入頁面。
    * **方式 B：微前端 IFrame 整合載入**
      1. 深圳門戶作為主站（Shell）。
      2. 切換廠區時，主站透過 `iframe` 容器動態載入目標站點頁面。
      3. 憑證與 `tenantId` 透過 `postMessage` 安全傳遞給 `iframe` 頁面，自動完成身分認證。
* **技術風險與防範機制**：
  1. **跨國網路延遲與抖動**：異步複製可能因網路閃斷、延遲導致數據短暫不一致。
     * *防範機制*：推薦使用 UUID 或雪花 ID (Snowflake ID) 作為用戶與權限主鍵，並在跳轉認證時加入重試與快取重讀等緩衝降級機制。
  2. **數據隱私合規限制**：跨國同步 PII (個人隱私數據) 可能違反當地的數據合規要求（如 GDPR、中國 PIPL）。
     * *防範機制*：同步時**按欄位過濾**，僅同步去隱私化的安全雜湊 Token、加密密碼與 ID，過濾真實姓名、信箱、手機等敏感欄位。

#### 方案二：各地區獨立手動維護
* **實作機制**：
  * 三地資料庫不進行任何數據同步。
  * 主管有跨區需求時，管理員需手動登入三個站點後台，逐一為該帳號配置廠區權限、品牌權限、角色與菜單可見項。
* **成本與風險評估**：
  * **零開發成本**：無合規風險，不需改動任何資料表結構，上線最快。
  * **高運維負擔**：主管的角色或菜單變更時需手動點擊修改三次，極易因人工漏配造成權限不一致或越權漏洞。

#### ⚖️ 決策建議
* **若本期優先考慮快速上線**，可先採用方案二（手動配置）。但考量到主管級帳號及菜單**變動的頻率**，長期而言方案一（三地對等 MySQL 系統庫增量同步）能顯著降低人工維護成本與資安風險，是更為穩健的架構選擇。**

---

## 三、 需求二：通用報表增加 "All" 客戶選項
*(Generic Reports 'All Customers' Dynamic Selection)*

### 1. 現有架構與現況說明

#### 1.1 報表分類與品牌適用性分析
根據 **[附錄 A 適用性對照表](#附錄-aphoenixtech-系統報表清單及-brand-適用性對照表-完整-55-筆)**，目前系統共有 **55 個** 報表。依據其業務屬性、適用品牌與底層資料表結構，這 55 個報表被劃分為以下三類：

##### **A 類：通用報表（共 35 個，適用 "ALL" 查詢）**
* **業務特點**：多個客戶品牌（DELL、HP、LENOVO）共用相同的統計維度、SQL 結構與欄位規格。
* **"ALL" 選項適用性**：✅ 適用。前端下拉選單中應動態注入 `"ALL"` 選項。選中 `"ALL"` 時，後端將執行多品牌合併統計。
* **報表範例**：`產品基本資料報表` (工程)、`WIP Age` (生產維修)、`Yield Rate & Tracking` (生產維修)、`入庫/出庫/庫存查詢` (倉庫) 等。
* **完整清單**：請參閱 **[附錄 A 適用性對照表](#附錄-aphoenixtech-系統報表清單及-brand-適用性對照表-完整-55-筆)** 中標註為 **「A 類 (通用)」** 的 35 個報表項目。

##### **B 類：專屬報表（共 17 個，不適用 "ALL" 查詢）**
* **業務特點**：專為單一客戶客製化的報表，其資料結構、工站指標與顯示規格專屬於單一品牌，不支援跨品牌合併統計。
* **"ALL" 選項適用性**：❌ 不適用。前端下拉選單強制不顯示 `"ALL"` 選項，且預設直接選中該報表綁定的物理品牌。
* **報表範例**：`Return Waterfull報表` (PM - 限 DELL)、`DELL CID報表` (交管 - 限 DELL)、`Lenovo CID報表` (交管 - 限 LENOVO)、`HP CID SMS APJ` (交管 - 限 HP) 等。
* **完整清單**：請參閱 **[附錄 A 適用性對照表](#附錄-aphoenixtech-系統報表清單及-brand-適用性對照表-完整-55-筆)** 中標註為 **「B 類 (專屬)」** 的 17 個報表項目。

##### **C 類：雙品牌報表（共 3 個，條件式適用 "ALL" 查詢）**
* **業務特點**：特定的雙品牌（如 HP 與 LENOVO）共用報表。
* **"ALL" 選項適用性**：⚠️ 條件式適用。當授權主管選中 `"ALL"` 時，代表同時查詢這兩個品牌的數據（排除 DELL）。
* **涉及報表項目**：
  1. `HP&Lenovo repair rawdata` (交管) —— *選中 "ALL" 時同時查詢 HP 與 LENOVO*
  2. `HL檢測結果報表` (檢測) —— *選中 "ALL" 時同時查詢 HP 與 LENOVO*
  3. `HP LENOVO DOA 報表` (交管) —— *選中 "ALL" 時同時查詢 HP 與 LENOVO*

---

#### 1.2 前端 UI 與報表服務配合
* **前端 UI 職責**：
  * 品牌清單介面維持不變，僅返回該廠區真實存在的品牌列表（如 `["DELL", "HP", "LENOVO"]`），不產生虛擬的 `"ALL"` 客戶髒數據。
  * 針對 **A 類通用報表**，前端取得清單後，**自動在下拉選單最上方注入一個 `"ALL"` 選項**；針對 **B 類專屬報表**，前端則不注入。
* **報表服務（`mes_report`）職責**：
  * 修改通用報表對應的 MyBatis Mapper XML。
  * 當傳入 `brand == 'ALL'` 時，動態 SQL 將不對 `rd.BRAND` 欄位進行過濾，從而查出所有品牌進行合併統計。
  ```xml
  <select id="getGeneralRepairReport" resultType="...">
      SELECT * FROM repair_detail rd
      WHERE rd.TENANT_ID = #{tenantId}
      <!-- 當 brand 為 'ALL' 時，不對 BRAND 欄位過濾，查出所有品牌數據 -->
      <if test="brand != null and brand != '' and brand != 'ALL'">
          AND rd.BRAND = #{brand}
      </if>
  </select>
  ```

### 2. 實作規劃方案

當前系統的運作方式是：所有報表 SQL 已內建品牌過濾條件（如 `AND rd.BRAND = #{brand}`）。若前端未傳遞 brand 參數（為空或 null），後端 SQL 預設會查出所有品牌的數據。

雖然「留空 brand 即可查出全品牌」在技術上最簡單，但這會帶來嚴重的安全隱患。必須**禁止**無 `PERM_ALL_BRANDS` 權限的使用者透過留空 brand 參數繞過權限，查詢全品牌數據。

為解決此安全與權限控制問題，我們評估了以下兩種實作路徑：

#### 2.1 方案一：後端權限攔截
* **實作機制**：在後端報表 Service 接口或全域 AOP 攔截器中檢查所有請求。若 brand 參數為空，後端強制校驗使用者是否擁有 `PERM_ALL_BRANDS` 權限；若無，則拒絕請求並拋出異常。
* **開發與維護評估**：
  * **開發成本高**：報表專案包含數十個 Controller 與 MyBatis XML。若在後端逐一適配、修改 DTO 參數並編寫校驗邏輯，開發與聯調週期極長。
  * **高迴歸風險**：逐個報表手動添加校驗，極易因疏漏造成安全漏洞，且大量改動核心業務代碼會增加系統不穩定性。
  * **維護難度大**：未來新增報表時，開發人員必須手動添加此校驗，不符合「安全預設（Secure by Default）」原則。

#### 2.2 方案二：前端必填約束 + 後端集合查詢（⭐️ 推薦方案）
為兼顧開發效率與系統穩定性，我們推薦此組合方案：

1. **前端 UI 邊界約束**：
   * 在通用報表頁面中，將「品牌（Brand）篩選」設為**必填項**，不允許置空提交。
   * **無 "ALL" 權限的使用者**：選單中**不提供 `"ALL"` 選項**，且預設選中該使用者擁有的單一品牌（如 `"DELL"`）。由於前端強制傳入單一品牌且不可留空，使用者在 UI 上無法發起跨品牌查詢。
   * **有 "ALL" 權限的使用者**：選單中**提供 `"ALL"` 選項**，且頁面載入時**預設選中 `"ALL"`**。

2. **後端 SQL 調整（IN 集合查詢）**：
   * SQL 放棄「不傳 brand 即全放行」的邏輯，而是改為**集合過濾（IN 查詢）**：
     * 將原有的 `AND rd.BRAND = #{brand}` 調整為 `AND rd.BRAND IN ( <foreach collection="brands" item="b" separator=",">#{b}</foreach> )`。
   * **業務邏輯處理**：
     * 當使用者選中單一品牌（如 `"DELL"`）時，後端裝為單元素集合 `["DELL"]` 傳入。
     * 當選中 `"ALL"` 時，後端會將 `"ALL"` 轉換為該使用者**所有被授權的品牌集合**（如 `["DELL", "HP", "LENOVO"]`）傳入。
     * 底層 SQL 始終執行有明確安全邊界的 `BRAND IN (...)` 查詢。

* **方案優勢**：
  * **資安防禦佳**：SQL 層面不再有「不傳參數就全量返回」的情況，徹底杜絕惡意置空參數進行全表掃描的越權風險。
  * **開發難度低、上線快**：前端做好必填校驗與選單注入；後端僅需將原有的 String 參數升級為 List 集合，並在 XML 內使用 MyBatis 標準 `<foreach>` 即可，改動極小且安全。

---

## 四、 需求三：實現 "All" 客戶的權限控制功能
*(Authorization & RBAC/ABAC Security Design)*

### 1. 現有架構與現況說明

#### 1.1 權限判定流
報表服務透過權限過濾，確保 "All" 客戶查詢只開放給授權主管：

```
[前端請求 brand = 'ALL'] 
         │
         ▼
[SaContextHolder (com.foxconn.gsp.report.context) / OAuth2ContextHolder] ──► 讀取當前 Session 的權限列表
         │
         ▼
[權限校驗] 是否包含 `PERM_ALL_BRANDS`？
   ├───► 是 (YES)：放行，執行跨品牌動態 SQL 查詢
   └───► 否 (NO) ：拒絕，拋出 `SaException("您無權查詢所有品牌的資料！")` 或自動降級為僅查詢名下授權的單一品牌
```

* **平台層與報表層的安全上下文對接設計**：
  * **報表服務端**：使用 `SaContextHolder` 與 `OAuth2ContextHolder` 解析請求中的品牌、用戶名及租戶 ID。
  * **平台門戶端**：安全上下文由 `AlpsHelper` 取得 `AlpsUserDetails` 後建立。其 `brands`（品牌陣列）及 `tenants`（租戶列表）提供了完整的授權數據，與下游報表端的認證機制完美對接。

#### 1.2 報表端安全校驗與防禦機制
* **核心代碼路徑**：
  * `ProdMasterReportServiceImpl`（以及其他 21 個通用報表的 Service 實作，如 `RepairReportServiceImpl` 等）。
* **防越權校驗（Anti-Spoofing）**：
  * 即便前端已做控制，若惡意使用者用 Postman 等工具直接發送 `brand = 'ALL'` 請求，後端仍必須進行權限校驗。
  * 在報表 Service 中，加入防禦性檢驗代碼：
  ```java
  if ("ALL".equalsIgnoreCase(dto.getBrand())) {
      // 驗證當前用戶是否具備 PERM_ALL_BRANDS 權限
      boolean hasAllBrandPermission = checkUserPermission("PERM_ALL_BRANDS");
      if (!hasAllBrandPermission) {
          throw new SaException("Security Error: Unauthorized access to ALL brand data.");
      }
  }
  ```

### 2. 實作方案

平臺在 **ABAC 廠區與品牌維度** 上（如清湖廠區_HP、福田廠區_DELL）已支持廠區與品牌的交叉授權。若勾選某廠區下的所有品牌，本質上等同於擁有了該廠區的「ALL」權限。

**優化方案**：我們將在**權限配置 UI** 中加入「全選 (Select All)」功能，便於管理員一鍵快速為使用者配置某廠區的全部品牌權限，這在數據隔離上已能完全滿足需求。

然而，目前系統存在一個**資安漏洞**：**「菜單入口（報表選單）未與品牌權限邏輯關聯。」**
導致無 HP 權限的使用者仍在側邊欄看到 HP 專屬報表入口，雖然點擊後查不出數據，但在使用者體驗與 SOC 2 合規審計上，暴露未授權的入口仍是不合規的。

同時，在設計選單過濾方案時，我們有一個**核心技術限制**：平台**採用**動態虛擬角色覆蓋（Dynamic Virtual Role Overwrite）架構，這使得傳統的靜態 RBAC 角色選單過濾方案不可行。

* **「動態虛擬角色覆蓋」機制原理**：
  * **運行時覆蓋**：當管理員為用戶手動配置了某個廠區下的所有品牌（等同於 ALL 品牌權限）時，`oauth2-admin` 模組在運行時（Runtime）會動態為該用戶計算並綁定一個**「虛擬角色 (Virtual Role)」**。
  * **覆蓋行為**：此動態虛擬角色會**完全覆蓋（Clobber）** 該用戶原先手動配置的所有靜態角色（包括自定義的 `ROLE_HP_REPORT_VIEWER` 等報表專屬角色）。
  * **技術結論**：由於虛擬角色的覆蓋特性，透過細分靜態角色、各自綁定不同品牌選單來實現選單動態過濾，在運行時都會因角色被覆蓋而失效。因此，靜態角色隔離方案不可行。

針對此漏洞，我們評估了以下解決方案：

#### 方案一：新建菜單與品牌關聯表
* **實作機制**：
  * 新建多對多關係表 `sys_menu_brand`（關聯菜單 ID 與品牌）。
  * 載入菜單時執行關聯查詢，若用戶不擁有該菜單對應的品牌權限，則動態從列表中移除。
* **技術局限與壞味道**：
  * **破壞標準系統架構**：此補丁將**業務邏輯（品牌）侵入了基礎配置層（菜單管理）**。這會導致權限系統失去通用性，未來若有「按產品線」、「按客戶」等過濾菜單的需求，將面臨無盡的資料表修改。
  * **開發與維護成本極高**：涉及 Gateway 攔截器、菜單加載服務重寫以及前端適配，且每次新增報表都需手動註冊關係，引發 Regression 的風險很高。

#### 方案二：手動帳戶菜單權限維護（⭐️ 推薦方案）
* **實作機制**：
  * **零代碼與資料庫變動**：直接利用現有的帳號/角色菜單配置功能，對主管帳號進行手動維護。
  * **操作路徑**：管理員在配置權限時，直接在現有的選單分配 UI 中，**手動取消勾選該用戶不應看到的報表菜單項**（如取消勾選 HP 專屬報表）。由於未勾選，系統原生的菜單過濾機制會自動在側邊欄隱藏該入口。
* **方案優勢與可行性分析**：
  1. **零開發與零風險**：完全依靠現有平台成熟功能，安全性 100%，無任何系統防禦漏洞與 Regression 風險。
  2. **維護工作量完全可控（O(1) 工作量）**：
     * 全球需要配置跨品牌權限的高階主管與看板使用者**人數極少（僅限幾十位）**。
     * 這些主管帳號在完成首次選單配置後，業務分工與角色高度靜態，後續極少變動。
     * 因此，人工維護成本極低，性價比與安全性最高。

#### ⚖️ 決策建議
* **推薦「方案二（手動帳戶菜單權限維護）」**：這是目前最快、最安全、最合規且開發成本為零的解決方案。它不僅避開了平台「動態虛擬角色覆蓋」的技術限制，更免去了開發全新關係表帶來的系統架構腐化與潛在 Bug 風險。
* **反對「方案一（新建菜單與品牌關聯表）」**：避免將業務邏輯硬編碼進基礎權限管理。

---

## 五、 附錄

### 附錄 A：系統報表與品牌對照表 (55 個)

#### 📝 報表代碼查找指引
開發或維運團隊如需在 `mes_report` 代碼庫中查找最完整的報表定義、映射或更新清單，請參考以下 **三大核心源頭 (Source of Truth)**：
1. **API 介面控制器層 (`src/main/java/com/foxconn/gsp/report/business/mes/controller/`)**：
   * 所有報表的 HTTP Entrypoints（入口介面，含分頁查詢、Excel 導出等）均以獨立 Controller Class 定義於此目錄（目前共有 27 個控制器類，代表了系統所有可被前端調用的報表服務）。
2. **多語言國際化 properties 檔案 (`src/main/resources/message_zh_CN.properties` 與 `message_en_US.properties`)**：
   * 本專案統一使用 EasyExcel 導出報表。所有報表導出時的 **Excel 實體檔案名稱和主 Worksheets 名稱** 都統一以 `*_EXCELNAME` 或 `*_TITLE` 的 Key-Value 鍵值對配置在此目錄下，這代表了系統所有支持 Excel 導出的實體報表名稱。
3. **MyBatis XML 數據對接層 (`src/main/resources/mapper/`)**：
   * 每個報表的自定義 SQL、數據表關聯及字段過濾（包括 `RLS tenantId`、`brands` 集合等）均在 mapper 目錄的 XML 中定義，為報表底層數據的最終源頭。

---

以下數據完整轉換自 `系統報表清單.csv` 原始檔，並結合 `mes_report` 程式碼庫中的 **27 隻 Controller 控制器** 與 **多語言properties配置** 進行了精確的增補與標準化，包含適用廠區、客戶/品牌、報表類別及 "ALL" 篩選適用性（共 55 筆）：

| 編號 | 部門 | 報表名稱 | 適用廠區 | 適用客戶/品牌 | 報表類別 | ALL選項適用性 | 對應底層實作控制器 |
| :--- | :--- | :--- | :--- | :--- | :--- | :---: | :--- |
| 1 | PM | Return Waterfull報表 / WOPR主報表 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `CustomerReportController` / `getWopr` |
| 2 | 工程 | 产品基本资料报表 / 產品主數據報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ProdMasterReportController` / `PRODMASTER_EXCELNAME` |
| 3 | 工程 | 产品ECN升级报表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `CreateWoDetailReportController` / `ECNUPGRADE_EXCELNAME` |
| 4 | 生產維修 | 維修不良現象Top 10報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RepairStationCountReportController` / `REPAIR_STATION_COUNT_ITEM` |
| 5 | 生產維修 | 維修不良零件Top 10報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `TestFailDistributionReportController` / `TESTFAILDISTRIBUTION_EXCELNAME` |
| 6 | 生產維修 | WIP Age | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `WIPReportController` / `WIP_ITEM` |
| 7 | 生產維修 | Yield Rate& Tracking | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ProduceReportController` / `REPORT_SELECT_ERROR_CODE` |
| 8 | 生產維修 | WIP预警 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `WIPReportController` / `WIPEARLYWARNING_EXCELNAME` |
| 9 | 生產維修 | PPID Route Tracking | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RouteTrackingReportController` / `REPAIRTRANSFER_EXCELNAME` |
| 10 | 生產維修 | 過站記錄 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` |
| 11 | 交管 | DELL CID報表 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `CustomerReportController` / `DELLCID_EXCELNAME` |
| 12 | 交管 | Lenovo CID报表 | 昆山/上海 | LENOVO | B 類 (專屬) | ❌ | `RepairReportLenovoController` / `LENOVOCID_EXCELNAME` |
| 13 | 交管 | HP CID SMS APJ | 昆山 | HP | B 類 (專屬) | ❌ | `CustomerReportController` / `HPCIDSMSAPJ_EXCELNAME` |
| 14 | 交管 | HP CID NON SMS APJ | 昆山 | HP | B 類 (專屬) | ❌ | `CustomerReportController` / `HPNONAPJCID_EXCELNAME` |
| 15 | 交管 | 進口跟蹤表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 交管通用報表 |
| 16 | 交管 | 來貨錯料 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 交管通用報表 |
| 17 | 交管 | Onway/在途 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 交管通用報表 |
| 18 | 交管 | DELL repair cost | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | PM / 交管 DELL 專屬 |
| 19 | 交管 | DELL repair PPID | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | PM / 交管 DELL 專屬 |
| 20 | 交管 | KS Receive | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 交管通用報表 |
| 21 | 交管 | TAT未結案/在線Open明細 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | TAT明細報表 |
| 22 | 交管 | TAT已結案/結案明細 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | TAT明細報表 |
| 23 | 交管 | HP&Lenovo repair rawdata | 昆山/上海 | HP/LENOVO | 雙品牌過濾 | ✅ | `ReportController` / `REPAIRRESULT_EXCELNAME` |
| 24 | 品保 | VFIR | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `CustomerReportController` / `VIFR_EXCELNAME` |
| 25 | 品保 | RIO | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `CustomerReportController` / `RIO_ERROR_REPORT_TITLE` |
| 26 | 品保 | OBA rate%報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ObaTrendChartController` / `OBATRENDCHART_FILE_NAME` |
| 27 | 品保 | RR90 data報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RR90ReportController` / `REPEATRETURNTRENDCHART_FILE_NAME` |
| 28 | 品保 | RY(Repair yield)報表 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RepairYieldTrendChartController` / `REPAIRYIELDTRENDCHART_FILE_NAME` |
| 29 | 品保 | VID data報表 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `ReportController` / `VID_REPORT_EXCELNAME` |
| 30 | 品保 | CND&ECND比率統計 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `TestFailDistributionReportController` / `TESTFAILDISTRIBUTION_EXCELNAME` |
| 31 | 品保 | RIO异常處理 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | RIO異常處理 |
| 32 | 品保 | RIO异常查詢 | 昆山/廈門 | DELL | B 類 (專屬) | ❌ | `CustomerReportController` / `RIO_ERROR_REPORT_TITLE` |
| 33 | 品保 | LENOVO月維修數量報表 | 昆山/上海 | LENOVO | B 類 (專屬) | ❌ | `RepairReportLenovoController` / `LENOVOREPAIRREPORTMONTH_EXCELNAME` |
| 34 | 品保 | HP质量指标月度追踪报表 | 昆山 | HP | B 類 (專屬) | ❌ | `HpReportController` / `hpQualityMetricTrackerMonth` |
| 35 | 品保 | Lenovo GI_DATA | 昆山/上海 | LENOVO | B 類 (專屬) | ❌ | Lenovo GI_DATA 專屬 |
| 36 | 倉庫 | 入库查询 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 庫存與物流通用報表 |
| 37 | 倉庫 | 出库查询 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 庫存與物流通用報表 |
| 38 | 倉庫 | 库存查询 | 昆山/廈門/上海 | DELL/HP/LENOVO | A 類 (通用) | ✅ | 庫存與物流通用報表 |
| 39 | 檢測 | HL檢測結果報表 | 昆山/上海 | HP/LENOVO | 雙品牌過濾 | ✅ | 檢測雙品牌報表 |
| 40 | 檢測 | Lenovo DPK Report | 昆山/上海 | LENOVO | B 類 (專屬) | ❌ | `RepairReportLenovoController` / `LENOVODPK_EXCELNAME` |
| 41 | PM | 實際出貨報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ActualShipmentController` / `ACTUALSHIPMENT_EXCELNAME` |
| 42 | 生產維修 | BGA 報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `BGAReportController` / `BGA_ITEM` |
| 43 | 生產維修 | 開工單明細報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `CreateWoDetailReportController` / `CREATEWO_ITEM` |
| 44 | 交管 | EW XPPC Tracker 報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `EwXppcTrackerReportController` / `EW_XPPC_TRACKER_STATUS` |
| 45 | 交管 | HP LENOVO DOA 報表 | - | HP/LENOVO | 雙品牌過濾 | ✅ | `HPAndLenovoDoaReportController` / `HP_DOA_MONTH` |
| 46 | 檢測 | MAC 對接報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `MacDockingReportController` / `MAC_DOCKING_EXCELNAME` |
| 47 | 生產維修 | 維修包裝報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RepairPackingReportController` / `REPAIRPACKING_EXCELNAME` |
| 48 | 交管 | RMA 列表報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RmaListReportController` / `RMA_ITEM` |
| 49 | 生產 | 報廢過站數量報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `getScrapDetailReport` |
| 50 | 生產維修 | 維修測試報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `getRepairTestReport` |
| 51 | 生產 | D9 月度統計報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `getD9CountsByMonth` |
| 52 | 生產維修 | 維修流轉明細報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `REPAIRTRANSFER_EXCELNAME` |
| 53 | 生產維修 | 包裝明細報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `PACKING_DETAIL_EXCELNAME` |
| 54 | 生產維修 | 維修 KPI 報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `ReportController` / `REPAIR_KPI_EXCELNAME` |
| 55 | 生產維修 | 維修進度匯總報表 | - | DELL/HP/LENOVO | A 類 (通用) | ✅ | `RepairReportController` / `REPAIR_PROGRESS_SUMMARY_EXCELNAME` |
