package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 * @author zhuhaojie
 *
 */
public class SysMenu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 父菜单id
	 */
	private String pid;
	
	/**
	 * 菜单依赖路径
	 */
	private String depend_path;
	
	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 菜单请求url
	 */
	private String url;
	
	/**
	 * 身份证明
	 */
    private String identification;
    
    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单类型 
     * 1：菜单
     * 2:按钮
     */
    private Integer type;
    
    /**
     * 是否跳转
     * 0：不跳转
     * 1:跳转
     */
    private Integer isJumpLink;
    
    /**
     * 菜单排序编号
     */
    private Integer sortNum;
    
    /**
     * 菜单图标
     */
    private String icon;
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 菜单创建者id
     */
    private String createUserId;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 菜单更新者id
     */
    private String updateUserId;
	public String getId() {
		return id;
	}
	public String getPid() {
		return pid;
	}
	public String getDepend_path() {
		return depend_path;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public String getIdentification() {
		return identification;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getType() {
		return type;
	}
	public Integer getIsJumpLink() {
		return isJumpLink;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public String getIcon() {
		return icon;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setDepend_path(String depend_path) {
		this.depend_path = depend_path;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setIsJumpLink(Integer isJumpLink) {
		this.isJumpLink = isJumpLink;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", depend_path=" + depend_path + ", name=" + name + ", url=" + url
				+ ", identification=" + identification + ", remark=" + remark + ", type=" + type + ", isJumpLink="
				+ isJumpLink + ", sortNum=" + sortNum + ", icon=" + icon + ", createTime=" + createTime
				+ ", createUserId=" + createUserId + ", updateTime=" + updateTime + ", updateUserId=" + updateUserId
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((createUserId == null) ? 0 : createUserId.hashCode());
		result = prime * result + ((depend_path == null) ? 0 : depend_path.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identification == null) ? 0 : identification.hashCode());
		result = prime * result + ((isJumpLink == null) ? 0 : isJumpLink.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sortNum == null) ? 0 : sortNum.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((updateUserId == null) ? 0 : updateUserId.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysMenu other = (SysMenu) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUserId == null) {
			if (other.createUserId != null)
				return false;
		} else if (!createUserId.equals(other.createUserId))
			return false;
		if (depend_path == null) {
			if (other.depend_path != null)
				return false;
		} else if (!depend_path.equals(other.depend_path))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identification == null) {
			if (other.identification != null)
				return false;
		} else if (!identification.equals(other.identification))
			return false;
		if (isJumpLink == null) {
			if (other.isJumpLink != null)
				return false;
		} else if (!isJumpLink.equals(other.isJumpLink))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sortNum == null) {
			if (other.sortNum != null)
				return false;
		} else if (!sortNum.equals(other.sortNum))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updateUserId == null) {
			if (other.updateUserId != null)
				return false;
		} else if (!updateUserId.equals(other.updateUserId))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}