package com.lib.equipment.manager.mapper;

import com.lib.equipment.manager.model.Permission;
import com.lib.equipment.manager.model.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    long countByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int deleteByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    List<Permission> selectByExampleWithRowbounds(PermissionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated Thu Sep 19 20:02:21 CST 2019
     */
    int updateByPrimaryKey(Permission record);
}