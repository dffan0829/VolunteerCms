package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.DynamicNewsMapper;
import com.dffan.volunter.domain.DynamicNews;



/**
 * 关于动态新闻service方法
 * 
 * @author Administrator
 * 
 */
@Service
public class DynamicNewsService {
	
	@Autowired
	private DynamicNewsMapper dynamicNewsMapper;
	/**
	 * 查询所有的动态要闻
	 * @return
	 */
	public List<DynamicNews> getAllDynamicNews(Map<String,Object> map) {
		System.out.println("service:"+map.toString());
		return dynamicNewsMapper.getAllDynamicNews(map);
	}
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	public DynamicNews queryDetail(Integer id){
		return dynamicNewsMapper.queryDynamicDetail(id);
	}
	/**
	 * 保存一条动态要闻
	 * @param dynamicNews
	 * @return
	 */
	public boolean saveAdd(DynamicNews dynamicNews) {
		return dynamicNewsMapper.saveAdd(dynamicNews);	
	}
	/**
	 * 修改动态要闻
	 * @param dynamicNews
	 * @return
	 */
	public boolean update(DynamicNews dynamicNews) {
		return dynamicNewsMapper.update(dynamicNews);	
	}
}
