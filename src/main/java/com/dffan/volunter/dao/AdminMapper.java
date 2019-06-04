package com.dffan.volunter.dao;

import java.util.Map;

import com.dffan.volunter.domain.Admin;
import com.dffan.volunter.domain.Reply;

/**
 * 后台管理员
 * @author admin
 *
 */
public interface AdminMapper {
    /**
     * 后台登录
     * @param map
     * @return
     */
	public Admin login(Map<String, Object> map);
    /**
     * 管理员回复一条用户留言
     * @param r
     * @return
     */
	public boolean replyYhly(Reply r);

	
}
