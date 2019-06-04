package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.BaseNews;

/**
 * 基层速递
 * @author admin
 *
 */
public interface BaseNewsMapper {
	/**
	 * 查询基层速递
	 * @return
	 */
	public List<BaseNews> getAllBaseNews(Map<String, Object> map);
	/**
	 * 基层速递详情
	 * @param id
	 * @return
	 */
	public BaseNews queryBaseDetail(Integer id);
}
