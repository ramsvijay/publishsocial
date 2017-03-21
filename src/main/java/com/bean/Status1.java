package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;

@Entity
@Table(name = "status1")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Status1 {
	
	 @Id
	 @GeneratedValue
	 @Column(name = "id")
	 private int id;
	 @Column(name="facebookid")
	 private String facebookid;
	 @Column(name="twitterid")
	 private String twitterid;
	 @Column(name="tumblrid")
	 private String tumblrid;
	 @Column(name="status")
	private String status;
	
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFacebookid() {
		return facebookid;
	}
	@XmlElement
	public void setFacebookid(String facebookid) {
		this.facebookid = facebookid;
	}
	public String getTwitterid() {
		return twitterid;
	}
	@XmlElement
	public void setTwitterid(String twitterid) {
		this.twitterid = twitterid;
	}
	public String getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	 
     public String getUsername() {
		return username;
	}
     @XmlElement
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTumblrid() {
		return tumblrid;
	}
	@XmlElement
     public void setTumblrid(String tumblrid) {
		this.tumblrid = tumblrid;
	}

	@Column(name="username")
	private String username;
	 
}
