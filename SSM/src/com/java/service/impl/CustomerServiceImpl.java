package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.CustomerMapper;
import com.java.domain.Customer;
import com.java.service.CustomerService;

//业务
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	/*
	 * 注入接口对象   @resource @autowired
	 */
	@Resource
	private CustomerMapper customerMapper;
	

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerMapper.findAll();
	}


	@Override
	public void save(Customer customer) {
		// 判断是添加还是修改
		if(customer.getId() != null) {
			//修改
			customerMapper.update(customer);
		}else {
		customerMapper.save(customer);
		}
	}


	@Override
	public Customer findById(Integer id) {
		// TODO Auto-generated method stub
		return customerMapper.findById(id);
	}


	@Override
	public void delete(Integer[] id) {
		// TODO Auto-generated method stub
		customerMapper.delete(id);
	}
	
}
