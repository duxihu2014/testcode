package sifangtestcode;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Test {

    public static void main(String[] args) {




        String host = "xxx.xxx.xxx.xxx"; //AD服务器
        String port = "389"; //端口
        String url = new String("ldap://" + host + "：" + port);

        String userName = "huwensong-v"; //用户名称
        String password = "1234567890-pppp"; //密码
        String domain = "@xxx.com"; //邮箱的后缀名
        String user = userName.indexOf(domain) > 0 ? userName : userName + domain;

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, user);//不带邮箱后缀名的话,会报错,具体原因还未探究。高手可以解释分享。
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);

        DirContext ctx;
        try {
            ctx = new InitialDirContext(env);
            ctx.close();
            System.out.println("验证成功！");
        } catch (NamingException err) {

            err.printStackTrace();

            System.out.println("验证失败！");

        }

    }

}
