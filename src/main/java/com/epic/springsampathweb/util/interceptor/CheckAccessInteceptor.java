/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.interceptor;

import com.epic.springsampathweb.dao.common.CommonDAO;
import com.epic.springsampathweb.util.common.AccessControlService;
import com.epic.springsampathweb.util.common.AuditBean;
import com.epic.springsampathweb.util.common.SessionBean;
import com.epic.springsampathweb.util.varlist.LogoutMsgVarList;
import com.epic.springsampathweb.util.varlist.SessionVarlist;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;
            String methodName = method.getMethod().getName();

            System.out.println("Called CheckAccessInteceptor :" + methodName);

            if (sessionBean.getSystemUser() != null) {

                ServletContext sc = request.getServletContext();
                HashMap<String, String> userMap = (HashMap<String, String>) sc.getAttribute(SessionVarlist.USERMAP);
                String sessionId = userMap.get(sessionBean.getSystemUser().getUserName());

                if (sessionId.equals(request.getSession(false).getId())) {

                    if (method.getBean() instanceof AccessControlService) {

                        if (((AccessControlService) method.getBean()).checkAccess(methodName, sessionBean.getSystemUser().getUserRole())) {
                            return true;
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("LogoutUserLogin/ERROR_ACCESS");
                            rd.forward(request, response);
//                            response.sendRedirect("LogoutUserLogin");
                            System.out.println("Method Access Denied :");
                            return false;

                        }
                    } else {
                        return true;
                    }
                } else {//multi access
                    RequestDispatcher rd = request.getRequestDispatcher("LogoutUserLogin/ERROR_ACCESSPOINT");
                    rd.forward(request, response);
                    System.out.println("multi access denied :");
                    return false;
                }
            } else {//session expire

                RequestDispatcher rd = request.getRequestDispatcher("LogoutUserLogin/ERROR_USER_INFO");
                rd.forward(request, response);
                System.out.println("session expire :");
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        System.out.println("called post handler :");

        try {

            if (handler instanceof HandlerMethod) {

                HandlerMethod method = (HandlerMethod) handler;
                String methodName = method.getMethod().getName();
                // Set Audittrace
                if (sessionBean != null && sessionBean.getSystemUser() != null) {

                    AuditBean audittrace = sessionBean.getAuditTrace();

                    if (audittrace != null && !audittrace.isSkip()) {

                        audittrace.setCreatetime(commonDAO.getCurrentDate());
                        audittrace.setLastupdatedtime(commonDAO.getCurrentDate());

                        String status = commonDAO.insertAudit(audittrace);

                        System.out.println("Audittrace inserted status : " + status);
                    } else {
                        System.out.println("Audittrace Skipped in method :" + methodName);
                    }

                }

            } else {
                System.out.println("Not a HandlerMethod :");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                sessionBean.setAuditTrace(null);
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }

}
