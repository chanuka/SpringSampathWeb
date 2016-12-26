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
public class SystemUserMapper implements RowMapper<SystemUserBean> {

    @Override
    public SystemUserBean mapRow(ResultSet rs, int rowNum) throws SQLException {

        SystemUserBean user = new SystemUserBean();

            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setUserRole(rs.getString("USERROLECODE"));
            user.setUserRoleDes(rs.getString("USERROLEDES"));
            user.setStatus(rs.getString("STATUSCODE"));
            user.setFirstname(rs.getString("FULLNAME"));
            user.setExpiryDate((rs.getDate("EXPIRYDATE")));
        
        return user;
    }
}
