/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.controller.usermanagement;

import com.mycompany.springsampathweb.bean.common.StatusBean;
import com.mycompany.springsampathweb.bean.usermanagement.TaskBean;
import com.mycompany.springsampathweb.dao.usermanagement.TaskDAO;
import com.mycompany.springsampathweb.util.common.AccessControlService;
import com.mycompany.springsampathweb.util.common.CommonUtil;
import com.mycompany.springsampathweb.util.common.SessionBean;
import com.mycompany.springsampathweb.util.validators.TaskValidator;
import com.mycompany.springsampathweb.util.varlist.PageVarList;
import com.mycompany.springsampathweb.util.varlist.TaskVarList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(taskValidator);
    }

    @RequestMapping(value = "/ViewTask", method = RequestMethod.GET)
    public ModelAndView View(HttpServletRequest request, @ModelAttribute("tasksearchform") TaskBean inputBean) throws Exception {

        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("task", "tasksearchform", inputBean);

        return modelAndView;
    }

    @RequestMapping(value = "listTask")
    public @ResponseBody
    List<TaskBean> List() {

        List<TaskBean> rateList = taskDAO.getTaskBeanList("");
        return rateList;
    }

    @RequestMapping(value = "/addTask", params = "Add", method = RequestMethod.POST)
    public ModelAndView Add(HttpServletRequest request, @ModelAttribute("tasksearchform") @Validated TaskBean inputBean, BindingResult result, Model model) throws Exception {
        String message;
        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);
        System.out.println("Adddd:");

        if (result.hasErrors()) {
            System.out.println("validation fail:");
        } else {
            System.out.println("validation success:");

            message = taskDAO.insertTask(inputBean);

            if (message.isEmpty()) {
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
    public ModelAndView Update(HttpServletRequest request, @ModelAttribute("tasksearchform") TaskBean inputBean, Model model) throws Exception {
        int message;
        List<StatusBean> statusBeanList = sessionBean.getStatusBeanList();
        inputBean.setStatusBeanList(statusBeanList);
        System.out.println("updateeeee:");
        message = taskDAO.updateTask(inputBean);

        if (message > 0) {
            inputBean = new TaskBean();
            model.addAttribute("successMessage", "Update Success");
        } else {
            model.addAttribute("errorMessage", "Update Error");
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

        } catch (Exception e) {
//			taskBean.setErrormessage("error");
        }
        return taskBean;
    }

    @RequestMapping(value = "deleteTask", method = RequestMethod.POST)
    public @ResponseBody
    TaskBean Delete(@RequestParam String taskCode) throws Exception {
        TaskBean taskBean = new TaskBean();

        try {

            System.out.println("called deleteTask :");
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

        status = new CommonUtil().checkMethodAccess(task, page, userRole,sessionBean);

        return status;
    }

}
