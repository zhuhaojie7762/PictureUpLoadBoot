package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 系统日志表
 * @author zhuhaojie
 *
 */
public class SysLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 客户端ip
	 */
	private String ip;
	
	/**
	 * sessionId
	 */
	private String sessionId;
	
	/**
	 * 浏览器名称
	 */
	private String browerName;
	
	/**
	 * 浏览器信息
	 */
	private String browerMessage;
	
	/**
	 * 请求地址
	 */
	private String requestUrl;
	
	/**
	 * 请求参数
	 */
	private String requestParam;
	
	/**
	 * 异常信息名称
	 */
	private String exceptionName;
	

	
	/**
	 * 登录用户名
	 */
	private String userName;
	
	/**
	 * 访问结果
	 */
	private String status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getBrowerName() {
		return browerName;
	}

	public String getBrowerMessage() {
		return browerMessage;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	

	public String getUserName() {
		return userName;
	}

	public String getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setBrowerName(String browerName) {
		this.browerName = browerName;
	}

	public void setBrowerMessage(String browerMessage) {
		this.browerMessage = browerMessage;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysLog [id=" + id + ", ip=" + ip + ", sessionId=" + sessionId + ", browerName=" + browerName
				+ ", browerMessage=" + browerMessage + ", requestUrl=" + requestUrl + ", requestParam=" + requestParam
				+ ", exceptionName=" + exceptionName + ", userName=" + userName + ", status="
				+ status + ", createTime=" + createTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((browerMessage == null) ? 0 : browerMessage.hashCode());
		result = prime * result + ((browerName == null) ? 0 : browerName.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((exceptionName == null) ? 0 : exceptionName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((requestParam == null) ? 0 : requestParam.hashCode());
		result = prime * result + ((requestUrl == null) ? 0 : requestUrl.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		SysLog other = (SysLog) obj;
		if (browerMessage == null) {
			if (other.browerMessage != null)
				return false;
		} else if (!browerMessage.equals(other.browerMessage))
			return false;
		if (browerName == null) {
			if (other.browerName != null)
				return false;
		} else if (!browerName.equals(other.browerName))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (exceptionName == null) {
			if (other.exceptionName != null)
				return false;
		} else if (!exceptionName.equals(other.exceptionName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (requestParam == null) {
			if (other.requestParam != null)
				return false;
		} else if (!requestParam.equals(other.requestParam))
			return false;
		if (requestUrl == null) {
			if (other.requestUrl != null)
				return false;
		} else if (!requestUrl.equals(other.requestUrl))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}
