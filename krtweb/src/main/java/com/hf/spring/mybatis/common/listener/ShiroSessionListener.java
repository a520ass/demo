package com.hf.spring.mybatis.common.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShiroSessionListener extends SessionListenerAdapter{
	
	private static final Logger logger=LoggerFactory.getLogger(ShiroSessionListener.class); 
	
	@Override
	public void onStart(Session session) {
		logger.info("会话创建：" + session.getId()); 
	}

	@Override
	public void onStop(Session session) {
		logger.info("会话停止：" + session.getId());    
	}

	@Override
	public void onExpiration(Session session) {
		logger.info("会话过期：" + session.getId()); 
	}

}
