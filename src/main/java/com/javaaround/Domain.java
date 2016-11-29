package com.javaaround;
import com.javaaround.xmladapter.LocalDateAdapter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
 
 


@XmlRootElement
@Entity
@Table(name = "domains")
public class Domain {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String domain;
	@Column(name = "created_date", nullable = false)
	//@Type  map between 
	//jodatime LocalDate and database specific Date.
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
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