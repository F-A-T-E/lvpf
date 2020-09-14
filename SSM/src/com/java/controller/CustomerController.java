package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MailcapCommandMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.domain.Customer;
import com.java.service.CustomerService;
import com.mysql.cj.xdevapi.Result;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	/*
	 * 注入service对象
	 */
	
	@Resource
	CustomerService customerService;
	
	
	/*
	 * 查询所有数据  datagray  给页面返回 json格式数据
	 * easyui 的 datagrid组件，有分页功能，需要接受json数据： [{},{}]
	 */
	@RequestMapping("/list")
	@ResponseBody  //转换对象为json或xml
	public List<Customer> list(){
		
		//查询数据
		List<Customer> list = customerService.findAll();
		return list;
	}
	
	
	//涉及map集合 存储返回给页面的对象数据
	private Map<String, Object> Result = new HashMap<String, Object>();
	/*
	 * 分页查询
	 */
	@RequestMapping("/listByPage")
	@ResponseBody
	public Map<String, Object> listByPage(Integer page,Integer rows){
		
		//设置分页参数
		PageHelper.startPage(page, rows);
		//查询所有数据
		List<Customer> list = customerService.findAll();
		
		//使用pageinfo封装查询结果
		PageInfo<Customer> pageInfo  = new PageInfo<Customer>(list);
		
		//从pageInfo对象取出查询结果
		//总记录数
		long total = pageInfo.getTotal();
		
		//当前页数据列表
		List<Customer> custliList = pageInfo.getList();
		
		Result.put("total", total);
		Result.put("rows", custliList);
		return Result;
	}
	
	
	/*
	 * 保存数据
	 */
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(Customer customer){
		
		try {
			customerService.save(customer);
			Result.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Result.put("success", false);
			Result.put("msg", e.getMessage());
		}
		return Result;
	}
	
	
	/*
	 * 根据id查询对象
	 * 
	 */
	
	@RequestMapping("/findById")
	@ResponseBody
	public Customer findById(Integer id) {
		Customer customer = customerService.findById(id);
		return customer;
	}
	
	
	
	/*
	 * 删除数据
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer[] id){
		
		try {
			customerService.delete(id);
			Result.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Result.put("success", false);
			Result.put("msg", e.getMessage());
		}
		return Result;
	}
	
	
	
}
