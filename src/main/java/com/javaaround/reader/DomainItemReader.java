package com.javaaround.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import com.javaaround.Domain;
/**
 * @author Dinesh Rajput
 *
 */
public class DomainItemReader implements ItemReader<Domain>{
 
 private List<Domain> domainList;
 private int domainCount = 0;
 @Override
 public Domain read() throws Exception, UnexpectedInputException,
   ParseException {
  
  if(domainCount < domainList.size())
   	return domainList.get(domainCount++);
  else
   	return null;
  
 }
 public List<Domain> getDomainList() {
  return domainList;
 }
 public void setDomainList(List<Domain> domainList) {
  this.domainList = domainList;
 }
 
}