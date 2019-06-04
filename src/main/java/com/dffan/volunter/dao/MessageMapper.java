package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.VisitorMsg;

/**
 * 用户留言
 * @author admin
 *
 */
public interface MessageMapper {

	//查询所有的留言
	public List<VisitorMsg> getAllMessage(Map<String, Object> map);
    //保存一条用户留言
	public boolean saveYhly(VisitorMsg yhly);
	//更新留言
	public boolean updateMessage(Map<String, Object> map);

}
