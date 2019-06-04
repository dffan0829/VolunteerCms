package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.MessageMapper;
import com.dffan.volunter.domain.VisitorMsg;
/**
 * 用户留言service
 * @author admin
 *
 */
@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;
	/**
	 * 查询所有的留言
	 * @param map
	 * @return
	 */
	public List<VisitorMsg> getAllMessage(Map<String,Object> map){
		return messageMapper.getAllMessage(map);
	}
	/**
	 * 保存一条用户留言
	 * @param yhly
	 * @return
	 */
	public boolean saveYhly(VisitorMsg yhly) {
		return messageMapper.saveYhly(yhly);
	}
	/**
	 * 更新留言
	 * @param map
	 */
	public boolean updateMessage(Map<String, Object> map) {
		return messageMapper.updateMessage(map);
	}

}
