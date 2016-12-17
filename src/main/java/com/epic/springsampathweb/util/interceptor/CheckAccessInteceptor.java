/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.interceptor;

import com.epic.springsampathweb.util.common.AccessControlService;
import com.epic.springsampathweb.util.common.SessionBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chanuka
 */
public class CheckAccessInteceptor implements HandlerInterceptor {

    @Autowired
    private SessionBean sessionBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;
            String methodName = method.getMethod().getName();

            System.out.println("Called CheckAccessInteceptor :" + methodName);

            if (sessionBean.getSystemUser() != null) {

                if (method.getBean() instanceof AccessControlService) {

                    if (((AccessControlService) method.getBean()).checkAccess(methodName, "ADMIN")) {
                        return true;
                    } else {
                        response.sendRedirect("login.jsp");
                        System.out.println("Access Denied :");
                        return false;

                    }
                } else {
                    return true;
                }

            } else {

                response.sendRedirect("login.jsp");
                System.out.println("session expire :");
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }

}
