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
 * @author chanuka gunathilaka
 */
public class TaskBean {

    private String taskCode;
    private String description;
    private String status;
    private String statusDes;
    private String message;
    private Date lastupdatedtime;
    private Date createdtime;
    private String createdtimeStr;
    private boolean isUpdateBut;
    private boolean isAddBut;
    private List<StatusBean> statusBeanList;

    /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    /*-------for access control-----------*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StatusBean> getStatusBeanList() {
        return statusBeanList;
    }

    public void setStatusBeanList(List<StatusBean> statusBeanList) {
        this.statusBeanList = statusBeanList;
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

    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdate() {
        return vupdate;
    }

    public void setVupdate(boolean vupdate) {
        this.vupdate = vupdate;
    }

    public boolean isVdelete() {
        return vdelete;
    }

    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
    }

}
