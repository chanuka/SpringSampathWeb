/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.dao.common;

import com.epic.springsampathweb.util.common.StatusBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class CommonDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<StatusBean> getStatusList(String statusCategory) throws Exception {
        List<StatusBean> statusBeanList = null;

        String query = "select STATUSCODE,DESCRIPTION from STATUS where CATEGORYCODE = ?";
        Object[] inputs = new Object[]{statusCategory};

        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(query, inputs);

        if (!resultSet.isEmpty()) {
            statusBeanList = new ArrayList<>();

            for (Map<String, Object> record : resultSet) {
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusCode(record.get("STATUSCODE") + "");
                statusBean.setDescription(record.get("DESCRIPTION") + "");
                statusBeanList.add(statusBean);
            }
        } else {
        }

        return statusBeanList;
    }

    public Date getCurrentDate() throws Exception {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date convertedDate;

        Map<String, Object> cdate;
        try {
            String query = "SELECT TO_CHAR (SYSDATE, 'MM-DD-YYYY HH24:MI:SS') NOW FROM DUAL";
            cdate = jdbcTemplate.queryForMap(query);

            convertedDate = (Date) formatter.parse(cdate.get("NOW").toString());
        } catch (Exception e) {
            throw e;
        }
        return convertedDate;

    }
}
