package com.javaaround.xmladapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
 
import org.joda.time.LocalDate;
 
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
 	@Override
    public LocalDate unmarshal(String v) throws Exception {
        return new LocalDate(v);
    }
 	@Override
    public String marshal(LocalDate v) throws Exception {
    	//default formate yyyy-MM-dd.if need other use
    	// v.toString("dd/MM/yyyy")
        return v.toString();  
    }
 
}