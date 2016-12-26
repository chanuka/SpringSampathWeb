package com.epic.springsampathweb.util.common;
/**
 * 
 * @author chanuka
 *
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonProperty;


@SuppressWarnings("serial")
public class DataTablesResponse <T> implements Serializable {

    @JsonProperty(value = "iTotalRecords")
//    public int totalRecords;
    public long totalRecords;

    @JsonProperty(value = "iTotalDisplayRecords")
//    public int totalDisplayRecords;
    public long totalDisplayRecords;

    @JsonProperty(value = "sEcho")
    public String echo;

    @JsonProperty(value = "sColumns")
    public String columns;

    @JsonProperty(value = "aaData")
    public List<T> data = new ArrayList<T>();

    public DataTablesResponse() {
    }
}
