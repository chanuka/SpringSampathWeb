/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.controller.login;

import com.epic.springsampathweb.bean.login.PageBean;
import com.epic.springsampathweb.bean.login.SectionBean;
import com.epic.springsampathweb.bean.usermanagement.SystemUserBean;
import com.epic.springsampathweb.dao.common.CommonDAO;
import com.epic.springsampathweb.dao.login.LoginDAO;
import com.epic.springsampathweb.util.common.CommonUtil;
import com.epic.springsampathweb.util.common.SessionBean;
import com.epic.springsampathweb.util.varlist.LogoutMsgVarList;
import com.epic.springsampathweb.util.varlist.SessionVarlist;
import com.epic.springsampathweb.util.varlist.StatusVarList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView checkLogin(HttpServletRequest request, @ModelAttribute("loginform") SystemUserBean inputBean) throws Exception {

        System.out.println("called LoginController : checkLogin");

        ModelAndView modelAndView;
        System.out.println("username :" + inputBean.getUserName());

        SystemUserBean userBean = loginDao.getUser(inputBean.getUserName());

        if (userBean.getMessage() == null || userBean.getMessage().isEmpty()) {

            System.out.println("success :" + inputBean.getPassword());

            if (userBean.getPassword().equals(CommonUtil.makeHash(inputBean.getPassword()))) {

                HttpSession session = request.getSession(true);

                //set user and sessionid to hashmap
                HashMap<String, String> userMap = null;

                ServletContext sc = request.getServletContext();
                userMap = (HashMap<String, String>) sc.getAttribute(SessionVarlist.USERMAP);
                if (userMap == null) {
                    userMap = new HashMap<String, String>();
                }
                userMap.put(inputBean.getUserName(), session.getId());
                sc.setAttribute(SessionVarlist.USERMAP, userMap);

                sessionBean.setSystemUser(userBean);
                session.setAttribute(SessionVarlist.SYSTEMUSER, userBean);

                session.setAttribute(SessionVarlist.LOGGEDDATE, commonDAO.getCurrentDate());
                session.setAttribute(SessionVarlist.CURRENTDATE, commonDAO.getCurrentDate());

                HashMap<SectionBean, List<PageBean>> sectionPages = loginDao.getSectionPages(userBean.getUserRole());
                session.setAttribute(SessionVarlist.SECTIONPAGELIST, sectionPages);

                sessionBean.setSectionPages(sectionPages);

//        sectionPages.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
//                sectionPages.entrySet().forEach(entry -> {
//                    System.out.println("section Key : " + entry.getKey().getDescription() + " Value : " + entry.getValue().get(0).getDescription());
//                });
                HashMap<String, List<String>> pageTasks = loginDao.getPageTasks(userBean.getUserRole());

//                pageTasks.entrySet().forEach(entry -> {
//                    System.out.println("page Key : " + entry.getKey() + " Value : " + entry.getValue());
//                });
                sessionBean.setPageTasks(pageTasks);

                sessionBean.setStatusBeanList(commonDAO.getStatusList(StatusVarList.STATUS_DEF_CATEGORY));

                modelAndView = new ModelAndView("home");

                return modelAndView;

            } else { //password mismatch

                System.out.println("password error :" + userBean.getMessage());

                modelAndView = new ModelAndView("login");

                return modelAndView;
            }

        } else {// username not found

            System.out.println("error :" + userBean.getMessage());

            modelAndView = new ModelAndView("login");

            return modelAndView;
        }
    }

    @RequestMapping(value = "/LogoutUserLogin/{message}")
    public ModelAndView logoutUserLogin(HttpServletRequest request, @PathVariable Map<String, String> pathVars, Model model) throws Exception {

        System.out.println("called LoginController : logout with error");
        String msg = "";

        System.out.println("path vari:" + pathVars.get("message"));
        msg = pathVars.get("message");

        if (msg.equalsIgnoreCase("ERROR_ACCESS")) {
            model.addAttribute("errorMessage", LogoutMsgVarList.ERROR_ACCESS);
        } else if (msg.equals("ERROR_ACCESSPOINT")) {
            model.addAttribute("errorMessage", LogoutMsgVarList.ERROR_ACCESSPOINT);
        } else if (msg.equals("ERROR_USER_INFO")) {
            model.addAttribute("errorMessage", LogoutMsgVarList.ERROR_USER_INFO);
        } else if (msg.equals("PASSWORD_CHANGE_SUCCESS")) {
            model.addAttribute("errorMessage", LogoutMsgVarList.PASSWORD_CHANGE_SUCCESS);
        } else {
            model.addAttribute("errorMessage", LogoutMsgVarList.ERROR_SESSION);
        }

        HttpSession session = request.getSession(false);
        if (session != null) {

            session.invalidate();
        }

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("login");

        return modelAndView;

    }

    @RequestMapping(value = "/LogoutNow")
    public ModelAndView logoutUserLogin(HttpServletRequest request) throws Exception {

        System.out.println("called LoginController : logout");

        HttpSession session = request.getSession(false);
        if (session != null) {

            session.invalidate();
        }

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("login");

        return modelAndView;

    }

    @RequestMapping("/")
    public ModelAndView checkLogin(HttpServletRequest request) throws Exception {

        System.out.println("called LoginController : welcome");
        ModelAndView modelAndView;

//        modelAndView = new ModelAndView("login");
        modelAndView = new ModelAndView("welcome");

        return modelAndView;

    }

}
