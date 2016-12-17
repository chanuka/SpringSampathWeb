/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.controller.login;

import com.epic.springsampathweb.bean.login.LoginInputBean;
import com.epic.springsampathweb.bean.login.PageBean;
import com.epic.springsampathweb.bean.login.SectionBean;
import com.epic.springsampathweb.dao.common.CommonDAO;
import com.epic.springsampathweb.dao.login.LoginDAO;
import com.epic.springsampathweb.util.common.SessionBean;
import com.epic.springsampathweb.util.varlist.SessionVarlist;
import com.epic.springsampathweb.util.varlist.StatusVarList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chanuka
 */
@Controller
@Scope("request")
public class LoginController {
    
    @Autowired
    LoginDAO loginDao;
    
    @Autowired
    CommonDAO commonDAO;
    
    @Autowired
    private SessionBean sessionBean;
    
    @RequestMapping(value = "/CheckUserLogin", method = RequestMethod.POST)
    public ModelAndView checkLogin(HttpServletRequest request) throws Exception {
        
        LoginInputBean bean = loginDao.getUser("admin");
        System.out.println("success :" + bean.getPassword());
        sessionBean.setSystemUser("admin");
        
        HashMap<SectionBean, List<PageBean>> sectionPages = loginDao.getSectionPages("ADMIN");
        
        request.getSession().setAttribute(SessionVarlist.SECTIONPAGELIST, sectionPages);
        
        sessionBean.setSectionPages(sectionPages);

//        sectionPages.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
        sectionPages.entrySet().forEach(entry -> {
            System.out.println("section Key : " + entry.getKey().getDescription() + " Value : " + entry.getValue().get(0).getDescription());
        });
        
        HashMap<String, List<String>> pageTasks = loginDao.getPageTasks("ADMIN");
        
        pageTasks.entrySet().forEach(entry -> {
            System.out.println("page Key : " + entry.getKey() + " Value : " + entry.getValue());
        });
        
        sessionBean.setPageTasks(pageTasks);
        
        sessionBean.setStatusBeanList(commonDAO.getStatusList(StatusVarList.STATUS_DEF_CATEGORY));
        
        ModelAndView modelAndView;
        
        modelAndView = new ModelAndView("home");
        
        return modelAndView;
    }
    
}
