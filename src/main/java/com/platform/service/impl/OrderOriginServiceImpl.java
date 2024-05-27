//package com.platform.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.platform.dao.OrderOriginDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.OrderOrigin;
//import com.platform.service.OrderOriginService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.List;
//
//@Service(value = "orderOriginService")
//public class OrderOriginServiceImpl extends ServiceManager<OrderOrigin, OrderOriginDAO> implements OrderOriginService {
//    @Resource(name = "orderOriginDAO")
//    @Override
//    public void setDao(OrderOriginDAO dao) {
//        this.dao = dao;
//    }
//
//    @Override
//    public void insertBatch(List<OrderOrigin> list) {
//        dao.insertBatch(list);
//    }
//
//    @Override
//    public Date getMaxEndTime() {
//        return dao.getMaxEndTime();
//    }
//
//    @Override
//    public Date getMinBeginTime() {
//        return dao.getMinBeginTime();
//    }
//
//    @Override
//    public PageInfo<OrderOrigin> selectListBeginPageInfo(int pageNum, int pageSize, OrderOrigin orderOrigin) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<OrderOrigin> list = dao.listBegin(orderOrigin);
//        PageInfo<OrderOrigin> pageInfo = new PageInfo<>(list);
//        pageInfo.setList(list);
//        return pageInfo;
//    }
//}
