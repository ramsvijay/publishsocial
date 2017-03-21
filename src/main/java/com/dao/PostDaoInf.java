package com.dao;

import java.util.List;

import com.bean.Status1;
import com.bean.register;

public interface PostDaoInf {
	
	public String statusUpdate(Status1 s);

	
	public List<Status1> view(String n);

	public String twit(Status1 s);

	public String both(Status1 s);
	
	
	public Status1 detail(Integer id);
    
	public register registerdetails(register r);
	public register login(String email,String password);

    public String updatetoken(String accesstoken,String accesssecret,String email);

}
