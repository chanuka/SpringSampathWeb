/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.springsampathweb.util.common;

import com.epic.springsampathweb.bean.login.SectionBean;
import java.util.Comparator;

/**
 *
 * @author chanuka
 */
public class SectionComparator implements Comparator<SectionBean> {

    public int compare(SectionBean _first, SectionBean _second) {
        return _first.getDescription().compareTo(_second.getDescription());
    }

}
