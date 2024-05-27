package com.platform.service;

import com.github.pagehelper.PageInfo;
import com.platform.base.DaoManager;
import com.platform.entity.SysLog;

import java.util.List;

/**
 * 系统日志接口
 * @author zhuhaojie
 *
 */
//extends DaoManager<SysLog>
public interface SysLogService {
    /**
     * 查询列表
     * 1
     * @param e 任意对象
     * @return List<E> 符合条件的指定对象集合
     */
    List<SysLog> list(SysLog e);

    /**
     * 2
     * 查询一条数据
     * @param e 任意对象
     * @return E 符合条件的单个对象
     */
    SysLog get(SysLog e);

    /**
     * 添加
     * 3
     * @param e 任意对象
     * @return int 保存后对数据库记录影响的条数
     */
    int save(SysLog e);

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
    int delete(SysLog e);

    /**
     * 修改
     *
     * @param e 任意对象
     * @return int 修改后对数据库记录影响条数
     */
    int update(SysLog e);

    int updateById(SysLog e);


    /**
     * 根据ID查询一条记录
     *
     * @param id
     * @return
     */
    SysLog selectById(String id);






    /**
     * 对selectList方法做分页查询
     *
     * @param e 任意对象
     * @param pageNum 请求哪一页
     * @param pageSize 每一页显示多少行
     * @return
     */
    PageInfo<SysLog> selectListPage(SysLog e, int pageNum, int pageSize);
}
