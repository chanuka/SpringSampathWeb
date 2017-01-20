/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.controller.usermanagement;

import com.epic.springsampathweb.bean.usermanagement.TaskBean;
import com.epic.springsampathweb.dao.usermanagement.TaskDAO;
import com.epic.springsampathweb.util.common.AccessControlService;
import com.epic.springsampathweb.util.common.AuditBean;
import com.epic.springsampathweb.util.common.CommonUtil;
import com.epic.springsampathweb.util.common.DataTablesRequest;
import com.epic.springsampathweb.util.common.DataTablesResponse;
import com.epic.springsampathweb.util.common.SessionBean;
import com.epic.springsampathweb.util.common.StatusBean;
import com.epic.springsampathweb.util.validators.TaskValidator;
import com.epic.springsampathweb.util.varlist.PageVarList;
import com.epic.springsampathweb.util.varlist.TaskVarList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chanuka
 */
@Controller
@Scope("request")
public class TaskController implements AccessControlService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    private SessionBean sessionBean;

    @Autowired
    TaskValidator taskValidator;

    @Autowired
    CommonUtil commonUtil;

    @InitBinder("tasksearchform")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(taskValidator);
    }

    @RequestMapping(value = "/ViewTask", method = RequestMethod.GET)
    public ModelAndView View(HttpServletRequest request, @ModelAttribute("tasksearchform") TaskBean inputBean) throws Exception {

        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);
        inputBean.setIsUpdateBut(true);
        inputBean.setIsAddBut(false);

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("task", "tasksearchform", inputBean);

        return modelAndView;
    }

