package com.arpankarki.spring.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arpankarki.spring.dao.CustomerDAO;
import com.arpankarki.spring.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

	@Autowired // Autowires the session factory / injects the session factory
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession(); // getting the session object
		Query<Customer> customerQuery = session.createQuery("from Customer order by lastName", Customer.class);// creating
																												// customer
																												// query
		List<Customer> customers = customerQuery.getResultList(); // Retrieving set of customers
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Customer customerToBeDeleted = session.get(Customer.class, id);
		session.delete(customerToBeDeleted);

	}
}
