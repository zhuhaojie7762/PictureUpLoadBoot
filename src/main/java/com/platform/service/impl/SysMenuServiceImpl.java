//package com.platform.service.impl;
//
//
//import com.platform.dao.SysMenuDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.SysMenu;
//import com.platform.service.SysMenuService;
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
//@Service(value="sysMenuService")
//public class SysMenuServiceImpl extends ServiceManager<SysMenu,SysMenuDAO> implements SysMenuService {
//
//	/**
//	 * 这里根据名称查找
//	 * 将组件实现好的DAO层对象注入
//	 */
//	@Resource(name="sysMenuDAO")
//	@Override
//	public void setDao(SysMenuDAO dao) {
//		this.dao = dao;
//
//	}
//
//
//
//}
