package com.javaaround;
import com.javaaround.xmladapter.LocalDateAdapter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Domain {

	private int id;
	private String domain;
	private LocalDate createdDate;

    public Domain(){
    	
    }

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="create")
	@XmlJavaTypeAdapter(type = LocalDate.class, value=LocalDateAdapter.class)
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	@XmlElement(name="name")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}