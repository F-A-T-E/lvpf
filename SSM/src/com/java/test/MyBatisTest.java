package com.java.test;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.java.dao.CustomerMapper;
import com.java.domain.Customer;

public class MyBatisTest {
	
	@Test
	public void test() throws IOException {
		
		//1.����sqlsessionFactory
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//����sqlMapConfig.xml�ļ�
		InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
		
		//����SqlSessionFactory
		SqlSessionFactory factory = builder.build(is);
		
		//3.��sqlsession
		SqlSession sqlSession = factory.openSession();
		
		//��ȡmapper�ӿڵĶ���
		CustomerMapper customerMapper  = (CustomerMapper) sqlSession.getMapper(Customer.class);
		
		Customer customer = new Customer();
		customer.setName("С��");
		customer.setTelephone("48465");
		customer.setAddress("�������");
		customer.setGender("��");
		
		/* customerMapper.saveCustomer(customer); */
		
		//6���ύ����		
		sqlSession.commit();
		
		sqlSession.close();
	}

}
