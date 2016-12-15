/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.dao.usermanagement;

import com.mycompany.springsampathweb.bean.usermanagement.TaskBean;
import com.mycompany.springsampathweb.bean.usermanagement.TaskMapper;
import com.mycompany.springsampathweb.dao.common.CommonDAO;
import java.util.ArrayList;
import java.util.Date;
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
public class TaskDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonDAO commonDAO;

    private final String SQL_INSERT_TASK = "insert into TASK (TASKCODE,DESCRIPTION,STATUS, LASTUPDATEDUSER, LASTUPDATEDTIME, CREATEDTIME) values (?,?,?,?,?,?)";

    public List<TaskBean> getTaskBeanList(String rateType) {
        List<TaskBean> taskBeanList = new ArrayList<>();

        try {
            String sql = "select DESCRIPTION , TASKCODE,STATUS,CREATEDTIME from TASK ORDER BY CREATEDTIME DESC";

            List<Map<String, Object>> taskList = jdbcTemplate.queryForList(sql, new Object[]{});

            for (Map<String, Object> record : taskList) {
                TaskBean rate = new TaskBean();

                if (record.get("DESCRIPTION") != null && record.get("DESCRIPTION") != "") {
                    rate.setDescription(record.get("DESCRIPTION").toString());
                } else {
                    rate.setDescription("--");
                }

                if (record.get("TASKCODE") != null && record.get("TASKCODE") != "") {
                    rate.setTaskCode(record.get("TASKCODE").toString());
                } else {
                    rate.setTaskCode("--");
                }

                if (record.get("STATUS") != null && record.get("STATUS") != "") {
                    rate.setStatus(record.get("STATUS").toString());
                } else {
                    rate.setStatus("--");
                }
                if (record.get("CREATEDTIME") != null && record.get("CREATEDTIME") != "") {
                    rate.setCreatedtime((Date) record.get(record.get("CREATEDTIME")));
                } else {

                }
                taskBeanList.add(rate);
            }
        } catch (Exception e) {
        }

        return taskBeanList;
    }

    public TaskBean getTaskBean(String taskCode) {
        TaskBean taskBean = null;

        try {
            String sql = "select DESCRIPTION,TASKCODE,STATUS,CREATEDTIME,LASTUPDATEDTIME from TASK WHERE TASKCODE=?";

            taskBean = jdbcTemplate.queryForObject(sql, new Object[]{taskCode}, new TaskMapper());

//            taskBean = new TaskBean();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskBean;
    }

    public int deleteTask(String taskCode) throws Exception{
        int count = 0;

        try {
            String sql = "delete from TASK WHERE TASKCODE=?";

            count = jdbcTemplate.update(sql, new Object[]{taskCode});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int updateTask(TaskBean inputBean) throws Exception{
        
        int count = 0;
        inputBean.setLastupdatedtime(commonDAO.getCurrentDate());
        
        try {
            String sql = "update TASK set description=?,status=?,LASTUPDATEDTIME=? WHERE TASKCODE=?";

            count = jdbcTemplate.update(sql, new Object[]{inputBean.getDescription(),inputBean.getStatus(),inputBean.getLastupdatedtime(),inputBean.getTaskCode()});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public String insertTask(TaskBean inputBean)
            throws Exception {
        String message = "";
        int value = 0;

        inputBean.setLastupdatedtime(commonDAO.getCurrentDate());
        inputBean.setCreatedtime(commonDAO.getCurrentDate());

        try {
            value = jdbcTemplate.update(SQL_INSERT_TASK,
                    new Object[]{inputBean.getTaskCode(), inputBean.getDescription(), inputBean.getStatus(), "admin", inputBean.getLastupdatedtime(),
                        inputBean.getCreatedtime()});

        } catch (Exception e) {
            e.printStackTrace();
            message = "error insert";
        }
        if (value == 1) {
            message = "";
        }
        return message;
    }
}
