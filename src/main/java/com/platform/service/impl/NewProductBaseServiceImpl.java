//package com.platform.service.impl;
//
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.platform.dao.NewProductBaseDAO;
//import com.platform.base.impl.ServiceManager;
//import com.platform.entity.NewProductBase;
//import com.platform.service.NewProductBaseService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service(value = "newProductBaseService")
//public class NewProductBaseServiceImpl extends ServiceManager<NewProductBase, NewProductBaseDAO> implements NewProductBaseService {
//    @Resource(name = "newProductBaseDAO")
//    @Override
//    public void setDao(NewProductBaseDAO newProductBaseDAO) {
//        this.dao = newProductBaseDAO;
//    }
//
//    @Override
//    public void saveOrUpdateBatch(List<NewProductBase> list) {
//        dao.saveOrUpdateBatch(list);
//    }
//
//    @Override
//    public void updateBatch(List<NewProductBase> list) {
//        dao.updateBatch(list);
//    }
//
//    @Override
//    public Map<String, NewProductBase> getNewProductBaseByItemNo(List<String> list) {
//        Map<String, NewProductBase> map = new HashMap<>();
//        List<NewProductBase> newProductBaseList = dao.listByItemNo(list);
//        if (newProductBaseList != null && newProductBaseList.size() > 0) {
//            for (NewProductBase newProductBase : newProductBaseList) {
//                String itemNo = newProductBase.getItemNo();
//                if (!map.containsKey(itemNo)) {
//                    map.put(itemNo, newProductBase);
//                }
//            }
//        }
//        return map;
//    }
//
//    @Override
//    public List<NewProductBase> listByItemNo(List<String> itemNoList) {
//        return dao.listByItemNo(itemNoList);
//    }
//
//    @Override
//    public PageInfo<NewProductBase> selectListPage(NewProductBase newProductBase, int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<NewProductBase> list = dao.list(newProductBase);
//        PageInfo<NewProductBase> pageInfo = new PageInfo<>(list);
//        pageInfo.setList(list);
//        return pageInfo;
//    }
//}
