package com.fl.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/12/15:48
 * @Description:数据库的用户表
 */
public class User implements Serializable {
    private static final Long serialVersionUID=1L;

    private int uid;
    private String username;//对应数据库uname
    private String  upassword;//密码
    private String usex;
    private String ustatus;//'用户的激活状态 0 未激活 1 激活'
    private String code;//'邮件激活码'
    private String email;
    private int urole;//'用户 0 管理员 1'

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public int getUrole() {
        return urole;
    }

    public void setUrole(int urole) {
        this.urole = urole;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", upassword='" + upassword + '\'' +
                ", usex='" + usex + '\'' +
                ", ustatus='" + ustatus + '\'' +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", urole=" + urole +
                '}';
    }
}
