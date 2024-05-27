package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 图片表
 * @author zhuhaojie
 *
 */
public class Pictures implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 父id
	 */
	private String pid;
	/**
	 * 图片标题
	 */
	private String title;
	
	/**
	 * 图片名称
	 */
	private String name;
	
	/**
	 * 图片路径
	 */
	private String url;
	
	/**
	 * 记录创建时间
	 */
	private Date createTime;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public String getPid() {
		return pid;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Pictures [id=" + id + ", pid=" + pid + ", title=" + title + ", name=" + name + ", url=" + url
				+ ", createTime=" + createTime + ", remark=" + remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Pictures other = (Pictures) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
