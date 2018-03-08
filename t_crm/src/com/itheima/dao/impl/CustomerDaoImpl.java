package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.Customer;
import com.itheima.dao.CustomerDao;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public void save(Customer customer) {
		getHibernateTemplate().save(customer);
	}

	@Override
	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		criteria.setProjection(null);
		List<Customer> list = (List<Customer>) getHibernateTemplate().findByCriteria(criteria,(currentPage-1)*pageSize,pageSize);
		return list;
	}

	@Override
	public void delete(Customer customer) {
		getHibernateTemplate().delete(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}

	@Override
	public List<Customer> findAll() {
		
		return (List<Customer>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Customer.class));
	}

}
