package testjsonformat;

public class TestJsonFormat {

    public static void main(String[] args) {
        // jsonstr ---->json格式化输出
        String jsonStr1 = "{\"name\":\"SO JSON在线\",\"url\":\"https://www.sojson.com\",\"address\":{\"city\":\"北京\",\"country\":\"中国\"},\"domain_list\":[{\"name\":\"ICP备案查询\",\"url\":\"https://icp.sojson.com\"},{\"name\":\"JSON在线解析\",\"url\":\"https://www.sojson.com\"},{\"name\":\"房贷计算器\",\"url\":\"https://fang.sojson.com\"}]}";
        System.out.println(TestJsonFormat.handleJson(jsonStr1));

        System.out.println("===============");
        String jsonStr2 = "{\"name\":\"SO JSON在线\",\"url\":\"https://www.sojson.com\",\"address\":{\"city\":\"北京\",\"country\":\"中国\"}}";
        System.out.println(TestJsonFormat.handleJson(jsonStr2));

    }

    public static String handleJson(String jsonStr) {
        StringBuffer jsonForMatStr = new StringBuffer();
        char charArrays[] = jsonStr.toCharArray();
        int level = 0;

        //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
        for (char key : charArrays) {
            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getTabStr(level));
            }

            if (String.valueOf(key).equals("{") || String.valueOf(key).equals("[")) {
                jsonForMatStr.append(key + "\n");
                level++;
            } else if (String.valueOf(key).equals(",")) {
                jsonForMatStr.append(key + "\n");
            } else if (String.valueOf(key).equals("]") || String.valueOf(key).equals("}")) {
                jsonForMatStr.append("\n");
                level--;
                jsonForMatStr.append(getTabStr(level));
                jsonForMatStr.append(key);
            } else {
                jsonForMatStr.append(key);
            }
        }

        return jsonForMatStr.toString();
    }


    private static String getTabStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int i = 0; i < level; i++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}
