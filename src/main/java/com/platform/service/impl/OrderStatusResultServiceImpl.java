//package com.platform.service.impl;
//
//
//import com.platform.dao.OrderStatusResultDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.OrderStatusResult;
//import com.platform.service.OrderStatusResultService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//
//@Service(value = "orderStatusResultService")
//public class OrderStatusResultServiceImpl extends ServiceManager<OrderStatusResult, OrderStatusResultDAO> implements OrderStatusResultService {
//
//    @Resource(name = "orderStatusResultDAO")
//    @Override
//    public void setDao(OrderStatusResultDAO orderStatusResultDAO) {
//        this.dao = orderStatusResultDAO;
//    }
//
//
//
//    @Override
//    public void updateBatch(List<OrderStatusResult> list) {
//        dao.updateBatch(list);
//    }
//
//    @Override
//    public void insertBatch(List<OrderStatusResult> list) {
//        dao.insertBatch(list);
//    }
//
//    @Override
//    public void saveOrUpdateBatch(List<OrderStatusResult> list) {
//       dao.saveOrUpdateBatch(list);
//    }
//
////    @Override
////    public void saveOrUpdateReaultAndDetailBatch(Map<String,Object> map) {
////        dao.saveOrUpdateResultAndDetailBatch(map);
////    }
//}
