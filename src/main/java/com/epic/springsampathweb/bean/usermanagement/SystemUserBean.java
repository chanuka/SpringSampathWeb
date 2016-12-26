/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.bean.usermanagement;

import com.epic.springsampathweb.util.common.StatusBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chanuka
 */
public class SystemUserBean {

    private String userName;
    private String userRole;
    private String userRoleDes;
    private String status;
    private Date lastupdatedtime;
    private Date createdtime;
    private String createdtimeStr;
    private String password;
    private String firstname;
    private String lastname;
    private int firstlogin;
    private Date expiryDate;
    private String email;
    private String message;

    private boolean isUpdateBut;
    private boolean isAddBut;
    private List<StatusBean> statusBeanList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreatedtimeStr() {
        return createdtimeStr;
    }

    public void setCreatedtimeStr(String createdtimeStr) {
        this.createdtimeStr = createdtimeStr;
    }

    public boolean isIsUpdateBut() {
        return isUpdateBut;
    }

    public void setIsUpdateBut(boolean isUpdateBut) {
        this.isUpdateBut = isUpdateBut;
    }

    public boolean isIsAddBut() {
        return isAddBut;
    }

    public void setIsAddBut(boolean isAddBut) {
        this.isAddBut = isAddBut;
    }

    public List<StatusBean> getStatusBeanList() {
        return statusBeanList;
    }

    public void setStatusBeanList(List<StatusBean> statusBeanList) {
        this.statusBeanList = statusBeanList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(int firstlogin) {
        this.firstlogin = firstlogin;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserRoleDes() {
        return userRoleDes;
    }

    public void setUserRoleDes(String userRoleDes) {
        this.userRoleDes = userRoleDes;
    }

}
