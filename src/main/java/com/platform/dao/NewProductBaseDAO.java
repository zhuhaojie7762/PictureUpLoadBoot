package com.platform.dao;

import com.github.pagehelper.PageInfo;
import com.platform.base.DaoManager;
import com.platform.entity.NewProductBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//extends DaoManager<NewProductBase>
public interface NewProductBaseDAO  {
    /**
     * 批量新增或者更新
     * <p>
     * *
     * tpTid
     *
     * @param list
     */
    void saveOrUpdateBatch(@Param(value = "newProductBaseList") List<NewProductBase> list);


    void updateBatch(@Param(value = "newProductBaseList") List<NewProductBase> list);

    List<NewProductBase> listByItemNo(@Param(value = "itemNoList")List<String> itemNoList);



    /**
     * 查询列表
     * @return List<E> 符合条件的指定对象集合
     */
    List<NewProductBase> list(NewProductBase newProductBase);

    /**
     * 2
     * 查询一条数据
     * @param e 任意对象
     * @return E 符合条件的单个对象
     */
    NewProductBase get(NewProductBase e);

    /**
     * 添加
     * 3
     * @param e 任意对象
     * @return int 保存后对数据库记录影响的条数
     */
    int save(NewProductBase e);

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
    int delete(NewProductBase e);

    /**
     * 修改
     *
     * @param e 任意对象
     * @return int 修改后对数据库记录影响条数
     */
    int update(NewProductBase e);

    int updateById(NewProductBase e);


    /**
     * 根据ID查询一条记录
     *
     * @param id
     * @return
     */
    NewProductBase selectById(String id);
}
