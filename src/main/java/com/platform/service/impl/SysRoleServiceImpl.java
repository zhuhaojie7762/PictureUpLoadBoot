//package com.platform.service.impl;
//
//
//import com.platform.dao.SysRoleDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysRole;
//import com.platform.service.SysRoleService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
///**
// * 指定此类是一个spring服务层
// * 并指定此服务层名称为sysRoleService
// * 以便控制层使用
// * @author zhuhaojie
// *
// */
//@Service(value="sysRoleService")
//public class SysRoleServiceImpl extends ServiceManager<SysRole,SysRoleDAO> implements SysRoleService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysRoleDAO")
//	@Override
//	public void setDao(SysRoleDAO dao) {
//		this.dao = dao;
//
//	}
//
//
//
//}
