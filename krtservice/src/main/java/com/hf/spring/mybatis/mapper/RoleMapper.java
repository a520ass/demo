package com.hf.spring.mybatis.mapper;

import com.hf.spring.mybatis.MyBatisDao;
import com.hf.spring.mybatis.entity.Role;

import java.util.List;
@MyBatisDao
public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Feb 12 08:29:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Feb 12 08:29:44 CST 2017
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Feb 12 08:29:44 CST 2017
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Feb 12 08:29:44 CST 2017
     */
    List<Role> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Feb 12 08:29:44 CST 2017
     */
    int updateByPrimaryKey(Role record);
    
    /**
     * 根据role id集合查询菜单id集合
     * @param ids
     * @return
     */
    List<Integer> selectMenuId(List<Integer> ids);
}