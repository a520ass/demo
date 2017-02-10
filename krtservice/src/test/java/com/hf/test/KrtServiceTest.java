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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context-mybatis.xml","classpath:dubbo-provider.xml"})
@TestPropertySource({"classpath:application.properties"})
public class KrtServiceTest{
	
	@Autowired
	private DataSource dataSource;
	
	
	@Test
	public void test1() throws SQLException, InterruptedException{
		dataSource.getConnection();
		TimeUnit.HOURS.sleep(24);
	}

}

