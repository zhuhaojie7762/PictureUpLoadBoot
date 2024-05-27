package com.platform.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.dao.PicturesDAO;
import com.platform.base.impl.ServiceManager;
import com.platform.entity.Pictures;
import com.platform.entity.SysLog;
import com.platform.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//extends ServiceManager<Pictures,PicturesDAO>
@Service("pictureService")
public class PictureServiceImpl  implements PictureService{

    @Autowired
	PicturesDAO picturesDAO;


	@Override
	public List<Pictures> list(Pictures e) {
		return picturesDAO.list(e);
	}

	@Override
	public Pictures get(Pictures e) {
		return picturesDAO.get(e);
	}

	@Override
	public int save(Pictures e) {
		return picturesDAO.save(e);
	}

	@Override
	public int removeById(String id) {
		return picturesDAO.removeById(id);
	}

	@Override
	public int delete(Pictures e) {
		return picturesDAO.delete(e);
	}

	@Override
	public int update(Pictures e) {
		return picturesDAO.update(e);
	}

	@Override
	public int updateById(Pictures e) {
		return picturesDAO.updateById(e);
	}

	@Override
	public Pictures selectById(String id) {
		return picturesDAO.selectById(id);
	}

	@Override
	public PageInfo<Pictures> selectListPage(Pictures e, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Pictures> list = picturesDAO.list(e);
		PageInfo<Pictures> pageInfo = new PageInfo<>(list);
		pageInfo.setList(list);
		return pageInfo;
	}
}
