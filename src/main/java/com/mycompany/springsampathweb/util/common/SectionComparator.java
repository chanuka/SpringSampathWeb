/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsampathweb.util.common;

import com.mycompany.springsampathweb.bean.login.SectionBean;
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
