package com.youmina.day0725;

/**
 * @author zhangxinhe
 * @date 2018/7/25 15:06
 */
public class Test {

    public static void main(String[] args){

        UserDao target = new UserDao();

        //1.静态代理
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();

        //2.JDK代理

        System.out.println(target.getClass());
        IUserDao proxy_jdk = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy_jdk.getClass());
        proxy_jdk.save();

        //3.cglib代理
        UserCglibDao target_3 = new UserCglibDao();
        UserCglibDao proxy_cglib = (UserCglibDao)new ProxyCglibFactory(target_3).getProxyInstance();
        proxy_cglib.save();
    }
}
