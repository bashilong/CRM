package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.dao.CustomerDao;
import com.itheima.service.CustomerService;
@Transactional 
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		int totalSize = customerDao.findCount(criteria);
		List<Customer> list = customerDao.findByPage(criteria,currentPage,pageSize);
		
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrentPage(currentPage);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}


}
