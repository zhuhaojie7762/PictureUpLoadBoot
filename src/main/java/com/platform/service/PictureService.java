package com.platform.service;



import com.github.pagehelper.PageInfo;
import com.platform.base.DaoManager;
import com.platform.entity.Pictures;

import java.util.List;

/**
 * 接口中可以定义特殊方法
 * 公共方法都在DaoManager接口中定义
 * 并由扫描组件类实现
 * @author zhuhaojie
 *
 */
public interface PictureService {
    /**
     * 查询列表
     * 1
     * @param e 任意对象
     * @return List<E> 符合条件的指定对象集合
     */
    List<Pictures> list(Pictures e);

    /**
     * 2
     * 查询一条数据
     * @param e 任意对象
     * @return E 符合条件的单个对象
     */
    Pictures get(Pictures e);

    /**
     * 添加
     * 3
     * @param e 任意对象
     * @return int 保存后对数据库记录影响的条数
     */
    int save(Pictures e);

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
    int delete(Pictures e);

    /**
     * 修改
     *
     * @param e 任意对象
     * @return int 修改后对数据库记录影响条数
     */
    int update(Pictures e);

    int updateById(Pictures e);


    /**
     * 根据ID查询一条记录
     *
     * @param id
     * @return
     */
    Pictures selectById(String id);






    /**
     * 对selectList方法做分页查询
     *
     * @param e 任意对象
     * @param pageNum 请求哪一页
     * @param pageSize 每一页显示多少行
     * @return
     */
    PageInfo<Pictures> selectListPage(Pictures e, int pageNum, int pageSize);
}
