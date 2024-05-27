package com.platform.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.base.DaoManager;

import java.util.List;
/**
 * 
 * @author zhuhaojie
 *
 * @param <E> 泛型E 代表任意对象
 * @param <DAO> 泛型DAO 代表DaoManager的任意实现类
 */
public abstract class ServiceManager<E, DAO extends DaoManager<E>> implements DaoManager<E> {

	
	
	/**
	 * DaoManager类的子类
	 */
	protected DAO dao;

	/**
	 * 获取此成员变量值
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月15日上午11:01:12
	 * @return DAO
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * 设置此成员变量值
	 * 这里设置为抽象的因为子类可以设置自己的dao
	 * @author zhuhaojie
	 * @time 2016年7月15日上午11:01:35
	 * @param dao
	 *            参数
	 */
	public abstract void setDao(DAO dao);

	/**
	 * 向数据库插入此对象代表的一条记录
	 * @author zhuhaojie
	 * @time 2016年7月15日上午11:18:26
	 * @param e
	 *            准备插入数据表中的对象
	 * @return int 返回插入后数据库受影响的行数
	 */
	public int save(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.save(e);
	}
	
	public List<E> list(E e){
		return dao.list(e);
	}
	
	public E get(E e){
		return dao.get(e);
	}

	/**
	 * 删除此对象代表的数据库记录
	 * 
	 * @param e
	 *            :准备删除的对象
	 * @return int
	 */
	public int delete(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.delete(e);
	}
	
	
	public int updateById(E e){
		return dao.updateById(e);
	}
	@Override
	public int removeById(String id) {
		
		return dao.removeById(id);
	}



	/**
	 * 修改
	 * 
	 * @param e
	 *            准备要插入的对象
	 * @return int 插入后受影响的行数
	 */
	public int update(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.update(e);
	}


	public PageInfo<E> selectListPage(E e, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<E> list = dao.list(e);
		PageInfo<E> pageInfo = new PageInfo<E>(list);
		pageInfo.setList(list);
		return pageInfo;
	}


	
	
	public PageInfo<E> selectListPage(DAO newDAO,E e, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<E> list = newDAO.list(e);
		PageInfo<E> pageInfo = new PageInfo<E>(list);
		pageInfo.setList(list);
		return pageInfo;
	}


    /**
     * 根据主键查询记录
     * @author zhuhaojie
     * @time 2016年12月30日 下午3:33:42
     * @param id 主键值
     */
	@Override
	public E selectById(String id) {
		return dao.selectById(id);
	}
}
