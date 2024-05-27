package com.platform.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.dao.SysLogDAO;
import com.platform.base.impl.ServiceManager;
import com.platform.entity.SysLog;
import com.platform.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//extends ServiceManager<SysLog,SysLogDAO>
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogDAO sysLogDAO;

    @Override
    public List<SysLog> list(SysLog e) {
        return sysLogDAO.list(e);
    }

    @Override
    public SysLog get(SysLog e) {
        return sysLogDAO.get(e);
    }

    @Override
    public int save(SysLog e) {
        return sysLogDAO.save(e);
    }

    @Override
    public int removeById(String id) {
        return sysLogDAO.removeById(id);
    }

    @Override
    public int delete(SysLog e) {
        return sysLogDAO.delete(e);
    }

    @Override
    public int update(SysLog e) {
        return sysLogDAO.update(e);
    }

    @Override
    public int updateById(SysLog e) {
        return sysLogDAO.updateById(e);
    }

    @Override
    public SysLog selectById(String id) {
        return sysLogDAO.selectById(id);
    }

    @Override
    public PageInfo<SysLog> selectListPage(SysLog e, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysLog> list = sysLogDAO.list(e);
        PageInfo<SysLog> pageInfo = new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }
    /**
     * sysLogDAO名称和接口名称有关
     */

}