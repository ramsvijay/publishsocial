package com.bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name = "view")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ViewList {

	 private List<Status1> view;
	 
	 public ViewList() {
		 view= new ArrayList<Status1>();
		    }

	 
	 public ViewList(List<Status1> items) {
			this.view = items;
		    }

	public List<Status1> getView() {
		return view;
	}
	
	
	 @XmlElement(name = "view")
	public void setView(List<Status1> view) {
		this.view = view;
	}
	
	
}
