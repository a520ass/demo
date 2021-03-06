package com.hf.test;


import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hf.spring.mybatis.mapper.RoleMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.RoleService;
import com.hf.spring.mybatis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context-mybatis.xml","classpath:dubbo-provider.xml"})
@TestPropertySource({"classpath:application.properties"})
public class KrtServiceTest{
	
	@Autowired
	private DataSource dataSource;
	@Autowired RoleMapper roleMapper;
	@Autowired UserMapper userMapper;
	@Autowired RoleService roleService;
	@Autowired UserService userService;
	
	@Test
	public void startService() throws SQLException, InterruptedException{
		dataSource.getConnection();
		TimeUnit.HOURS.sleep(24);
	}

}

