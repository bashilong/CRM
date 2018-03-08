package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.service.CustomerService;
import com.itheima.util.MyFileUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@Override
	public Customer getModel() {
		return customer;
	}

	//要想获取文件数据,需要这么声明
	private File upload; // 属性名称就是页面的 name属性值  <input type="file" name="upload"/>
	private String uploadContentType;  //文件类型  = name属性值 + ContentType 
	private String uploadFileName;  //文件名称 = name属性值 + FileName
	private int currentPage=1;
	private int pageSize=5;
	
	//提供set方法，以便页面修改了获取的具体页码数
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	private Customer editCustomer;
	private List<Customer> list;
	public Customer getEditCustomer() {
		return editCustomer;
	}
	public List<Customer> getList() {
		return list;
	}
	
	public String findAll(){
		list = customerService.findAll();
		return Constant.JSON_SUCCESS;
	}
	
	//修改客户第一步(仅仅是根据ID查询)
	public String edit(){
		//根据id查询客户数据
	    editCustomer = customerService.findById(customer.getCust_id());
	    return Constant.EDIT_SUCCESS;
	}
	//修改客户第二步
	public String update(){
		customerService.update(customer);
		return Constant.UPDATE_SUCCESS;
	}
	
	//删除客户
	public String delete(){
		customerService.delete(customer);
		return Constant.DELETE_SUCCESS;
	}
	
	/**
	 * 分页显示客户列表
	 * @return
	 */
	public String findByPage(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//判断条件
		//校验客户名称
		if(!StringUtils.isEmpty(customer.getCust_name())){
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		//校验客户电话
		if(!StringUtils.isEmpty(customer.getCust_phone())){
			criteria.add(Restrictions.like("cust_phone", "%"+customer.getCust_phone()+"%"));
		}
		
		//校验客户来源
		if(customer.getCust_source()!=null && !StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			criteria.add(Restrictions.like("cust_source.dict_id", "%"+customer.getCust_source().getDict_id()+"%"));
		}
		
		//校验客户行业
		if(customer.getCust_industry()!=null && !StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			criteria.add(Restrictions.like("cust_industry.dict_id", "%"+customer.getCust_industry().getDict_id()+"%"));
		}
		
		//校验客户级别
		if(customer.getCust_level()!=null && !StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			criteria.add(Restrictions.like("cust_level.dict_id", "%"+customer.getCust_level().getDict_id()+"%"));
		}
		
		
		//查询数据,有返回值
		PageBean<Customer> pageBean = customerService.findByPage(criteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	public String save() throws IOException{
	
		
		//对所有的数据都进行校验
		//校验客户名称
		if(StringUtils.isEmpty(customer.getCust_name())){
			addActionError("客户名称不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户地址
		if(StringUtils.isEmpty(customer.getCust_address())){
			addActionError("客户地址不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户电话
		if(StringUtils.isEmpty(customer.getCust_phone())){
			addActionError("客户电话不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户行业
		if(StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			addActionError("客户行业不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户来源
		if(StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			addActionError("客户来源不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户级别
		if(StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			addActionError("客户级别不能为空!");
			return Constant.INPUT_ERROR;
		}
		
		//设置创建人和负责人
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		customer.setCust_create_id(user);
		customer.setCust_user_id(user);
		
		//存储图片
		if(upload!=null){
			String fileName = MyFileUtil.getFileName(uploadFileName);
			File file = new File("D:/heima28/img",fileName);
			FileUtils.copyFile(upload, file);
			customer.setCust_image("img/"+fileName);
		}
		
		
		customerService.save(customer);
		return Constant.SAVE_SUCCESS;
		
	}
	
}
