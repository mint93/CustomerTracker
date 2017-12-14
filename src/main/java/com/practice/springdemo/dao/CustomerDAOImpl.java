package com.practice.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		List<Customer> customers =  query.getResultList();
		
		return customers;
	}

	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		//saveOrUpdate: if primaryKey/id empty, then INSERT new customer (save), else UPDATE existing customer (update)
		session.saveOrUpdate(customer);
	}

	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, id);
	}

	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

}
