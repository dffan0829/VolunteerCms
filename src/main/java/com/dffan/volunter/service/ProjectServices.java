package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.ProjectMapper;
import com.dffan.volunter.domain.AddProject;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.domain.Volunteer;

//招募活动的service
@Service
public class ProjectServices {

	@Autowired
	private ProjectMapper projectMapper;

	/**
	 * 查询所有的招募活动
	 * 
	 * @return
	 */
	public List<TeamProject> getAllProject(Map<String, Object> map) {
		return projectMapper.getAllProject(map);
	}

	/**
	 * 志愿者项目详情
	 */
	public TeamProject queryProjectDetail(Integer id) {
		return projectMapper.queryProjectDetail(id);
	}
	/**
	 * 查看当前项目的状态
	 * @return
	 */
	public String queryProjectStatus(Integer id){
		return projectMapper.queryProjectStatus(id);
	}
    /**
     * 查看加入该项目的人
     * @param id
     * @return
     */
	public List<Volunteer> queryAddPersonByPro(Integer id) {
		return projectMapper.queryAddPersonByPro(id);
	}

	/**
	 * 加入活动 存数据
	 * @param addProject
	 * @return
	 */
	public boolean addProByVolun(AddProject addProject) {
		return projectMapper.addProByVolun(addProject);
	}
	/**
	 * 查看志愿者参与的所有志愿活动
	 * @param id
	 * @return
	 */
	public List<TeamProject> queryAddPro(Integer id) {
		return projectMapper.queryAddPro(id);
	}
	/***
	 * 查看志愿者当前是否参加了该活动
	 * @param map
	 * @return
	 */
	public int queryProByMyId(Map<String, Object> map) {
		return projectMapper.queryProByMyId(map);
	}
    /**
     * 管理员（个队长） 后台添加志愿者活动
     * @param pro
     * @return
     */
	public boolean addProjectByAdmin(TeamProject pro) {
		return projectMapper.addProjectByAdmin(pro);

	}
    /**
     * 更新 招募活动
     * @param map
     * @return
     */
	public boolean updateProject(Map<String, Object> map) {
		return projectMapper.updateProject(map);

	}
	 /**
     * 更新 招募活动
     * @param map
     * @return
     */
	public boolean updateProjectByEntity(TeamProject t) {
		return projectMapper.updateProjectByEntity(t);

	}
	/**
	 * 删除志愿活动
	 * @param map
	 * @return
	 */
	public boolean deletePro(Map<String, Object> map) {
		return projectMapper.deletePro(map);

	}
}
