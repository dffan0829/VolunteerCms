package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.AddProject;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.domain.Volunteer;

public interface ProjectMapper {
	/**
	 * 查询所有的志愿者项目
	 * @param map
	 * @return
	 */
	public List<TeamProject> getAllProject(Map<String, Object> map);
   /**
    * 志愿者项目详情
    */
	public TeamProject queryProjectDetail(Integer id);
	/**
	 * 查看当前项目的状态
	 * @param id
	 * @return
	 */
    public String queryProjectStatus(Integer id);
    /**
     * 查询当前加入该项目的人
     * @param id
     * @return
     */
	public List<Volunteer> queryAddPersonByPro(Integer id);
	/**
	 * 加入活动 存数据
	 * @param addProject
	 * @return
	 */
	public boolean addProByVolun(AddProject addProject);
	/**
	 * 查看志愿者参与的所有志愿活动
	 * @param id
	 * @return
	 */
	public List<TeamProject> queryAddPro(Integer id);
	/***
	 * 查看志愿者当前是否参加了该活动
	 * @param map
	 * @return
	 */
	public int queryProByMyId(Map<String, Object> map);
	/**
	 * 后台管理员添加活动（个队长添加活动）
	 * @param pro
	 * @return
	 */
	public boolean addProjectByAdmin(TeamProject pro);
	/**
	 * 更新招募活动
	 * @param map
	 * @return
	 */
	public boolean updateProject(Map<String, Object> map);
	/**
	 * 更新招募活动
	 * @param map
	 * @return
	 */
	public boolean updateProjectByEntity(TeamProject t);
	/**
	 * 删除志愿活动
	 * @param map
	 * @return
	 */
	public boolean deletePro(Map<String, Object> map);
}
