# 重寫 `OdsRejectsServiceImpl.importRma` 方法實施方案 (修訂版)

## 1. 需求概述

本文檔旨在規劃 `com.foxconn.dbg.dp.prj.gssd.oms.business.originalreceipt.service.impl.OdsRejectsServiceImpl.java` 中 `importRma` 方法的重寫工作。

核心目標是實現對特定 Excel 文件 (`ods_rejects_rma_template.xlsx`) 的導入功能。該功能涉及數據解析、多重校驗、以及對 `ods_rejects` 和 `ods_rejects_extend` 兩個數據庫表的查詢與更新操作。

整個實現將參考現有代碼（如 `OdsRejectsServiceImpl` 自身）的風格，特別是關於 Excel 處理、校驗和錯誤反饋的模式。

## 2. 詳細實施步驟

### 步驟 0: 創建數據傳輸對象 (DTO)

為了方便地接收和處理 Excel 中的數據，需要創建一個專門的 DTO。

- **類名**: `OdsRejectsRmaImportDTO.java`
- **位置**: `com.foxconn.dbg.dp.prj.gssd.oms.business.originalreceipt.pojo.dto` 包下。
- **類成員**:
    ```java
    // 對應 Excel 表頭 "FlowTag"
    private String flowTag;

    // 對應 Excel 表頭 "eWayBill"
    private String eWayBill;

    // 對應 Excel 表頭 "DeliveryChallan"
    private String deliveryChallan;
    ```
- **註解**: 為了與項目現有 `ExcelUtils` 工具類兼容，可以在每個字段上使用 `@ExcelColumn` 註解，將其與 Excel 的列明確綁定。

### 步驟 1: 重寫 `importRma` 方法的核心邏輯

`importRma` 方法的簽名預計為 `public ImportResult importRma(MultipartFile file)`，它將包含以下子步驟。

#### 1.1. 讀取和解析 Excel

- **不使用 EasyExcel**。參考項目中 `OdsRejectsServiceImpl` 等現有實現，使用 `WorkbookFactory.create(file.getInputStream())` 創建 `Workbook` 對象。
- 採用項目中已有的 `ExcelUtils.read(...)` 方法，該方法能夠將 `Workbook` 解析為 `Map<String, List<Map<String, CellValue<Object>>>>` 結構。
- 將上述 Map 結構進一步轉換為 `List<OdsRejectsRmaImportDTO>` 對象列表。

#### 1.2. 執行初步數據校驗（行級別）

- 初始化一個空的錯誤列表：`List<ExcelValidationError> validationErrors = new ArrayList<>();`
- 遍歷 `List<OdsRejectsRmaImportDTO>`，對每個 DTO 對象執行以下校驗：
    1.  **過濾示例行**: 檢查 `flowTag` 字段。如果其值（忽略大小寫）包含 "sample" 字符串，則跳過該行，不進行任何校驗和處理。
    2.  **FlowTag 校驗**: 檢查 `flowTag` 字段是否為 `null` 或空字符串。如果是，則創建一個 `ExcelValidationError` 對象，記錄行號、列名和錯誤信息（例如 "FlowTag 不能為空"），並將其添加到 `validationErrors` 列表中。
    3.  **eWayBill / DeliveryChallan 校驗**: 檢查 `eWayBill` 和 `deliveryChallan` 兩個字段是否 **同時** 為 `null` 或空字符串。如果是，則創建一個 `ExcelValidationError` 對象，記錄錯誤信息（例如 "eWayBill 和 DeliveryChallan 不能同時為空"），並添加到 `validationErrors` 列表中。

#### 1.3. 執行業務邏輯校驗（數據庫級別）

- **批量查詢優化**:
    1.  從 `List<OdsRejectsRmaImportDTO>` 中提取所有有效（非 sample、非空）的 `flowTag`，存入一個 `List<String> flowTags`。
    2.  如果 `flowTags` 列表不為空，執行一次數據庫查詢：`SELECT id, flow_tag FROM ods_rejects WHERE flow_tag IN (...)`，傳入 `flowTags` 列表。將查詢結果存入 `Map<String, Long> flowTagIdMap`，其中 Key 是 `flow_tag`，Value 是 `id`。
    3.  從 `flowTagIdMap` 中提取所有的 `id`，存入 `List<Long> rejectIds`。
    4.  如果 `rejectIds` 列表不為空，執行第二次數據庫查詢：`SELECT id, rejects_id, arn_no FROM ods_rejects_extend WHERE rejects_id IN (...)`，傳入 `rejectIds` 列表。將結果存入 `Map<Long, OdsRejectsExtend> extendMap`，其中 Key 是 `rejects_id`。
