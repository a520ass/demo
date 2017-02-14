package com.hf.spring.mybatis.mapper;

import com.hf.spring.mybatis.MyBatisDao;
import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.User;

import java.util.List;
@MyBatisDao
public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    Menu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    List<Menu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    int updateByPrimaryKey(Menu record);

	void deleteByRoleId(Long roleId);
	List<Menu> selectAllIn(List<Integer> ids);
}