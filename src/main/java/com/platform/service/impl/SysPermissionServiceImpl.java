//package com.platform.service.impl;
//
//
//import com.platform.dao.SysPermissionDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysPermission;
//import com.platform.service.SysPermissionService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
///**
// * 指定此类是一个spring服务层
// * 并指定此服务层名称为sysMenuService
// * 以便控制层使用
// * @author zhuhaojie
// *
// */
//@Service(value="sysPermissionService")
//public class SysPermissionServiceImpl extends ServiceManager<SysPermission,SysPermissionDAO> implements SysPermissionService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysPermissionDAO")
//	@Override
//	public void setDao(SysPermissionDAO dao) {
//		this.dao = dao;
//
//	}
//}