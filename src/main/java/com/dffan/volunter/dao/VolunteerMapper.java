package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.Volunteer;

public interface VolunteerMapper {


	/**
	 * 查询所有的志愿者
	 * @return
	 */
	public List<Volunteer> getAllVolun(Map<String, Object> map);
     /**
      * 删除志愿者
      * @param id
      * @return
      */
	public boolean deleteVolunt(Integer id);
	/**
	 * 查询志愿者详细信息
	 * @param id
	 * @return
	 */
	public Volunteer queryVolunteer(Map<String, Object> map);
	/**
	 * 用户注册志愿者提交
	 * @param volun
	 * @return 
	 */
	public boolean saveVolun(Volunteer volun);
	/**
	 * 志愿者登录
	 * @param map
	 * @return
	 */
	public Volunteer volunLogin(Map<String, Object> map);
	/**
	 * 更新志愿者
	 * @param map
	 * @return
	 */
	public boolean updateVolun(Map<String, Object> map);
	//查询参加该活动的所有志愿者id
	public List getVidByProId(Map<String, Object> map);
}
