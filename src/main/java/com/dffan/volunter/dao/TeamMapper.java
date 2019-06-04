package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.Team;

/**
 * 志愿服务队伍 DAO
 * @author admin
 *
 */
public interface TeamMapper {
    /**
     * 查询所有的服务队伍
     * @return
     */

	public List<Team> queryAllTeam(Map<String, Object> map);

	/**
	 * 根据id查询组织详情
	 * @param map
	 * @return
	 */
	public Team getDetailTeam(Map<String, Object> map);
    /**
     * 查询组织 后台
     * @param map
     * @return
     */
	public List<Map<String, Object>> queryTeam(Map<String, Object> map);
    /**
     * 队长更新组织信息
     * @param t
     * @return
     */
	public boolean updateTeam(Team t);
    /**
     * 查询活跃组织
     * @return
     */
	public List<Map<String,Object>> getHotteam();

}
