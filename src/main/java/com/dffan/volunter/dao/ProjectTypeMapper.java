package com.dffan.volunter.dao;

import java.util.List;

import com.dffan.volunter.domain.ProjectType;
/**
 * 项目类别
 * @author admin
 *
 */
public interface ProjectTypeMapper {
	/**
	 * 查询所有项目类别
	 * @return
	 */
	public List<ProjectType> getAllProtype();

}
