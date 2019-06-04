package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.InformMapper;
import com.dffan.volunter.domain.Inform;





/**
 * 通知公告的service
 * @author Administrator
 *
 */
@Service
public class InformService {

	@Autowired
	private InformMapper inforMapper;
	
	public List<Inform> getAllInform(Map<String,Object> map) {
		return inforMapper.getAllInform(map);
	}
	
	/**
	 * 通知公告详情
	 * @param id
	 * @return
	 */
	public Inform queryInformDetail(Integer id){
		return inforMapper.queryInformDetail(id);
		
	}
	
}
