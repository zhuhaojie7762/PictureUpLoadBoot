//package com.platform.service.impl;
//
//
//import com.platform.dao.SysUserDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysUser;
//import com.platform.service.SysUserService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
///**
// * 指定此类是一个spring服务层
// * 并指定此服务层名称为sysUserService
// * 以便控制层使用
// * @author zhuhaojie
// *
// */
//@Service(value="sysUserService")
//public class SysUserServiceImpl extends ServiceManager<SysUser,SysUserDAO> implements SysUserService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysUserDAO")
//	@Override
//	public void setDao(SysUserDAO dao) {
//		this.dao = dao;
//
//	}
//}
