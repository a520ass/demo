package com.hf.spring.mybatis.mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BatchDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void assign(final Long roleId, final Long[] userIds){
		
		String sql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
		BatchPreparedStatementSetter pss=new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, userIds[i]);
				ps.setLong(2, roleId);
			}
			
			@Override
			public int getBatchSize() {
				return userIds.length;
			}
		};
		jdbcTemplate.batchUpdate(sql, pss);
	}
}
