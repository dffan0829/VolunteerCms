package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.BaseNewsMapper;
import com.dffan.volunter.domain.BaseNews;


/**
 * 关于基层速递的service
 * @author Administrator
 *
 */
@Service
public class BaseNewsService {

	@Autowired
	private BaseNewsMapper baseNewsMapper;
	/**
	 * 查询所有的基层速递
	 * @return
	 */
	public List<BaseNews> getAllBaseNews(Map<String,Object> map) {
		return baseNewsMapper.getAllBaseNews(map);
	}
	/**
	 * 基层速递详情
	 * @param id
	 * @return
	 */
	public BaseNews queryBaseDetail(Integer id){
		return baseNewsMapper.queryBaseDetail(id);
	}
}
