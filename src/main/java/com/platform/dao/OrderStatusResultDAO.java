//package com.platform.dao;
//
//
//import com.platform.base.DaoManager;
//import com.platform.entity.OrderStatusResult;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
///**
// * 订单结果主表
//* @Author:zhuhaojie
//* @Date:13:55 2018/12/20
//*/
//public interface OrderStatusResultDAO  extends DaoManager<OrderStatusResult> {
//
//    /**
//     * 批量新增或者更新
//     * 根据fd_tp_id和fd_trade_no的组合唯一
//     *
//     * @Author:zhuhaojie
//     * @Date:15:42 2018/12/19
//     */
//    void insertBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//    /**
//     * 批量修改
//     * @param list
//     */
//    void updateBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//
//
//    void saveOrUpdateBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//    /**
//     * 批量新增或者修改详情表和总表
//     * @param map
//     */
//    //void saveOrUpdateResultAndDetailBatch(@Param(value = "map") Map<String,Object> map);
//}
