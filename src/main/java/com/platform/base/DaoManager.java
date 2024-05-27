package com.platform.base;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作数据库的基础接口
 * @param <E> 任意Object类子类对象
 **@author：zhuhaojie
 *@time：2017年5月31日 上午10:31:47
 */
public interface DaoManager<E> {

	/**
	 * 查询列表
	 * 1
	 * @param e 任意对象
	 * @return List<E> 符合条件的指定对象集合
	 */
	List<E> list(E e);

	/**
	 * 2
	 * 查询一条数据
	 * @param e 任意对象
	 * @return E 符合条件的单个对象
	 */
	E get(E e);

	/**
	 * 添加
	 * 3
	 * @param e 任意对象
	 * @return int 保存后对数据库记录影响的条数
	 */
	int save(E e);

	/**
	 * 根据id删除
	 * 4
	 * @param id 主键
	 * @return int 删除后对数据库记录影响的条数
	 */
	int removeById(String id);
	 /**
	 * 删除
	 *
	 * @param e
	 * @return
	 */
    int delete(E e);

	/**
	 * 修改
	 *
	 * @param e 任意对象
	 * @return int 修改后对数据库记录影响条数
	 */
	int update(E e);

	int updateById(E e);


	/**
	 * 根据ID查询一条记录
	 *
	 * @param id
	 * @return
	 */
	 E selectById(String id);






    /**
	 * 对selectList方法做分页查询
	 *
	 * @param e 任意对象
	 * @param pageNum 请求哪一页
	 * @param pageSize 每一页显示多少行
	 * @return
	 */
	 PageInfo<E> selectListPage(E e, int pageNum, int pageSize);
}
