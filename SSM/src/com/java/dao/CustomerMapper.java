package com.java.dao;

import java.util.List;
import com.java.domain.Customer;
public interface CustomerMapper {
	
	/*
	 * 查询所有客户的信息
	 */

	public List<Customer> findAll();
	
	/*
	 * 
	 * 保存客户信息
	 */
	
	public void save(Customer customer);

	
	public Customer findById(Integer id);

	
	/*
	 * 修改对象
	 */
	public void update(Customer customer);

	public void delete(Integer[] id);

}
