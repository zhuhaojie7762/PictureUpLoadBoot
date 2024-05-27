//package com.platform.service;
//
//import com.platform.entity.OrderStatusResult;
//import com.platform.entity.ResultVo;
//
//import java.util.Date;
//
//public interface WanLiNiuService {
//    ResultVo<OrderStatusResult> getStatus(String billCode, Date createTime,
//                                          Date modifyTime, Date sendGoodsTime, Integer page, Integer limit, ResultVo<OrderStatusResult> resultResultVo) throws Exception;
//
//    /**
//     * @param billCode      单据编码
//     * @param createTime    单据创建时间,修改时间为空是使用
//     * @param modifyTime    单据修改时间  传递这个返回 这段时间（传递的时间到现在）里面有过修改的单子和新增过的单子
//     * @param sendGoodsTime 发货时间，创建时间为空时使用
//     * @param page          当前页码，从1开始
//     * @param limit         每页大小,最大200
//     *                      bill_code,create_time,modify_time,send_goods_time 不能全为空
//     * @Author:zhuhaojie
//     * @Date:11:08 2018/12/20
//     */
//    String getData(String billCode, Date createTime,
//                   Date modifyTime, Date sendGoodsTime, Integer page, Integer limit) throws Exception;
//
//    /**
//     * 拉取万里牛订单接口原始数据
//     *
//     * @param page       请求哪一页
//     * @param now        系统当前时间
//     * @param maxEndTime 上一次解析的结束时间
//     * @Author:zhuhaojie
//     * @Date:13:16 2018/12/20
//     */
//    void pullData(int page, Date now, Date maxEndTime) throws Exception;
//
//    /**
//     * 从原始数据表中解析数据到目标表
//     *
//     * @param page         请求哪一页
//     * @param now          系统当前时间
//     * @param minBeginTime 原始表中解析开始的最小时间
//     * @Author:zhuhaojie
//     * @Date:13:54 2018/12/22
//     */
//    void parseData(int page, Date now, Date minBeginTime) throws Exception;
//}
