/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.util.common;

/**
 *
 * @author chanuka
 */
public interface AccessControlService {

    public boolean checkAccess(String method, String userRole);

}
