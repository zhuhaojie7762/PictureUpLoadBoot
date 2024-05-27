//package com.platform.service.impl;
//
//
//import com.platform.dao.SysUserRoleDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysUserRole;
//import com.platform.service.SysUserRoleService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
///**
// * 指定此类是一个spring服务层
// * 并指定此服务层名称为sysUserRoleService
// * 以便控制层使用
// * @author zhuhaojie
// *
// */
//@Service(value="sysUserRoleService")
//public class SysUserRoleServiceImpl extends ServiceManager<SysUserRole,SysUserRoleDAO> implements SysUserRoleService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysUserRoleDAO")
//	@Override
//	public void setDao(SysUserRoleDAO dao) {
//		this.dao = dao;
//
//	}
//
//
//
//}
