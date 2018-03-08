package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();
	private SaleVisitService saleVisitService;
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	private int currentPage = 1;
	private int pageSize = 5;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String findByPage(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria,currentPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	public String save(){
		User user  =new User();
		user.setUser_id(3L);
		
		saleVisit.setUser(user);
		saleVisitService.save(saleVisit);
		return Constant.SAVE_SUCCESS;
		
	}

}
