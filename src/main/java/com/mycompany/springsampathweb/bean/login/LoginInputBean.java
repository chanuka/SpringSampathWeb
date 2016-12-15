/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.bean.login;

import java.util.Date;

/**
 *
 * @author chanuka
 */
public class LoginInputBean {

    private String username;
    private String password;
    private String passcode;
    private String message;
    private String title;
    private String firstname;
    private String lastname;
    private String userImage;
    private int firstlogin;
    private Date lastExTime;
    private String status;
    private int passcode_enable;
    private String email;
    private String emailConfirm;
    private String browserTabKey;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(int firstlogin) {
        this.firstlogin = firstlogin;
    }

    public Date getLastExTime() {
        return lastExTime;
    }

    public void setLastExTime(Date lastExTime) {
        this.lastExTime = lastExTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPasscode_enable() {
        return passcode_enable;
    }

    public void setPasscode_enable(int passcode_enable) {
        this.passcode_enable = passcode_enable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public String getBrowserTabKey() {
        return browserTabKey;
    }

    public void setBrowserTabKey(String browserTabKey) {
        this.browserTabKey = browserTabKey;
    }
    
    
    

}
