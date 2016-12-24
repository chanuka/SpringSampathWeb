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
    private String status;
    private Date lastupdatedtime;
    private Date createdtime;
    private String createdtimeStr;
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

    
    
    
    
}
