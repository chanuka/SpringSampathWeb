/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.util.validators;

import com.mycompany.springsampathweb.bean.usermanagement.TaskBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author chanuka
 */
@Component
public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TaskBean taskBean = (TaskBean)target;
        
        String taskCode = taskBean.getTaskCode();
        String description = taskBean.getDescription();
        String status = taskBean.getStatus();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskCode", "taskbean.taskcode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "taskbean.description.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "taskbean.status.empty");

        //Business validation
//        if (!password.equals(confPassword)) {
//            errors.rejectValue("description", "customer.password.missMatch");
//        }
//        if (age < 18 || age > 60) {
//            errors.rejectValue("status", "customer.age.range.invalid");
//        }

    }

}
