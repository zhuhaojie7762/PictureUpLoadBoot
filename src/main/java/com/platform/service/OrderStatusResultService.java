//package com.platform.service;
//
//import com.platform.base.DaoManager;
//import com.platform.entity.OrderStatusResult;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface OrderStatusResultService extends DaoManager<OrderStatusResult> {
//    /**
//     * 批量新增
//     *
//     * @Author:zhuhaojie
//     * @Date:15:42 2018/12/19
//     */
//    void insertBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//    /**
//     * 批量修改
//     *
//     * @param list
//     */
//    void updateBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//    /**
//     * 批量新增或者更新
//     * <p>
//     * * 线上单号,如果是线下订单，则是万里牛的单号，合单情况下会将单号合并，使用|做分隔符
//     * 订单编码和订单号
//     * tpTid
//     * tradeNo
//     *
//     * @param list
//     */
//    void saveOrUpdateBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//
//
//
//    //void saveOrUpdateReaultAndDetailBatch(Map<String,Object> map);
////    void saveOrUpdateBatch(@Param(value = "OrderStatusList") List<OrderStatusResult> list);
//}
