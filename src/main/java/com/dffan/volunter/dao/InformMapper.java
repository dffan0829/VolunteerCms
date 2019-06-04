package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.Inform;

public interface InformMapper {

	/**
	 * 查询所有的通知公告
	 * @return
	 */
	public List<Inform> getAllInform(Map<String, Object> map);

	/**
	 * 通知公告详情
	 * @param id
	 * @return
	 */
	public Inform queryInformDetail(Integer id);
}
