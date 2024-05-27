package com.platform.dao;

import com.platform.base.DaoManager;
import com.platform.entity.Pictures;
import com.platform.entity.Pictures;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 图片表操作DAO层
 * @author zhuhaojie
 *
 */
//extends DaoManager<Pictures>
@Mapper
public interface PicturesDAO  {
    /**
     * 查询列表
     * @return List<E> 符合条件的指定对象集合
     */
    List<Pictures> list(Pictures Pictures);

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
}
