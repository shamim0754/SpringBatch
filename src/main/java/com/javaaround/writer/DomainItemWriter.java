package com.javaaround.writer;

import java.util.List;
import java.io.Writer;
import java.io.IOException;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import com.javaaround.Domain;
public class DomainItemWriter implements ItemWriter<Domain>,
                                        FlatFileFooterCallback {

    private ItemWriter<Domain> delegate;
    private int size = 0;
    @Override
    public void write(List<? extends Domain> domains) throws Exception{
        for (Domain domain : domains) {
             size++;
        }
        delegate.write(domains);
    }
    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.write("Total Amount Processed: " + size);
    }
    public void setDelegate(ItemWriter delegate) {
        this.delegate=delegate;
    }
}