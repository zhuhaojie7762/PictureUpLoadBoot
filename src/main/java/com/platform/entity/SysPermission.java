package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class SysPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 菜单是否可见
	 */
	private Integer visiable;
	
	/**
	 * 菜单id
	 */
	private String menuId;

	/**
	 * 0:可修改
	 * 1:不可修改
	 */
	private Integer edit;
	
	/**
	 * 0:可新增
	 * 1:不可新增
	 */
	private Integer added;
	
	
	/**
	 * 0:可删除
	 * 1:不可删除
	 */
	private Integer del;
	
	/**
	 * 权限描述
	 */
	private String description;
	
	
	/**
	 * 记录生成时间
	 * @return
	 */
	private Date createTime;
	
	
	
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public Integer getVisiable() {
		return visiable;
	}

	public String getMenuId() {
		return menuId;
	}

	public Integer getEdit() {
		return edit;
	}

	public Integer getAdded() {
		return added;
	}

	public Integer getDel() {
		return del;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVisiable(Integer visiable) {
		this.visiable = visiable;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public void setEdit(Integer edit) {
		this.edit = edit;
	}

	public void setAdded(Integer added) {
		this.added = added;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SysPermission [id=" + id + ", visiable=" + visiable + ", menuId=" + menuId + ", edit=" + edit
				+ ", added=" + added + ", del=" + del + ", description=" + description + ", createTime=" + createTime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((del == null) ? 0 : del.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((edit == null) ? 0 : edit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((visiable == null) ? 0 : visiable.hashCode());
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
		SysPermission other = (SysPermission) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (del == null) {
			if (other.del != null)
				return false;
		} else if (!del.equals(other.del))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (edit == null) {
			if (other.edit != null)
				return false;
		} else if (!edit.equals(other.edit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (visiable == null) {
			if (other.visiable != null)
				return false;
		} else if (!visiable.equals(other.visiable))
			return false;
		return true;
	}
	
	
	

}
