package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.VolunteerMapper;
import com.dffan.volunter.domain.Volunteer;
/**
 * 关于志愿者的Service
 * @author Administrator
 */
@Service
public class VolunteerService {

	@Autowired
	private VolunteerMapper volunteerMapper;
	
	/**
	 * 查询所有的志愿者 分页
	 * @param map
	 * @return
	 */
	public List<Volunteer> getAllVolun(Map<String,Object> map) {
		return volunteerMapper.getAllVolun(map);
	}
	/**
	 * 删除志愿者
	 * @param id 主键
	 * @return
	 */
	public boolean deleteVolunt(Integer id) {
		return volunteerMapper.deleteVolunt(id);
	}
	/**
	 * 查询志愿者详细信息
	 * @param id
	 * @return
	 */
	public Volunteer queryVolunteer(Map<String,Object> map) {
		return volunteerMapper.queryVolunteer(map);
	}
	/**
	 * 用户注册提交
	 * @param volun
	 * @return 
	 */
	public boolean saveVolun(Volunteer volun) {
		return volunteerMapper.saveVolun(volun);	
	}
	/**
	 * 志愿者登录
	 * @param map
	 * @return
	 */
	public Volunteer volunLogin(Map<String, Object> map) {
		return volunteerMapper.volunLogin(map);
	}
	/**
	 * 更新志愿者
	 * @param map
	 * @return
	 */
	public boolean updateVolun(Map<String, Object> map) {
		return volunteerMapper.updateVolun(map);
	}
	/**
	 * 查询参加该活动的所有志愿者id
	 * @param map
	 * @return
	 */
	public List getVidByProId(Map<String, Object> map) {
		return volunteerMapper.getVidByProId(map);

	}
}
