/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.dao.login;

import com.mycompany.springsampathweb.bean.common.StatusBean;
import com.mycompany.springsampathweb.bean.login.LoginInputBean;
import com.mycompany.springsampathweb.bean.login.PageBean;
import com.mycompany.springsampathweb.bean.login.SectionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chanuka
 */
@Repository
@Scope("prototype")
public class LoginDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIRST_LOGIN = "select PASSWORD,username,FULLNAME,STATUSCODE from SYSTEMUSER where lower(username)=?";
    private final String SQL_SECTIONPAGE = "SELECT S.SECTIONCODE,S.DESCRIPTION as sectiondes,P.PAGECODE,P.DESCRIPTION as pagedes,P.URL FROM SECTIONPAGE SP,PAGE P,SECTION S WHERE SP.USERROLECODE = ? AND P.PAGECODE = SP.PAGECODE AND P.PAGECODE !='LGPG' AND P.STATUS = 'ACT' AND SP.SECTIONCODE=S.SECTIONCODE AND S.STATUS = 'ACT'";
//    private final String SQL_USERSECTION = "SELECT S.SECTIONCODE,S.DESCRIPTION as sectiondes FROM USERROLESECTION US,SECTION S WHERE US.USERROLECODE = ? AND US.SECTIONCODE=S.SECTIONCODE AND S.STATUS = 'ACT'";
//    private final String SQL_SECTIONPAGE = "SELECT P.PAGECODE,P.DESCRIPTION as pagedes,P.URL FROM SECTIONPAGE SP,PAGE P WHERE SP.USERROLECODE = ? AND P.PAGECODE = SP.PAGECODE AND P.PAGECODE !='LGPG' AND P.STATUS = 'ACT' AND SP.SECTIONCODE=?";
    private final String SQL_PAGETASK = "select P.PAGECODE,T.TASKCODE from PAGETASK PT,PAGE P,TASK T where PT.USERROLECODE = ? and PT.PAGECODE = P.PAGECODE and PT.TASKCODE=T.TASKCODE and T.STATUS='ACT' and P.STATUS='ACT'";

    public LoginInputBean getUser(String username) throws Exception {

        LoginInputBean inputBean = new LoginInputBean();
        String fullName = "";
        String password = "";
        String status = "";
        Map<String, Object> listmap = jdbcTemplate.queryForMap(SQL_FIRST_LOGIN, new Object[]{username});

        if (listmap.size() != 0) {

            password = listmap.get("password") + "";
            fullName = listmap.get("firstname") + "";
            status = listmap.get("status") + "";

            inputBean.setPassword(password);
            inputBean.setUsername(username);
            inputBean.setFirstname(fullName);
            inputBean.setStatus(status);
            inputBean.setMessage("");
            return inputBean;

        } else {

            inputBean.setMessage("Invalid");
            return inputBean;
        }
    }

    public HashMap<SectionBean, List<PageBean>> getSectionPages(String userrole) throws Exception {

        List<PageBean> pageList = null;
        HashMap<SectionBean, List<PageBean>> secMap = new HashMap<>();// key : page code value : task list
        try {

            List<Map<String, Object>> secPageList = jdbcTemplate.queryForList(SQL_SECTIONPAGE,
                    new Object[]{userrole});

            for (Map<String, Object> record : secPageList) {

                SectionBean sectionBean = new SectionBean();
                sectionBean.setSectionCode(record.get("SECTIONCODE") + "");
                sectionBean.setDescription(record.get("sectiondes") + "");

                PageBean pageBean = new PageBean();
                pageBean.setPageCode(record.get("PAGECODE") + "");
                pageBean.setDescription(record.get("pagedes") + "");
                pageBean.setUrl(record.get("URL") + "");

                pageList = secMap.get(sectionBean);

                if (pageList == null || pageList.isEmpty()) {

                    pageList = new ArrayList<>();
                    pageList.add(pageBean);

                } else {

                    pageList.add(pageBean);
                }

                secMap.put(sectionBean, pageList);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
            } catch (Exception e) {
                throw e;
            }
        }
        return secMap;
    }

//    public HashMap<SectionBean, List<PageBean>> getSectionPages(String userrole) throws Exception {
//
//        List<PageBean> pageList = null;
//        HashMap<SectionBean, List<PageBean>> secMap = new HashMap<>();// key : page code value : task list
//        try {
//
//            List<Map<String, Object>> secList = jdbcTemplate.queryForList(SQL_USERSECTION,
//                    new Object[]{userrole});
//
//            for (Map<String, Object> section : secList) {
//
//                SectionBean sectionBean = new SectionBean();
//                sectionBean.setSectionCode(section.get("SECTIONCODE") + "");
//                sectionBean.setDescription(section.get("sectiondes") + "");
//
//                List<Map<String, Object>> secPageList = jdbcTemplate.queryForList(SQL_SECTIONPAGE,
//                        new Object[]{userrole, section.get("SECTIONCODE")});
//
//                pageList = new ArrayList<>();
//
//                for (Map<String, Object> page : secPageList) {
//
//                    PageBean pageBean = new PageBean();
//                    pageBean.setPageCode(page.get("PAGECODE") + "");
//                    pageBean.setDescription(page.get("pagedes") + "");
//                    pageBean.setUrl(page.get("URL") + "");
//                    pageList.add(pageBean);
//                }
//
//                secMap.put(sectionBean, pageList);
//            }
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//            } catch (Exception e) {
//                throw e;
//            }
//        }
//        return secMap;
//    }
    public HashMap<String, List<String>> getPageTasks(String userrole) throws Exception {

        List<String> taskList = null;
        HashMap<String, List<String>> pageTaskMap = new HashMap<>();// key : page code value : task list
        try {

            List<Map<String, Object>> pageTaskList = jdbcTemplate.queryForList(SQL_PAGETASK,
                    new Object[]{userrole});

            for (Map<String, Object> record : pageTaskList) {

                taskList = pageTaskMap.get(record.get("PAGECODE") + "");

                if (taskList == null || taskList.isEmpty()) {

                    taskList = new ArrayList<>();
                    taskList.add(record.get("TASKCODE") + "");

                } else {

                    taskList.add(record.get("TASKCODE") + "");
                }

                pageTaskMap.put(record.get("PAGECODE") + "", taskList);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
            } catch (Exception e) {
                throw e;
            }
        }
        return pageTaskMap;
    }

    public List<StatusBean> getStatusList(String statusCategory) throws Exception {
        List<StatusBean> statusBeanList = null;

        String query = "select STATUSCODE,DESCRIPTION from STATUS where CATEGORYCODE = ?";
        Object[] inputs = new Object[]{statusCategory};

        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(query, inputs);

        if (!resultSet.isEmpty()) {
            statusBeanList = new ArrayList<>();

            for (Map<String, Object> record : resultSet) {
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusCode(record.get("STATUSCODE") + "");
                statusBean.setDescription(record.get("DESCRIPTION") + "");
                statusBeanList.add(statusBean);
            }
        } else {
        }

        return statusBeanList;
    }
}
