package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.dao.SaleVisitDao;
import com.itheima.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}
	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		int totalSize = saleVisitDao.findCount(criteria);
		List<SaleVisit> list = saleVisitDao.findByPage(criteria, currentPage, pageSize);
		
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		pageBean.setTotalPage((int) Math.ceil(totalSize*1.0/pageSize));
		pageBean.setList(list);
		return pageBean;
	}

}
