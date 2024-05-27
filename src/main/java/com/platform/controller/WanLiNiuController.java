//package com.platform.controller;
//
//
//import com.platform.entity.OrderStatusResult;
//import com.platform.entity.ResultVo;
//import com.platform.service.WanLiNiuService;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
//
///**
// * POST : _app=3623486051
// * &_s=&_sign=dc9cf30538541c75c1ebe92bab9b1a88&_t=1545111884&bill_code=XD181211000298&limit=20&page=1
// */
//@Controller
//public class WanLiNiuController {
//
//
//    private Logger logger = LoggerFactory.getLogger(WanLiNiuController.class);
//
//
//    @Resource
//    private WanLiNiuService wanLiNiuService;
//
//    /**
//     * 准确说是近90天以及历史未完成的单据
//     * application/x-www-form-urlencoded;charset=utf-8
//     * page=1&limit=20&createTime=2018-05-08
//     *
//     * @param billCode      单据编码
//     * @param createTime    单据创建时间,修改时间为空是使用 2018-11-17
//     * @param modifyTime    单据修改时间
//     * @param sendGoodsTime 发货时间，创建时间为空时使用
//     *                      注： bill_code,create_time,modify_time,send_goods_time 不能全为空
//     * @param page          Int	是	当前页码，从1开始
//     * @param limit         Int	是	每页大小,最大200
//     */
//    @RequestMapping("/getOrderStatus")
//    @ResponseBody
//    public ResultVo<OrderStatusResult> getOrderStatus(@RequestParam(value = "billCode", required = false) String billCode,
//                                                      @RequestParam(value = "createTime", required = false) Date createTime,
//                                                      @RequestParam(value = "modifyTime", required = false) Date modifyTime,
//                                                      @RequestParam(value = "sendGoodsTime", required = false) Date sendGoodsTime,
//                                                      @RequestParam(value = "page") Integer page,
//                                                      @RequestParam(value = "limit") Integer limit) {
//        ResultVo<OrderStatusResult> resultResultVo = new ResultVo<>();
//        try {
//            logger.info("开始发起订单状态查询........");
//            logger.info("订单状态查询入参:billCode:" + billCode + ",createTime:" + createTime + ",modifyTime:" + modifyTime + ",sendGoodsTime:" + sendGoodsTime + ",page:" + page + ",limit:" + limit);
//            String message;
//            if (StringUtils.isEmpty(billCode) && createTime == null && modifyTime == null && sendGoodsTime == null) {
//                message = "billCode,createTime,modifyTime,send_goods_time 不能全为空";
//                resultResultVo.setResult(false);
//                resultResultVo.setMessage(message);
//                return resultResultVo;
//            }
//            if (page == null) {
//                page = 1;
//            } else {
//                if (page <= 0) {
//                    message = "请求页面不能小于0";
//                    resultResultVo.setResult(false);
//                    resultResultVo.setMessage(message);
//                    return resultResultVo;
//                }
//            }
//            if (limit == null || limit <= 0) {
//                limit = 20;
//            } else {
//                if (limit > 200) {
//                    message = "每页最多显示200条记录";
//                    resultResultVo.setResult(false);
//                    resultResultVo.setMessage(message);
//                    return resultResultVo;
//                }
//            }
//            resultResultVo = wanLiNiuService.getStatus(billCode, createTime, modifyTime, sendGoodsTime, page, limit, resultResultVo);
//        } catch (Exception e) {
//            logger.info("订单状态查询异常........");
//            resultResultVo.setResult(false);
//            resultResultVo.setMessage("订单状态查询异常........");
//            e.printStackTrace();
//        }
//        return resultResultVo;
//    }
//
//    public static void main(String[] args){
//
//
////              String result="{\n" +
////                      "    \"code\":0,\n" +
////                      "    \"data\":[\n" +
////                      "        {\n" +
////                      "            \"shop_name\":\"BOB[K11]密斯特顾777\",\n" +
////                      "            \"shop_nick\":\"密斯特顾777\",\n" +
////                      "            \"storage_name\":\"WMS服装仓\",\n" +
////                      "            \"storage_code\":\"CK001\",\n" +
////                      "            \"trade_no\":\"XD181217020187\",\n" +
////                      "            \"oln_status\":4,\n" +
////                      "            \"buyer_account\":\"isabella121127\",\n" +
////                      "            \"buyer\":\"isabella121127\",\n" +
////                      "            \"receiver\":\"年华\",\n" +
////                      "            \"phone\":\"17346959686\",\n" +
////                      "            \"province\":\"湖南省\",\n" +
////                      "            \"city\":\"永州市\",\n" +
////                      "            \"district\":\"冷水滩区\",\n" +
////                      "            \"address\":\"梅湾街道滩区河东百业街401号 三楼\",\n" +
////                      "            \"zip\":\"425000\",\n" +
////                      "            \"create_time\":1545062391000,\n" +
////                      "            \"modify_time\":1545148802000,\n" +
////                      "            \"status\":4,\n" +
////                      "            \"is_pay\":false,\n" +
////                      "            \"orders\":[\n" +
////                      "                {\n" +
////                      "                    \"item_name\":\"羽绒服（浅蓝色）（LK）（降1-798）\",\n" +
////                      "                    \"sku_code\":\"B172f2589p337104\",\n" +
////                      "                    \"size\":1,\n" +
////                      "                    \"price\":698,\n" +
////                      "                    \"receivable\":698,\n" +
////                      "                    \"payment\":668,\n" +
////                      "                    \"tp_tid\":\"299681730733840058\",\n" +
////                      "                    \"oln_item_id\":\"B172f2762p3371\",\n" +
////                      "                    \"oln_status\":4,\n" +
////                      "                    \"oln_sku_id\":\"B172f2589p337104\",\n" +
////                      "                    \"status\":4,\n" +
////                      "                    \"order_id\":\"4759244D3FA23B3594FC86D1D072BEFD\",\n" +
////                      "                    \"oln_item_name\":\"颜色分类:蓝色[羽绒服];尺码:M\"\n" +
////                      "                }\n" +
////                      "            ],\n" +
////                      "            \"is_exception_trade\":false,\n" +
////                      "            \"tp_tid\":\"299681730733840058\",\n" +
////                      "            \"source_platform\":\"淘宝\",\n" +
////                      "            \"sum_sale\":668,\n" +
////                      "            \"post_fee\":12,\n" +
////                      "            \"paid_fee\":680,\n" +
////                      "            \"discount_fee\":0,\n" +
////                      "            \"service_fee\":0,\n" +
////                      "            \"has_refund\":0,\n" +
////                      "            \"oln_order_list\":[\n" +
////                      "                \"299681730733840058\"\n" +
////                      "            ],\n" +
////                      "            \"is_small_trade\":false,\n" +
////                      "            \"tel\":\"\",\n" +
////                      "            \"process_status\":10,\n" +
////                      "            \"logistic_code\":\"0017\"\n" +
////                      "        }\n" +
////                      "    ]\n" +
////                      "}";
////
////
////
////        InterfaceResult interfaceResult = JSON.parseObject(result,InterfaceResult.class);
////        if(interfaceResult!=null){
////           List<OrderStatusResult> list =interfaceResult.getData();
////           if(list!=null && list.size()>0){
////               System.out.println(list);
////           }
////        }
//        String s="$fsd$ds#";
//        s=s.replaceAll("#","");
//        s=s.replaceAll("\\$","");
//        System.out.println(s);
//    }
//}
