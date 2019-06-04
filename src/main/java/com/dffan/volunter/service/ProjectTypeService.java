package com.dffan.volunter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.ProjectTypeMapper;
import com.dffan.volunter.domain.ProjectType;

/**
 * 项目类别service
 * @author admin
 *
 */
@Service
public class ProjectTypeService {
 
	@Autowired
	private ProjectTypeMapper projectTypeMapper;
	
	/**
	 * 查询所有项目类别
	 * @return
	 */
	public List<ProjectType> getAllProtype(){
		return projectTypeMapper.getAllProtype();
	}
	
	
	
}

