package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bean.Status1;
import com.bean.register;
@Configuration
public class PostDaoImpl implements PostDaoInf{

	@Autowired
	 SessionFactory sessionFactory;

	 Session session = null;
	 Transaction tx = null;
	
	 
	 @Override
	public String statusUpdate(Status1 s) {
	
		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  session.save(s);
          tx.commit();
          session.close();

		return "success";
	}


	@Override
	public List<Status1> view(String n) {

		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		Criteria l = session.createCriteria(Status1.class);
		  l.add(Restrictions.eq("username",n));
		  List<Status1> b=l.list();
		  System.out.println(l);
		  tx.commit();
		  session.close();
		  return b;
		 
		
		
	}


	@Override
	public String twit(Status1 s) {
		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  session.save(s);
        tx.commit();
        session.close();

		return "success";
		
	}


	@Override
	public String both(Status1 s) {
		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  session.save(s);
      tx.commit();
      session.close();

		return "success";
	}


	@Override
	public Status1 detail(Integer id) {

		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		
		  Status1 s=(Status1)session.load(Status1.class, new Integer(id));
		return s;
	}

/**Login*/
	@Override
	public register login(String email, String password) {
		
		session = sessionFactory.openSession();
		  tx = session.beginTransaction();
		  System.out.println(email);
		  register s=(register)session.load(register.class,email);
		  
		  if(password.equals(s.getPassword()))
		  {
		  
		    return s;
		
		  }
		  else
			  return null;
	}


@Override
public register registerdetails(register r) {
	// TODO Auto-generated method stub
	session = sessionFactory.openSession();
	  tx = session.beginTransaction();
	  session.save(r);
     tx.commit();
     session.close();

	
	return r;
}


@Override
public String updatetoken(String accesstoken, String accesssecret, String email) {

	
	session = sessionFactory.openSession();
	  tx = session.beginTransaction();

	  Query q = session.createQuery("update register set accesstoken=:accesstoken,accesstokensecret=:accesssecret where email=:email");
	  q.setString("accesstoken", accesstoken);
	  q.setString("accesssecret", accesssecret);
	  q.setString("email", email);
	  q.executeUpdate();
	  //register s=(register) session.load(register.class,email);

      
      tx.commit();
      
	   
	return "update";
}

}
