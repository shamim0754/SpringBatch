package com.javaaround.processor;

import org.springframework.batch.item.ItemProcessor;
import com.javaaround.Domain;
 
public class DomainItemProcessor implements ItemProcessor<Domain, Domain>{
 
    @Override
    public Domain process(Domain domain) throws Exception {
      
        /*
         * Only return results which are equal or more than 60%
         *
         */
        if(domain.getId() > 3){
            return null;
        }
 
        return domain;
    }
 
}