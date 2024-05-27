//package com.platform.service.impl;
//
//
//import com.platform.dao.OpenOrderDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.OpenOrder;
//import com.platform.service.OpenOrderService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service(value = "openOrderService")
//public class OpenOrderServiceImpl extends ServiceManager<OpenOrder, OpenOrderDAO> implements OpenOrderService {
//    @Resource(name = "openOrderDAO")
//    @Override
//    public void setDao(OpenOrderDAO openOrderDAO) {
//        this.dao = openOrderDAO;
//    }
//
//    @Override
//    public void saveOrUpdateBatch(List<OpenOrder> list) {
//        dao.saveOrUpdateBatch(list);
//    }
//}
