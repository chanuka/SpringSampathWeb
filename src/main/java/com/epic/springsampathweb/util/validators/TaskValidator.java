/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.validators;

import com.epic.springsampathweb.bean.usermanagement.TaskBean;
import com.epic.springsampathweb.util.varlist.MessageVarList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TaskBean taskBean = (TaskBean) target;

        String taskCode = taskBean.getTaskCode();
        String description = taskBean.getDescription();
//        String status = taskBean.getStatus();

        String ALPHA_NUMERIC_PATTERN = "^[a-zA-Z0-9 ]*$";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskCode", MessageVarList.TASK_MGT_CODE_EMPTY);
        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", MessageVarList.TASK_MGT_DES_EMPTY);
        }
        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", MessageVarList.TASK_MGT_STATUS_EMPTY);
        }

        if (!errors.hasErrors()) {
            //Business validation
            if (taskCode != null && !taskCode.isEmpty()) {
                pattern = Pattern.compile(ALPHA_NUMERIC_PATTERN);
                matcher = pattern.matcher(taskCode);
                if (!matcher.matches()) {

                    errors.rejectValue("taskCode", MessageVarList.TASK_MGT_CODE_ERROR);
                }
            }
        }
        if (!errors.hasErrors()) {
            if (description != null && !description.isEmpty()) {
                pattern = Pattern.compile(ALPHA_NUMERIC_PATTERN);
                matcher = pattern.matcher(description);
                if (!matcher.matches()) {

                    errors.rejectValue("description", MessageVarList.TASK_MGT_DES_ERROR);
                }
            }
        }
    }

}
