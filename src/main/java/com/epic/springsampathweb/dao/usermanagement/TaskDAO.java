/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.dao.usermanagement;

import com.epic.springsampathweb.bean.usermanagement.TaskBean;
import com.epic.springsampathweb.bean.usermanagement.TaskMapper;
import com.epic.springsampathweb.dao.common.CommonDAO;
import com.epic.springsampathweb.util.common.CommonUtil;
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
            String sql = "select DESCRIPTION , TASKCODE,STATUS,to_char(createdtime,'yyyy-mm-dd HH:MI:SS') AS createdtime  from TASK ORDER BY CREATEDTIME DESC";

            List<Map<String, Object>> taskList = jdbcTemplate.queryForList(sql, new Object[]{});

            for (Map<String, Object> record : taskList) {
                TaskBean task = new TaskBean();

                if (record.get("DESCRIPTION") != null && record.get("DESCRIPTION") != "") {
                    task.setDescription(record.get("DESCRIPTION").toString());
                } else {
                    task.setDescription("--");
                }

                if (record.get("TASKCODE") != null && record.get("TASKCODE") != "") {
                    task.setTaskCode(record.get("TASKCODE").toString());
                } else {
                    task.setTaskCode("--");
                }

                if (record.get("STATUS") != null && record.get("STATUS") != "") {
                    task.setStatus(record.get("STATUS").toString());
                } else {
                    task.setStatus("--");
                }
                if (record.get("CREATEDTIME") != null && record.get("CREATEDTIME") != "") {
//                    task.setCreatedtime(CommonUtil.convertStringtoDate("05/12/2016 23:20:16"));
                    task.setCreatedtimeStr(record.get("CREATEDTIME").toString());

                } else {

                }
                taskBeanList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public int deleteTask(String taskCode) throws Exception {
        int count = 0;

        try {
            String sql = "delete from TASK WHERE TASKCODE=?";

            count = jdbcTemplate.update(sql, new Object[]{taskCode});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int updateTask(TaskBean inputBean) throws Exception {

        int count = 0;
        inputBean.setLastupdatedtime(commonDAO.getCurrentDate());

        try {
            String sql = "update TASK set description=?,status=?,LASTUPDATEDTIME=? WHERE TASKCODE=?";

            count = jdbcTemplate.update(sql, new Object[]{inputBean.getDescription(), inputBean.getStatus(), inputBean.getLastupdatedtime(), inputBean.getTaskCode()});

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
