package com.youmina.day0725;

/**
 * @author zhangxinhe
 * @date 2018/7/25 15:04
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("UserDao 保存数据");
    }
}
