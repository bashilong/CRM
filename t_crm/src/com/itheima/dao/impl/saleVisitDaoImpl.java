package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.dao.SaleVisitDao;

public class saleVisitDaoImpl extends HibernateDaoSupport implements SaleVisitDao {

	@Override
	public void save(SaleVisit saleVisit) {
		getHibernateTemplate().save(saleVisit);
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
	public List<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		int firstResult = (currentPage-1)*pageSize;
		int maxResults = pageSize;
		criteria.setProjection(null);
		return (List<SaleVisit>) getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}


}