//    @RequestMapping(value = "listTask")
//    public @ResponseBody
//    List<TaskBean> List() {
//
//        List<TaskBean> taskList = taskDAO.getTaskBeanList("");
//        return taskList;
//    }

    @RequestMapping(value = "/listTask")
    public @ResponseBody
    DataTablesResponse<TaskBean> List(@RequestBody DataTablesRequest dtReq, HttpServletResponse response) throws Exception {

        // Column names of the table view
        String[] cols = {"TASKCODE", "TK.DESCRIPTION", "STATUSDES", "LASTUPDATEDUSER", "LASTUPDATEDTIME", "CREATEDTIME"};

        String globalSearch = "";
        String searchSQL = "";
        String orderBySQL = "";

        if (!dtReq.searchQuery.contains("'") && !dtReq.searchQuery.contains("\"")) {

            globalSearch = " (TASKCODE LIKE '%" + dtReq.searchQuery + "%'"
                    + " OR TK.DESCRIPTION LIKE '%" + dtReq.searchQuery + "%'"
                    + " OR ST.DESCRIPTION LIKE '%" + dtReq.searchQuery + "%'"
                    + " OR LASTUPDATEDUSER LIKE '%" + dtReq.searchQuery + "%'"
                    + " OR LASTUPDATEDTIME LIKE '%" + dtReq.searchQuery + "%'"
                    + " OR CREATEDTIME LIKE '%" + dtReq.searchQuery + "%')";
        } else {
            globalSearch = "1=1";
        }

        searchSQL = globalSearch;

        // Ordering and limiting for pagination
        orderBySQL = " ORDER BY " + cols[dtReq.sortedColumns.get(0)] + " " + dtReq.sortDirections.get(0);

        int end = dtReq.displayLength + dtReq.displayStart;

        List<TaskBean> listotptask = taskDAO.listTaskForJson(searchSQL,orderBySQL, dtReq.displayStart, end);

        long countTasks = taskDAO.countTaskForJson(searchSQL);

        DataTablesResponse<TaskBean> restask = new DataTablesResponse<TaskBean>();
        restask.data.addAll(listotptask);

        restask.echo = dtReq.echo;
        restask.columns = dtReq.columns;

        restask.totalRecords = countTasks;
        restask.totalDisplayRecords = countTasks;

        return restask;
    }

    @RequestMapping(value = "/addTask", params = "Add", method = RequestMethod.POST)
    public ModelAndView Add(HttpServletRequest request, @ModelAttribute("tasksearchform") @Validated TaskBean inputBean, BindingResult result, Model model) throws Exception {
        String message;
        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);
        inputBean.setIsUpdateBut(true);
        inputBean.setIsAddBut(false);
        System.out.println("Adddd:");

        if (result.hasErrors()) {
            System.out.println("validation fail:");
        } else {
            System.out.println("validation success:");

            String newValue = inputBean.getTaskCode() + "|" + inputBean.getDescription() + "|" + inputBean.getStatus();
            AuditBean auditBean = commonUtil.makeAuditTrace(request, sessionBean, TaskVarList.ADD_TASK, PageVarList.TASK_MGT_PAGE, "Task added successfully :" + inputBean.getTaskCode(), newValue, null);
            sessionBean.setAuditTrace(auditBean);
            message = taskDAO.insertTask(inputBean);

            if (message.isEmpty()) {
                inputBean = new TaskBean();
                inputBean.setStatusBeanList(statusBeanList);

                model.addAttribute("successMessage", "Add Success");
            } else {
                model.addAttribute("errorMessage", "Add Error");
            }
        }

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("task", "tasksearchform", inputBean);

        return modelAndView;
    }

    @RequestMapping(value = "/addTask", params = "Update", method = RequestMethod.POST)
    public ModelAndView Update(HttpServletRequest request, @ModelAttribute("tasksearchform") @Validated TaskBean inputBean, BindingResult result, Model model) throws Exception {
        int message;
        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);
        inputBean.setIsUpdateBut(false);
        inputBean.setIsAddBut(true);

        System.out.println("updateeeee:");

        if (result.hasErrors()) {
            System.out.println("validation fail:");
        } else {
            System.out.println("validation success:");
            String newValue = inputBean.getTaskCode() + "|" + inputBean.getDescription() + "|" + inputBean.getStatus();

            AuditBean auditBean = commonUtil.makeAuditTrace(request, sessionBean, TaskVarList.UPDATE_TASK, PageVarList.TASK_MGT_PAGE, "Task updated successfully :" + inputBean.getTaskCode(), newValue, sessionBean.getOldValue());
            sessionBean.setAuditTrace(auditBean);
            message = taskDAO.updateTask(inputBean);

            if (message > 0) {
                inputBean = new TaskBean();
                model.addAttribute("successMessage", "Update Success");
                inputBean.setIsUpdateBut(true);
                inputBean.setIsAddBut(false);
            } else {
                model.addAttribute("errorMessage", "Update Error");
            }
        }
        inputBean.setStatusBeanList(statusBeanList);

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("task", "tasksearchform", inputBean);

        return modelAndView;
    }

    @RequestMapping(value = "findTask", method = RequestMethod.POST)
    public @ResponseBody
    TaskBean Find(@RequestParam String taskCode) throws Exception {
        TaskBean taskBean = new TaskBean();
        try {
            System.out.println("called findtask :");
            taskBean = taskDAO.getTaskBean(taskCode);

            String oldValue = taskBean.getTaskCode() + "|" + taskBean.getDescription() + "|" + taskBean.getStatus();
            sessionBean.setOldValue(oldValue);

        } catch (Exception e) {
            e.printStackTrace();
//			taskBean.setErrormessage("error");
        }
        return taskBean;
    }

    @RequestMapping(value = "deleteTask", method = RequestMethod.POST)
    public @ResponseBody
    TaskBean Delete(HttpServletRequest request, @RequestParam String taskCode) throws Exception {
        TaskBean taskBean = new TaskBean();

        try {

            System.out.println("called deleteTask :");

            AuditBean auditBean = commonUtil.makeAuditTrace(request, sessionBean, TaskVarList.DELETE_TASK, PageVarList.TASK_MGT_PAGE, "Task deleted successfully :" + taskCode, null, null);
            sessionBean.setAuditTrace(auditBean);

            int count = taskDAO.deleteTask(taskCode);
            if (count > 0) {
                taskBean.setMessage("delete success");
            } else {
                taskBean.setMessage("delete error");
            }

        } catch (Exception e) {
//			taskBean.setErrormessage("error");
        }
        return taskBean;
    }

    @Override
    public boolean checkAccess(String method, String userRole) {

        System.out.println("called checkAccess");
        boolean status;
        String page = PageVarList.TASK_MGT_PAGE;
        String task = null;
        if ("View".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD_TASK;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE_TASK;
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE_TASK;
        }//newly changed
        else if ("activate".equals(method)) {
            task = TaskVarList.UPDATE_TASK;
        } else if ("ViewPopup".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Detail".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        }

        status = commonUtil.checkMethodAccess(task, page, userRole, sessionBean);

        return status;
    }
    
//    private boolean applyUserPrivileges() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        List<Task> tasklist = new Common().getUserTaskListByPage(PageVarList.TASK_MGT_PAGE, request);
//
//        inputBean.setVadd(true);
//        inputBean.setVdelete(true);
//        inputBean.setVupdatelink(true);
//        inputBean.setVsearch(true);
//
//        if (tasklist != null && tasklist.size() > 0) {
//            for (Task task : tasklist) {
//                if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.ADD_TASK)) {
//                    inputBean.setVadd(false);
//                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.DELETE_TASK)) {
//                    inputBean.setVdelete(false);
////                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.LOGIN_TASK)) {
//                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.UPDATE_TASK)) {
//                    inputBean.setVupdatelink(false);
//                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.SEARCH_TASK)) {
//                    inputBean.setVsearch(false);
//                }
//            }
//        }
//        inputBean.setVupdatebutt(true);
//
//        return true;
//    }

}
