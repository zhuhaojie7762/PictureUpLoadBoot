//package com.platform.service;
//
//import com.github.pagehelper.PageInfo;
//import com.platform.base.DaoManager;
//import com.platform.entity.OrderOrigin;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.Date;
//import java.util.List;
//
//public interface OrderOriginService extends DaoManager<OrderOrigin> {
//
//    /**
//     * 批量新增
//     * @Author:zhuhaojie
//     * @Date:15:42 2018/12/19
//     */
//    void insertBatch(@Param(value = "OrderOriginList") List<OrderOrigin> list);
//    /**
//     * 获取最大的查询结束时间
//     * @Author:zhuhaojie
//     * @Date:9:46 2018/12/20
//     */
//    Date getMaxEndTime();
//    /**
//     * 解析使用获取最小的查询开始时间
//     *
//     * @Author:zhuhaojie
//     * @Date:13:11 2018/12/20
//     */
//    Date getMinBeginTime();
//    /**
//     * 按照请求开始时间为条件
//     * 分页查询数据
//    * @Author:zhuhaojie
//    * @Date:13:33 2018/12/20
//    */
//    PageInfo<OrderOrigin> selectListBeginPageInfo(int page,int limit,OrderOrigin orderOrigin);
//}
