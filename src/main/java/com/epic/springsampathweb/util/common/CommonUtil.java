/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.common;

import com.epic.springsampathweb.util.varlist.TaskVarList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chanuka
 */

public class CommonUtil {
    
//    @Autowired
//    private SessionBean sessionBean;

    //checks the accees to the method name passed
    public boolean checkMethodAccess(String taskcode, String page, String userRole,SessionBean sessionBean) {
        boolean access = false;
        if (taskcode == null || taskcode.isEmpty()) {
            access = false;
        } else {
            
            HashMap<String, List<String>> pageTasks = sessionBean.getPageTasks();
            
            List<String> taskList = pageTasks.get(page);
            if (taskList == null) {
                access = false;
            } else if (taskList.size() < 1) {
                access = false;
            } else {
                for (String task : taskList) {
                    if (task.toString().trim().equalsIgnoreCase(taskcode.trim())) {
                        access = true;
                        if (task.toString().equalsIgnoreCase(TaskVarList.VIEW_TASK)) {
                            
                            try {
                                
                                sessionBean.setCurrentPage(page);
                                
                                sessionBean.setCurrentSection(page);
                                
                            } catch (Exception ex) {
                                Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                        break;
                    }
                }
            }
        }
        return access;
    }
    
}
