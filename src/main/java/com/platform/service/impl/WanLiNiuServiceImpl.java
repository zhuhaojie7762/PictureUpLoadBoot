//package com.platform.service.impl;
//
//
//import com.alibaba.fastjson.JSON;
//import com.github.pagehelper.PageInfo;
//import com.platform.entity.*;
//import com.platform.service.OpenOrderService;
//import com.platform.service.OrderOriginService;
//import com.platform.service.OrderStatusResultService;
//import com.platform.service.WanLiNiuService;
//import com.platform.utils.DateUtils;
//import com.platform.utils.ListUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * 万里牛接口服务类
// * ; SQL []; Packet for query is too large (78193968 > 67108864).
// * You can change this value on the server by setting the max_allowed_packet' variable.;
// * nested exception is com.mysql.jdbc.PacketTooBigException: Packet for query is too large (78193968 > 67108864).
// * You can change this value on the server by setting the max_allowed_packet' variable.
// *
// * @Author:zhuhaojie
// * @Date:10:21 2018/12/12
// */
//@Service(value = "wanLiNiuService")
//public class WanLiNiuServiceImpl implements WanLiNiuService {
//
//    private Logger logger = LoggerFactory.getLogger(WanLiNiuServiceImpl.class);
//    /**
//     * 批量提交最多记录数
//     * 67000000
//     */
//    //private static int MAX = 500;
//    @Value("#{properties['max']}")
//    private int MAX;
//    /**
//     * 每次最多查询多少页数据
//     */
//    // private static int LIMIT = 200;
//    @Value("#{properties['limit']}")
//    private int LIMIT;
//
//    @Value("#{properties['publicId']}")
//    private int USER_ID;
//    /**
//     * 秘钥
//     */
//    @Value("#{properties['formalSecret']}")
//    private String secret;
//
//    /**
//     * key
//     */
//    @Value("#{properties['formalKey']}")
//    private String formalKey;
//    /**
//     * 前置url
//     */
//    @Value("#{properties['url']}")
//    private String url;
//
//    /**
//     * 订单状态url
//     */
//    @Value("#{properties['orderStatusUrl']}")
//    private String orderStatusUrl;
//    /**
//     * 批量查询url
//     */
//    @Value("#{properties['batchQueryUrl']}")
//    private String batchQueryUrl;
//
//
//    @Resource
//    private OrderOriginService orderOriginService;
//
//    @Resource
//    private OrderStatusResultService orderStatusResultService;
//
//
//    @Resource
//    private OpenOrderService openOrderService;
//
//    /**
//     * 查询订单状态
//     *
//     * @param billCode      单据编码
//     * @param createTime    单据创建时间,修改时间为空是使用
//     * @param modifyTime    单据修改时间
//     * @param sendGoodsTime 发货时间，创建时间为空时使用
//     *                      注： bill_code,create_time,modify_time,send_goods_time 不能全为空
//     * @param page          Int	是	当前页码，从1开始
//     * @param limit         Int	是	每页大小,最大200
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public ResultVo<OrderStatusResult> getStatus(String billCode, Date createTime,
//                                                 Date modifyTime, Date sendGoodsTime, Integer page, Integer limit, ResultVo<OrderStatusResult> resultResultVo) throws Exception {
//
//        List<OrderStatusResult> list;
//        String result = getInterfaceResult(billCode, createTime, modifyTime, sendGoodsTime, page, limit);
//        InterfaceResult interfaceResult = JSON.parseObject(result, InterfaceResult.class);
//        if (interfaceResult != null) {
//            list = interfaceResult.getData();
//            if (list != null) {
//
//                for(OrderStatusResult orderStatusResult:list){
//                   List<OpenOrder> list1 = orderStatusResult.getOrders();
//                   if(list1!=null && list1.size()>0){
//                      for(OpenOrder openOrder:list1){
//                          openOrder.setTradeNo(orderStatusResult.getTradeNo());
//                      }
//                   }
//                }
//                resultResultVo.setMessage("查询成功");
//                resultResultVo.setData(list);
//                resultResultVo.setResult(true);
//            } else {
//                resultResultVo.setMessage(interfaceResult.getMessage());
//                resultResultVo.setResult(false);
//            }
//        } else {
//            resultResultVo.setMessage("json解析后对象为null");
//            resultResultVo.setResult(false);
//        }
//        return resultResultVo;
//    }
//
//    @Override
//    public String getData(String billCode, Date createTime, Date modifyTime, Date sendGoodsTime, Integer page, Integer limit) throws Exception {
//        return getInterfaceResult(billCode, createTime, modifyTime, sendGoodsTime, page, limit);
//    }
//
//    private String getInterfaceResult(String billCode, Date createTime, Date modifyTime, Date sendGoodsTime, Integer page, Integer limit) throws Exception {
//        //业务参数
//        Map<String, Object> ps = new HashMap();
//        ps.put("page", page);
//        ps.put("limit", limit + "");
//        if (StringUtils.isNotEmpty(billCode)) {
//            ps.put("bill_code", billCode);
//        }
//        if (createTime != null) {
//            ps.put("create_time", createTime.getTime());
//        }
//        if (modifyTime != null) {
//            ps.put("modify_time", modifyTime.getTime());
//        }
//        if (sendGoodsTime != null) {
//            ps.put("send_goods_time", sendGoodsTime.getTime());
//        }
////        HopiRequest req = new HopiRequest(url, formalKey, secret, "");
////        String result = req.request(orderStatusUrl, ps);
//        return null;
//    }
//
//
//    @Override
//    public void pullData(int page, Date now, Date maxEndTime) throws Exception {
//        if (maxEndTime == null) {
//            maxEndTime = new Date(DateUtils.getStartTime());
//        }
//        String content = getData(null, null, maxEndTime, null, page, LIMIT);
//        String strBegin = DateUtils.dateToStr(maxEndTime, "yyyy-MM-dd HH:mm:ss");
//        String strEnd = DateUtils.dateToStr(now, "yyyy-MM-dd HH:mm:ss");
//        if (StringUtils.isEmpty(content)) {
//            logger.error("查询时间段为:beginTime:" + strBegin + ",endTime:" + strEnd + ",解析后结果为空");
//        }
//        logger.info("content:" + content);
//        content = content.trim();
//        content = content.replaceAll("#", "");
//        content = content.replaceAll("\\$", "");
//        //content = StringUtil.makeQueryStringAllRegExp(content);
//        logger.info("将数据保存到数据库:-------------");
//        OrderOrigin orderOrigin = new OrderOrigin();
//        orderOrigin.setUserId(USER_ID);
//        orderOrigin.setContent(content);
//        orderOrigin.setBeginTime(maxEndTime);
//        orderOrigin.setEndTime(now);
//        logger.info("-----------orderOrigin:---------" + orderOrigin);
//        orderOriginService.save(orderOrigin);
//        logger.info("--------数据保存完成----------");
//        logger.info("将内容解析为InterfaceResult对象:-------------");
//        logger.info("content:");
//        logger.info(content);
//        InterfaceResult interfaceResult = JSON.parseObject(content, InterfaceResult.class);
//        if (interfaceResult == null) {
//            logger.error("查询时间段为:beginTime:" + strBegin + ",endTime:" + strEnd + ",解析成interfaceResult对象为null");
//        }
//        logger.info("解析成InterfaceResult内容:" + interfaceResult);
//        List<OrderStatusResult> list = interfaceResult.getData();
//        if (list == null) {
//            logger.error("解析成List<OrderStatusResult>为空");
//            return;
//        }
//        logger.info("转化成List<OrderStatusResult>内容:" + interfaceResult);
//        int size = list.size();
//        System.out.println();
//        //如果小于200说明到头了
//        if (size < LIMIT) {
//            logger.info("--------当前订单拉取完成----------");
//        } else {
//            //继续请求
//            logger.info("--------循环拉取订单 ----------page:" + (page + 1));
//            pullData(page + 1, now, maxEndTime);
//        }
//    }
//
//
//    @Override
//    public void parseData(int page, Date now, Date minBeginTime) {
//        logger.info("---------------开始解析数据------------------");
//        if (minBeginTime == null || minBeginTime.getTime() > now.getTime()) {
//            logger.info("-------minBeginTime为null或者minBeginTime大于当前时间，解析中止------------------");
//            return;
//        }
//
//        OrderOrigin orderOrigin = new OrderOrigin();
//        orderOrigin.setBeginTimeStart(minBeginTime);
//        orderOrigin.setBeginTimeEnd(now);
//        logger.info("---------------开始以分页方式查询数据 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//        PageInfo<OrderOrigin> pageInfo = orderOriginService.selectListBeginPageInfo(page, LIMIT, orderOrigin);
//        logger.info("---------------数据查询完成 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//        logger.info("-------开始取出List<OrderOrigin>--------");
//        List<OrderOrigin> list = pageInfo.getList();
//        logger.info("-------取出List<OrderOrigin>完成,开始解析成List<OrderStatusResult>和List<OpenOrder>--------");
//        List<Object> list1 = parseResult(list);
//        logger.info("-------解析List<OrderStatusResult>和List<OpenOrder>完成--------");
//        if (list1 != null && list1.size() == 2) {
//            List<OrderStatusResult> listOrderStatus = (List<OrderStatusResult>) list1.get(0);
//            List<OpenOrder> listOpenOrder = (List<OpenOrder>) list1.get(1);
//            logger.info("-------开始批量插入--------");
//            int size = listOpenOrder.size();//详情表大小
//            int sizeTotal = listOrderStatus.size();//总表大小
//            /**
//             * 如果详情表大于总表
//             * 返回详情表数据
//             * 否则返回总表数据
//             */
//            int checkSize = size > sizeTotal ? size : sizeTotal;
//            if (checkSize < MAX) {
//                orderStatusResultService.saveOrUpdateBatch(listOrderStatus);
//                openOrderService.saveOrUpdateBatch(listOpenOrder);
//                logger.info("-------批量插入或者更新总计和详情完成--------");
//            }
//
//            List<List<OrderStatusResult>> separateOrderStatusResultList = ListUtils.fixedGrouping(listOrderStatus, MAX);
//            List<List<OpenOrder>> separateOpenOrderList = ListUtils.fixedGrouping(listOpenOrder, MAX);
//            //订单总表
//            int separateOrderStatusSize = separateOrderStatusResultList.size();
//            //订单详情表
//            int separateOpenOrderSize = separateOpenOrderList.size();
//            //返回大的
//            int checkSepareSize = separateOrderStatusSize > separateOpenOrderSize ? separateOrderStatusSize : separateOpenOrderSize;
//            /**
//             * 总表默认不大于详情表
//             */
//            boolean orderStatusSeparateBigger = false;
//            /**
//             * 如果比较的大小等于总表
//             */
//            if (checkSepareSize == separateOrderStatusSize) {
//                /**
//                 * 说明总表更大一些
//                 */
//                orderStatusSeparateBigger = true;
//            }
//
//
//            if (separateOrderStatusResultList != null && separateOrderStatusResultList.size() > 0 && separateOpenOrderList != null && separateOpenOrderList.size() > 0) {
//                if (orderStatusSeparateBigger) {
//                    //用小的循环          separateOrderStatusSize
//                    for (int i = 0; i < separateOpenOrderSize; i++) {
//                        logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                        logger.info("-------循环批量插入总表数据开始1--------i:" + i);
//                        orderStatusResultService.saveOrUpdateBatch(separateOrderStatusResultList.get(i));
//                        logger.info("-------循环批量插入总表数据结束1--------i:" + i);
//                        logger.info("-------循环批量插入详情数据开始1--------i:" + i);
//                        openOrderService.saveOrUpdateBatch(separateOpenOrderList.get(i));
//                        logger.info("-------循环批量插入详情数据结束1--------i:" + i);
//                        logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                    }
//                    List<List<OrderStatusResult>> shengyu = separateOrderStatusResultList.subList(separateOpenOrderSize, separateOrderStatusSize);
//                    if (shengyu != null && shengyu.size() > 0) {
//                        logger.info("-------循环批量插入剩余总表数据开始--------");
//                        for (List<OrderStatusResult> list2 : shengyu) {
//                            logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                            orderStatusResultService.saveOrUpdateBatch(list2);
//                        }
//                        logger.info("-------循环批量插入剩余总表数据完成--------");
//                    }
//                } else {
//                    //用小的循环  separateOpenOrderSize
//                    for (int i = 0; i < separateOrderStatusSize; i++) {
//                        logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                        logger.info("-------循环批量插入总表数据开始2--------i:" + i);
//                        orderStatusResultService.saveOrUpdateBatch(separateOrderStatusResultList.get(i));
//                        logger.info("-------循环批量插入总表数据结束2--------i:" + i);
//                        logger.info("-------循环批量插入详情数据开始2--------i:" + i);
//                        openOrderService.saveOrUpdateBatch(separateOpenOrderList.get(i));
//                        logger.info("-------循环批量插入详情数据结束2--------i:" + i);
//                    }
//                    List<List<OpenOrder>> shengyu = separateOpenOrderList.subList(separateOrderStatusSize, separateOpenOrderSize);
//                    if (shengyu != null && shengyu.size() > 0) {
//                        logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                        logger.info("-------循环批量插入剩余详情数据开始--------");
//                        for (List<OpenOrder> list2 : shengyu) {
//                            logger.info("---------------数据查询条件 ------------------page:" + page + ",limit:" + LIMIT + ",orderOrigin" + orderOrigin);
//                            openOrderService.saveOrUpdateBatch(list2);
//                        }
//                        logger.info("-------循环批量插入剩余详情数据完成--------");
//                    }
//                }
//            }
//        }
//        logger.info("-------第一次分页数据解析插入完成--------");
//        long total = pageInfo.getTotal();
//        if (total > LIMIT) {
//            logger.info("-------开始下一页数据请求--------");
//            parseData(page + 1, now, minBeginTime);
//        }
//        logger.info("-------数据解析保存完成--------");
//    }
//
//    private List<Object> parseResult(List<OrderOrigin> list) {
//        if (list != null && list.size() > 0) {
//            List<Object> finalResult = new ArrayList<>(2);
//            List<OrderStatusResult> totalList = new ArrayList<>();
//            List<OpenOrder> totalOpenList = new ArrayList<>();
//            for (OrderOrigin orderOrigin1 : list) {
//                String result = orderOrigin1.getContent();
//                Integer userId = orderOrigin1.getUserId();
//                if (StringUtils.isNotEmpty(result)) {
//                    InterfaceResult interfaceResult = JSON.parseObject(result, InterfaceResult.class);
//                    if (interfaceResult != null) {
//                        List<OrderStatusResult> listOrderList = interfaceResult.getData();
//                        if (listOrderList != null && listOrderList.size() > 0) {
//                            for (OrderStatusResult orderStatusResult : listOrderList) {
//                                List<String> list1 = orderStatusResult.getOlnOrderList();
//                                if (list1 != null) {
//                                    orderStatusResult.setOlnOrderStr(list1.toString());
//                                }
//                                orderStatusResult.setUserId(userId);
//                                List<OpenOrder> openOrderList = orderStatusResult.getOrders();
//                                if (openOrderList != null && openOrderList.size() > 0) {
//                                    for (OpenOrder openOrder : openOrderList) {
//                                        openOrder.setUserId(userId);
//                                        openOrder.setTradeNo(orderStatusResult.getTradeNo());
//                                    }
//                                    totalOpenList.addAll(openOrderList);
//                                }
//                            }
//                            totalList.addAll(listOrderList);
//                        }
//                    }
//                }
//            }
//            finalResult.add(0, totalList);
//            finalResult.add(1, totalOpenList);
//            return finalResult;
//        }
//        return null;
//    }
//
//
////    private Map<List<OrderStatusResult>,List<OpenOrder>> parseResult(List<OrderOrigin> list) {
////        if (list != null && list.size() > 0) {
////            List<Object> finalResult = new ArrayList<>(2);
////            List<OrderStatusResult> totalList = new ArrayList<>();
////            Map<List<OrderStatusResult>,List<OpenOrder>> totalOpenList = new ArrayList<>();
////            for (OrderOrigin orderOrigin1 : list) {
////                String result = orderOrigin1.getContent();
////                if (StringUtils.isNotEmpty(result)) {
////                    InterfaceResult interfaceResult = JSON.parseObject(result, InterfaceResult.class);
////                    if (interfaceResult != null) {
////                        List<OrderStatusResult> listOrderList = interfaceResult.getData();
////                        if (listOrderList != null && listOrderList.size() > 0) {
////                            for (OrderStatusResult orderStatusResult : listOrderList) {
////                                List<String> list1 = orderStatusResult.getOlnOrderList();
////                                if (list1 != null) {
////                                    orderStatusResult.setOlnOrderStr(list1.toString());
////                                }
////                                List<OpenOrder> openOrderList = orderStatusResult.getOrders();
////                                if (openOrderList != null && openOrderList.size() > 0) {
////                                    totalOpenList.addAll(openOrderList);
////                                }
////                            }
////                            totalList.addAll(listOrderList);
////                        }
////                    }
////                }
////            }
////            finalResult.add(0, totalList);
////            finalResult.add(1, totalOpenList);
////            return finalResult;
////        }
////        return null;
////    }
//
//}