- **遍歷校驗**:
    - 再次遍歷 `List<OdsRejectsRmaImportDTO>`（已過濾掉 sample 行）。
    - 對於每一行數據：
        1.  **校驗 FlowTag 是否存在**: 檢查當前行的 `flowTag` 是否存在於 `flowTagIdMap` 中。如果不存在，則創建 `ExcelValidationError`（錯誤信息如 "FlowTag [xxx] 在系統中不存在"）並添加到 `validationErrors` 列表。
        2.  **校驗 Extend 記錄是否存在**: 如果 `flowTag` 存在，從 `flowTagIdMap` 中獲取對應的 `rejectId`。然後檢查該 `rejectId` 是否存在於 `extendMap` 中。如果不存在，則創建 `ExcelValidationError`（錯誤信息如 "FlowTag [xxx] 對應的擴展信息不存在"）並添加到 `validationErrors` 列表。
        3.  **校驗 ARN_NO 狀態**: 如果 `extend` 記錄存在，從 `extendMap` 中獲取 `OdsRejectsExtend` 對象。檢查其 `ARN_NO` 字段是否不為 `null` 或空字符串。如果是，則表示該記錄已處理，創建 `ExcelValidationError`（錯誤信息如 "FlowTag [xxx] 對應的記錄已生成 ARN，不允許修改"）並添加到 `validationErrors` 列表。

#### 1.4. 集中處理所有校驗錯誤

- 在完成上述所有校驗步驟後，檢查 `validationErrors` 列表。
- **如果列表不為空**，則表示存在數據格式或業務邏輯問題。此時應立即停止後續流程。
- **操作**: 將包含所有錯誤信息的 `validationErrors` 列表封裝到 `ImportResult` 對象中，並 **返回該 ImportResult 對象**。**不要在後端拋出異常**。

#### 1.5. 執行數據更新

- **前提**: 只有在 `validationErrors` 列表仍為空時才執行此步驟。
- 創建一個待更新的列表：`List<OdsRejectsExtend> updateList = new ArrayList<>();`
- 遍歷 `List<OdsRejectsRmaImportDTO>`（已過濾掉 sample 行）。
- 對於每一行數據：
    1.  從 `flowTagIdMap` 獲取 `rejectId`。
    2.  從 `extendMap` 獲取對應的 `OdsRejectsExtend` 對象。
    3.  使用 DTO 中的數據更新該對象：
        ```java
        extendToUpdate.setEWayBill(dto.getEWayBill());
        extendToUpdate.setDeliveryChallan(dto.getDeliveryChallan());
        // 設置更新人和更新時間等審計字段
        ```
    4.  將更新後的 `extendToUpdate` 對象添加到 `updateList` 中。
- 調用 `odsRejectsExtendService.updateBatchById(updateList)` 或類似的批量更新方法，一次性將所有變更提交到數據庫。

#### 1.6. 返回最終結果

- 無論更新是否執行，方法最後都返回 `ImportResult` 對象。
- 如果 `validationErrors` 列表不為空，`ImportResult` 應包含這些錯誤信息。
- 如果所有操作成功，`ImportResult` 應表示成功狀態，且錯誤列表為空。

---

## 3. 開發約束

- **唯一修改點**: 本次需求的所有 Java 業務邏輯 **只能** 在 `com.foxconn.dbg.dp.prj.gssd.oms.business.originalreceipt.service.impl.OdsRejectsServiceImpl.java` 文件中的 `importRma` 方法內實現。
- **輔助方法**: 允許在 `OdsRejectsServiceImpl.java` 文件內部新增 `private` 的輔助方法，以供 `importRma` 方法調用，保持代碼整潔。
- **允許新增 DTO**: 允許為了此功能新增 DTO 文件，即步驟 0 中提到的 `OdsRejectsRmaImportDTO.java`。
- **嚴格禁止**:
    - **禁止修改任何其他已存在的文件**。
    - **禁止修改 `OdsRejectsServiceImpl.java` 文件中的任何其他方法**。

---
以上為修訂後的完整實施方案，已根據最新要求調整了錯誤處理機制和 Excel 解析策略。
