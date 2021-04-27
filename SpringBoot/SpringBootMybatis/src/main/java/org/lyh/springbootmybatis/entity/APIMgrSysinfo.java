package org.lyh.springbootmybatis.entity;

import java.util.Date;

public class APIMgrSysinfo {
    private String rowGuid;

    private Date createTime;

    private Integer status;

    private Date updatedTime;

    private String sysCode;

    private String sysDesc;

    private String sysName;

    private String sysUpwd;

    private String sysUname;

    private String roleId;

    private String sysSalt;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid == null ? null : rowGuid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc == null ? null : sysDesc.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysUpwd() {
        return sysUpwd;
    }

    public void setSysUpwd(String sysUpwd) {
        this.sysUpwd = sysUpwd == null ? null : sysUpwd.trim();
    }

    public String getSysUname() {
        return sysUname;
    }

    public void setSysUname(String sysUname) {
        this.sysUname = sysUname == null ? null : sysUname.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getSysSalt() {
        return sysSalt;
    }

    public void setSysSalt(String sysSalt) {
        this.sysSalt = sysSalt == null ? null : sysSalt.trim();
    }
}