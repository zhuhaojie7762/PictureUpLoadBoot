//package com.platform.task;
//
//
//import com.platform.service.OrderOriginService;
//import com.platform.service.WanLiNiuService;
//import com.platform.utils.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
//
///**
// * 定时获取接口数据和定时同步表任务
// *
// * @Author:zhuhaojie
// * @Date:16:10 2018/12/19
// */
////@Service
//@Component
//public class TimeTask {
//
//    private static final Logger logger = LoggerFactory.getLogger(TimeTask.class);
//
//    @Resource
//    private WanLiNiuService wanLiNiuService;
//    @Resource
//    private OrderOriginService orderOriginService;
//
//    /**
//     * 定时拉取万里牛数据
//     */
//    //每30秒执行一次
//    //@Scheduled(cron = "0/30 * *  * * ? ") 0 0/5 * * * ?
//    //每5分钟执行一次
//    //@Scheduled(cron = "0 0/5 * * * ?")
//     @Scheduled(cron = "0/30 * *  * * ? ")
//    public void pullData() {
//        logger.info("----------开始拉取数据----------");
//        try {
//            Date now = DateUtils.getNow();
//            //
//            Date maxEndTime = orderOriginService.getMaxEndTime();
//            wanLiNiuService.pullData(1, now, maxEndTime);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("----------拉取数据异常----------");
//            logger.error(e.toString());
//
//        }
//       logger.info("----------拉取数据结束----------");
//   }
//
//    /**
//     * 定时从表order_origin中解析数据
//     * 并插入或者更新到order_status_result表和 open_order表中
//     */
////    @Scheduled(cron = "0/5 * *  * * ? ")
//    @Scheduled(cron = "0/40 * *  * * ? ")
//    public void parseData() {
//        logger.info("----------开始解析数据----------");
//        try {
//            Date now = DateUtils.getNow();
//            //获取最小的开始时间
//            Date minBeginTime = orderOriginService.getMinBeginTime();
//            wanLiNiuService.parseData(1, now, minBeginTime);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("----------解析数据异常----------");
//            logger.error(e.toString());
//        }
//        logger.info("----------解析数据结束----------");
//    }
////
////    @Scheduled(cron = "0/5 * *  * * ? ") //每5秒执行一次
////    public void myTest3() {
////        System.out.println(Thread.currentThread().getName() + " " + "定时任务三：mytest3--------------------------开始");
////    }
//
//    public static void main(String[] args) {
//        char nextChar = 'm';
//        int k = nextChar;
//        //int s=(int)'0';
//        int refNum = k - '0';
//        if ((refNum < 0) || (refNum > 9)) {
//            System.out.println("错误");
//        } else {
//            System.out.println("成功");
//        }
//    }
//}
//
