package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Status1;
import com.bean.register;
import com.dao.PostDaoInf;

public class PostImpl implements PostInf {
	
	@Autowired
	PostDaoInf daoservice;
	
	@Override
	public String statusUpdate(Status1 s) {
		// TODO Auto-generated method stub
		
		String res=daoservice.statusUpdate(s);
		return res;
	}
	@Override
	public List<Status1> view(String n) {
		List<Status1> emp=  daoservice.view(n);
		return emp;
	}
	
	
	@Override
	public String twit(Status1 s) {
		String res=daoservice.twit(s);

		return res;
	}
	@Override
	public String both(Status1 s) {
		String res=daoservice.both(s);

		return res;
	}
	@Override
	public Status1 detail(Integer id) {
		
		return daoservice.detail(id);
	}
	@Override
	public register login(String email, String password) {
		return daoservice.login(email,password);
	}
	@Override
	public register registerdetails(register r) {
		// TODO Auto-generated method stub
		register rs=daoservice.registerdetails(r);
		return rs;
	}
	@Override
	public String updatetoken(String accesstoken, String accesssecret, String email) {

		
		return daoservice.updatetoken(accesstoken,accesssecret,email);
	}
	
	

}
