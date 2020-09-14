package com.java.test;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.dao.CustomerMapper;
import com.java.domain.Customer;
import com.java.service.CustomerService;

public class MybatisSpringTest {
	
	
	@Test
	public void test() {
		
		//1������spring����
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2����ȡ����
		//CustomerMapper customerMapper = (CustomerMapper)ac.getBean("customerService");
		
		CustomerService customerService = (CustomerService)ac.getBean("customerService");
		//3.���÷���
		
		Customer customer = new Customer();
		customer.setName("С��444444");
		customer.setGender("��");
		customer.setTelephone("021-15924428");
		customer.setAddress("����������");
		
		/* customerService.saveCustomer(customer); */
	}

}
