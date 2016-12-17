/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.common;

import com.epic.springsampathweb.bean.common.StatusBean;
import com.epic.springsampathweb.bean.login.PageBean;
import com.epic.springsampathweb.bean.login.SectionBean;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author chanuka
 */
@Component
@Scope("session")
public class SessionBean {

    HashMap<SectionBean, List<PageBean>> sectionPages;
    HashMap<String, List<String>> pageTasks;
    List<StatusBean> statusBeanList;
    String systemUser;
    String currentPage;
    String currentSection;

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }

    public List<StatusBean> getStatusBeanList() {
        return statusBeanList;
    }

    public void setStatusBeanList(List<StatusBean> statusBeanList) {
        this.statusBeanList = statusBeanList;
    }

    public HashMap<SectionBean, List<PageBean>> getSectionPages() {
        return sectionPages;
    }

    public void setSectionPages(HashMap<SectionBean, List<PageBean>> sectionPages) {
        this.sectionPages = sectionPages;
    }

    public HashMap<String, List<String>> getPageTasks() {
        return pageTasks;
    }

    public void setPageTasks(HashMap<String, List<String>> pageTasks) {
        this.pageTasks = pageTasks;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(String currentSection) {
        this.currentSection = currentSection;
    }

}
