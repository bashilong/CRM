package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.itheima.bean.BaseDict;
import com.itheima.constant.Constant;
import com.itheima.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseDictAction extends ActionSupport {
	private String dict_type_code;
	private BaseDictService baseDictService;
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	private List<BaseDict> list;
	public List<BaseDict> getList() {
		return list;
	}
	public String findByType(){
		
		list = baseDictService.findByType(dict_type_code);
			
		return Constant.JSON_SUCCESS;
	}
	/*public String findByType(){
		try {
			List<BaseDict> list = baseDictService.findByType(dict_type_code);
			String json = new Gson().toJson(list);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			//System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}*/
}
