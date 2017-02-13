package com.hf.test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.RoleMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context-mybatis.xml","classpath:dubbo-provider.xml"})
@TestPropertySource({"classpath:application.properties"})
public class KrtServiceTest{
	
	@Autowired
	private DataSource dataSource;
	@Autowired RoleMapper roleMapper;
	@Autowired UserMapper userMapper;
	@Autowired RoleService roleService;
	
	@Test
	public void test1() throws SQLException, InterruptedException{
		dataSource.getConnection();
		TimeUnit.HOURS.sleep(24);
	}
	
	@Test
	public void test2(){
		List<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		List<User> usersByRoleId = roleService.getUsersByRoleId(arrayList);
		System.out.println(usersByRoleId);
	}

}

