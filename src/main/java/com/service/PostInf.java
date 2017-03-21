package com.service;

import java.util.List;

import com.bean.Status1;
import com.bean.register;

public interface PostInf {
	
	public String statusUpdate(Status1 s);
	public String twit(Status1 s);
	public List<Status1> view(String n);
	
	public String both(Status1 s);

	
	public Status1 detail(Integer id);
    public register registerdetails(register r);
	public register login(String email,String password);
    public String updatetoken(String accesstoken,String accesssecret,String email);
}
