package com.itheima.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.constant.Constant;
import com.itheima.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkMan editLinkMan;
	public LinkMan getEditLinkMan() {
		return editLinkMan;
	}
	
	private Long cid;
	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	private List<LinkMan> list ;
	public List<LinkMan> getList() {
		return list;
	}
	public String findByCid(){
		//定义连线对象,表示查询哪张表
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//添加查询条件,按照客户的ID来查询
		criteria.add(Restrictions.eq("customer.cust_id", cid));
		list = linkManService.findByCid(criteria);
		return Constant.JSON_SUCCESS;
	}
	
	//修改联系人的第一步(根据ID查询)
	public String edit(){
		editLinkMan = linkManService.findById(linkMan.getLkm_id());
		return Constant.EDIT_SUCCESS;
	}
	//修改联系人第二步
	public String update(){
		linkManService.update(linkMan);
		return Constant.UPDATE_SUCCESS;
	}
	
	//删除联系人
	public String delete(){
		linkManService.delete(linkMan);
		return Constant.DELETE_SUCCESS;
	}

	public String save(){
		linkManService.save(linkMan);
		return Constant.SAVE_SUCCESS;
	}
	
	//分页查询
	private int currentPage = 1;
	private int pageSize = 5;
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		//设置过滤条件
		//名称
		if(!StringUtils.isEmpty(linkMan.getLkm_name())){
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		//邮箱
		if(!StringUtils.isEmpty(linkMan.getLkm_email())){
			criteria.add(Restrictions.like("lkm_email", "%"+linkMan.getLkm_email()+"%"));
		}
		//qq
		if(!StringUtils.isEmpty(linkMan.getLkm_qq())){
			criteria.add(Restrictions.like("lkm_qq", "%"+linkMan.getLkm_qq()+"%"));
		}
		//手机
		if(!StringUtils.isEmpty(linkMan.getLkm_mobile())){
			criteria.add(Restrictions.like("lkm_mobile", "%"+linkMan.getLkm_mobile()+"%"));
		}
		//性别
		if(!StringUtils.isEmpty(linkMan.getLkm_gender())){
			criteria.add(Restrictions.like("lkm_gender", "%"+linkMan.getLkm_gender()+"%"));
		}
		//所属客户
		if(linkMan.getCustomer()!= null && !StringUtils.isEmpty(linkMan.getCustomer().getCust_id())){
			criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		PageBean<LinkMan> pageBean = linkManService.findByPage(criteria,currentPage,pageSize);
		//存数据---push
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
}
