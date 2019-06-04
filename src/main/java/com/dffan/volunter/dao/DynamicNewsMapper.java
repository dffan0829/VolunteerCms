package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.DynamicNews;

/**
 * 动态要闻
 * @author admin
 *
 */
public interface DynamicNewsMapper {
  
	/**
	 * 查询动态要闻
	 * @return
	 */
	public List<DynamicNews> getAllDynamicNews(Map<String, Object> map);
	/**
	 * 动态要闻详情
	 * @param id
	 * @return
	 */
	public DynamicNews queryDynamicDetail(Integer id);
	/**
	 * 保存一条动态要闻
	 * @param dynamicNews
	 * @return
	 */
	public boolean saveAdd(DynamicNews dynamicNews);
	/**
	 * 修改动态要闻
	 * @param dynamicNews
	 * @return
	 */
	public boolean update(DynamicNews dynamicNews);
	
	
}
