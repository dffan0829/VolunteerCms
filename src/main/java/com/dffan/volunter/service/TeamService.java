package com.dffan.volunter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.TeamMapper;
import com.dffan.volunter.domain.Team;

/**
 * 志愿服务队伍
 * @author admin
 *
 */
@Service
public class TeamService {
	
	@Autowired
	private TeamMapper teamMapper;

    //查询所有的组织 
	public List<Team> getAllTeam(Map<String, Object> map) {
		return teamMapper.queryAllTeam(map);
	}

	public List<Team> queryAllTeam(HashMap<Object, Object> hashMap) {
		return null;
	}
    //查询组织详情 
	public Team getDetailTeam(Map<String, Object> map) {
		return teamMapper.getDetailTeam(map);
	}
   
	public List<Map<String, Object>> queryTeam(Map<String, Object> map) {
		return teamMapper.queryTeam(map);
	}
    //队长更新组织信息
	public boolean updateTeam(Team t) {
		return teamMapper.updateTeam(t);
	}
    //查询活跃组织
	public List<Map<String,Object>> getHotteam() {
		return teamMapper.getHotteam();
	}
	
	
	
}
