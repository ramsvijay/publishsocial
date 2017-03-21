package com.bean;

import java.io.Serializable;

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
import org.springframework.context.annotation.Configuration;
@Entity
@Table(name = "register")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Configuration
public class register implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	 @GeneratedValue
	 @Column(name = "id")
	 private int id;
	 @Column(name = "name")
     private String name;
	 public String getEmail() {
		return email;
	}
	 @XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}
	@XmlElement
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccesstoken() {
		return accesstoken;
	}
	@XmlElement
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getAccesstokensecret() {
		return accesstokensecret;
	}
	@XmlElement
	public void setAccesstokensecret(String accesstokensecret) {
		this.accesstokensecret = accesstokensecret;
	}
	 @Id
	@Column(name = "email")
	 private String email;
	@Column(name = "password")
	 private String password;
	@Column(name = "mobile")
	 private String mobile;
	@Column(name = "accesstoken",length=100)
	 private String accesstoken;
	@Column(name = "accesstokensecret",length=100)
	 private String accesstokensecret;
	 public int getId() {
		return id;
	}

@XmlElement
	public void setId(int id) {
		this.id = id;
	}
public String getName() {
	return name;
}
@XmlElement
public void setName(String name) {
	this.name = name;
}


}
