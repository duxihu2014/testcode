package testjsonformat;

public class Test2 {

    public static void main(String[] args) {

        String str = "{\"header\":{\"transSn\":\"e33128bb7622462ebfb2cbfcc46baa14\",\"dateTime\":\"20181002110000\",\"serviceCode\":\"********\",\"appId\":\"999999999999\",\"bizId\":\"000000\",\"version\":\"1.0\",\"resType\":\"A\",\"resCode\":\"SSS000\",\"resMsg\":\"交易成功(业务附加消息:*****)\"},\"resBody\":{\"operator\":\"lgh\",\"rowNumStart\":\"1\",\"pageRowNum\":\"100\",\"pageFlag\":\"0\",\"totalRowNum\":\"1\",\"orderFlag\":\"0\",\"orderField\":\"\",\"result\":[{\"flag\":\"1\",\"riskCode\":\"00567000\",\"riskName\":\"567*********\",\"prem\":\"520.00\",\"amntOrMult\":\"***\",\"amnt\":\"200000\",\"polNo\":\"12177\"}]}}";
        String formatStr = Test2.responseFormat(str);
        System.out.println(formatStr);
    }


    /**
     * @param resString
     * @return String
     * @throws
     * @Description 响应数据格式化
     * @author lgh
     * @date 2018/10/29-13:45
     */
    private static String responseFormat(String resString){

        StringBuffer jsonForMatStr = new StringBuffer();
        int level = 0;
        for(int index=0;index<resString.length();index++)//将字符串中的字符逐个按行输出
        {
            //获取s中的每个字符
            char c = resString.charAt(index);

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0  && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                    System.out.println(c);
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }
    /**
     * @param level
     * @return
     * @throws
     * @author lgh
     * @date 2018/10/29-14:29
     */
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int i = 0; i < level; i++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }








}
