/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epic.springsampathweb.bean.usermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chanuka
 */
public class TaskMapper implements RowMapper<TaskBean> {
           @Override
	   public TaskBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		   
		  TaskBean task = new TaskBean();
		  task.setTaskCode(rs.getString("TASKCODE"));
		  task.setStatus(rs.getString("STATUS"));
		  task.setDescription(rs.getString("DESCRIPTION"));
		  task.setLastupdatedtime((rs.getDate("LASTUPDATEDTIME")));
//		  task.setCreatedtime((rs.getDate("CREATEDTIME")));
		  task.setCreatedtimeStr((rs.getString("CREATEDTIME")));

	      return task;
	   }
	}
