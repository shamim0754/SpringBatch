package com.javaaround.fieldmapper;

import org.joda.time.LocalDate;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
 
import com.javaaround.Domain;
 
public class DomainFieldSetMapper implements FieldSetMapper<Domain>{
 
    @Override
    public Domain mapFieldSet(FieldSet fieldSet) throws BindException {
        Domain domain = new Domain();
        domain.setId(fieldSet.readInt(0));
        domain.setDomain(fieldSet.readString(1));
        domain.setCreatedDate(new LocalDate(fieldSet.readDate(2,"dd/MM/yyyy")));
        
        return domain;
    }
 
}