/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.common;

import com.epic.springsampathweb.dao.common.CommonDAO;
import com.epic.springsampathweb.util.varlist.TaskVarList;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chanuka
 */
@Component
public class CommonUtil {

    @Autowired
    CommonDAO commonDAO;

    //checks the accees to the method name passed
    public boolean checkMethodAccess(String taskcode, String page, String userRole, SessionBean sessionBean) {
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

    public static Date convertStringtoDate(String date) throws Exception {
        Date fdate = null;
        try {
            String pattern = "MM/DD/YYYY HH:mm:SS";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            fdate = dateFormat.parse(date);
        } catch (Exception e) {
            throw e;
        }
        return fdate;
    }

    public AuditBean makeAuditTrace(HttpServletRequest request, SessionBean sessionBean, String task, String page, String description, String newValue, String oldValue) throws Exception {
        AuditBean auditBean = new AuditBean();

        auditBean.setTask(task);
        auditBean.setPage(page);
        auditBean.setSection(commonDAO.getSectionCode(page, sessionBean.getSystemUser().getUserRole()));
        auditBean.setDescription(description);
        auditBean.setIp(request.getRemoteAddr());
        auditBean.setLastupdateduser(sessionBean.getSystemUser().getUserName());
        auditBean.setUserrole(sessionBean.getSystemUser().getUserRole());
        auditBean.setNewvalue(newValue);
        auditBean.setOldvalue(oldValue);
        auditBean.setSkip(false);

        return auditBean;
    }

    public static String makeHash(String text) throws Exception {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
