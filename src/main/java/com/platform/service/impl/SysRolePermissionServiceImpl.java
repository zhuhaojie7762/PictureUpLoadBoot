//package com.platform.service.impl;
//
//
//import com.platform.dao.SysRolePermissionDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysRolePermission;
//import com.platform.service.SysRolePermissionService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
///**
// * 指定此类是一个spring服务层
// * 并指定此服务层名称为sysRolePermissionService
// * 以便控制层使用
// * @author zhuhaojie
// *
// */
//@Service(value="sysRolePermissionService")
//public class SysRolePermissionServiceImpl extends ServiceManager<SysRolePermission,SysRolePermissionDAO> implements SysRolePermissionService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysRolePermissionDAO")
//	@Override
//	public void setDao(SysRolePermissionDAO dao) {
//		this.dao = dao;
//
//	}
//
//
//
//}
