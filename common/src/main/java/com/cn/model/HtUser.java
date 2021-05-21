package com.cn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("ht_user")
public class HtUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("user_pass")
    private String userPass;

    /**
     * 用户ID
     */
    @TableField(exist = false)  //表示该字段在数据库中不存在
    private String userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String moble;

    /**
     * 部门
     */
    private String department;

    /**
     * 担任职责
     */
    private String duty;

    /**
     * 角色ids
     */
    @TableField("role_ids")
    private String roleIds;

    /**
     * 0 不可用 1 可用
     */
    @TableField("is_enable")
    private String isEnable;

    /**
     * 创建时间
     */
    @TableField("cre_datetime")
    private Date creDatetime;

    /**
     * 修改时间
     */
    @TableField("mod_datetime")
    private Date modDatetime;

    /**
     * 创建者
     */
    @TableField("cre_name")
    private String creName;

    /**
     * 修改者
     */
    @TableField("mod_name")
    private String modName;



    @Override
    public String toString() {
        return "HtUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", name='" + name + '\'' +
                ", moble='" + moble + '\'' +
                ", department='" + department + '\'' +
                ", duty='" + duty + '\'' +
                ", roleIds='" + roleIds + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", creDatetime=" + creDatetime +
                ", modDatetime=" + modDatetime +
                ", creName='" + creName + '\'' +
                ", modName='" + modName + '\'' +
                ", userId='" + userId + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }

    /**
     * 旧密码
     */
    @TableField(exist = false)  //表示该字段在数据库中不存在
    private String oldPassword;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoble() {
        return moble;
    }

    public void setMoble(String moble) {
        this.moble = moble;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreDatetime() {
        return creDatetime;
    }

    public void setCreDatetime(Date creDatetime) {
        this.creDatetime = creDatetime;
    }

    public Date getModDatetime() {
        return modDatetime;
    }

    public void setModDatetime(Date modDatetime) {
        this.modDatetime = modDatetime;
    }

    public String getCreName() {
        return creName;
    }

    public void setCreName(String creName) {
        this.creName = creName;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
