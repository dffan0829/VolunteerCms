package com.dffan.volunter.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.AdminMapper;
import com.dffan.volunter.domain.Admin;
import com.dffan.volunter.domain.Reply;

/**
 * 后台管理员的Service
 * @author admin
 *
 */
@Service
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 管理员登录
	 * @param map
	 * @return
	 */
	public Admin login(Map<String, Object> map) {
		return adminMapper.login(map);
	}
	/**
	 * 管理回复留言
	 * @param yhly
	 * @return
	 */
	public boolean replyYhly(Reply r) {
		return adminMapper.replyYhly(r);
	}

	
}
