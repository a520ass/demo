package com.hf.spring.mybatis.mapper;

import com.hf.spring.mybatis.MyBatisDao;
import com.hf.spring.mybatis.entity.User;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Feb 12 08:15:52 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Feb 12 08:15:52 CST 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Feb 12 08:15:52 CST 2017
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Feb 12 08:15:52 CST 2017
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Feb 12 08:15:52 CST 2017
     */
    int updateByPrimaryKey(User record);
    
	User selectByUsername(String username);
	
	List<Integer> selectUserId(List<Integer> ids);
	List<Integer> selectRoleId(List<Integer> ids);
	List<User> selectAllIn(List<Integer> ids);

	void deleteByRoleId(Long roleId);
	void deleteByUserId(Integer userId);

	void updateLastLoginDate(@Param("username") String username, @Param("date") Date date);
}