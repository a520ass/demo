package com.hf.spring.mybatis.entity;

import com.hf.spring.mybatis.entity.base.DataEntity;
import java.util.Date;

public class Menu extends DataEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.parent_id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.parent_ids
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String parentIds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.name
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.href
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String href;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.permission
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String permission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.create_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.create_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.update_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.update_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.sort
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    private Integer sort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.id
     *
     * @return the value of menu.id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.id
     *
     * @param id the value for menu.id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.parent_id
     *
     * @return the value of menu.parent_id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.parent_id
     *
     * @param parentId the value for menu.parent_id
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.parent_ids
     *
     * @return the value of menu.parent_ids
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.parent_ids
     *
     * @param parentIds the value for menu.parent_ids
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.name
     *
     * @return the value of menu.name
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.name
     *
     * @param name the value for menu.name
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.href
     *
     * @return the value of menu.href
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getHref() {
        return href;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.href
     *
     * @param href the value for menu.href
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.permission
     *
     * @return the value of menu.permission
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.permission
     *
     * @param permission the value for menu.permission
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.create_user
     *
     * @return the value of menu.create_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.create_user
     *
     * @param createUser the value for menu.create_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.create_date
     *
     * @return the value of menu.create_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.create_date
     *
     * @param createDate the value for menu.create_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.update_user
     *
     * @return the value of menu.update_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.update_user
     *
     * @param updateUser the value for menu.update_user
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.update_date
     *
     * @return the value of menu.update_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.update_date
     *
     * @param updateDate the value for menu.update_date
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.sort
     *
     * @return the value of menu.sort
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.sort
     *
     * @param sort the value for menu.sort
     *
     * @mbg.generated Sun Feb 12 08:43:41 CST 2017
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}