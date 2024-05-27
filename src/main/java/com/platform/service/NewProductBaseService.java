package com.platform.service;

import com.github.pagehelper.PageInfo;
import com.platform.base.DaoManager;
import com.platform.entity.NewProductBase;
import com.platform.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//extends DaoManager<NewProductBase>
public interface NewProductBaseService  {

    /**
     * 批量新增或者更新
     * <p>
     * *
     * tpTid
     * @param list
     */
    void saveOrUpdateBatch(@Param(value = "OpenOrderList") List<NewProductBase> list);

    void updateBatch(@Param(value = "newProductBaseList") List<NewProductBase> list);

    /**
     * 根据货号查询对象
     * key为货号
     * value为实体
     * 如果查询到多条只放一个实体
    * @Author:zhuhaojie
    * @Date:17:53 2018/12/29
    */
    Map<String,NewProductBase> getNewProductBaseByItemNo(List<String> itemNoList);
    /**
    * @Author:zhuhaojie
    * @Date:19:09 2018/12/29
    */
    List<NewProductBase> listByItemNo(List<String> itemNoList);

    PageInfo<NewProductBase> selectListPage(NewProductBase e, int pageNum, int pageSize);
}
