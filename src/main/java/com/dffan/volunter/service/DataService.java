package com.dffan.volunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dffan.volunter.dao.DataMapper;
import com.dffan.volunter.domain.VolunData;

/**
 * 在线培训
 * 文件资料
 * 媒体传真
 * service
 * 
 * @author Administrator
 * 
 */
@Service
public class DataService {

	@Autowired
	private DataMapper dataMapper;
	
	public List<VolunData> getAllOnline(Map<String,Object> map) {
		return dataMapper.getAllOnline(map);
	}

	public List<VolunData> getAllFile(Map<String,Object> map) {
		return dataMapper.getAllFile(map);
	}
	public List<VolunData> getAllMedia(Map<String,Object> map) {
		return dataMapper.getAllMedia(map);
	}
	
	/**
	 * 所有资料详情详情
	 * @param id
	 * @return
	 */
	public VolunData queryDataDetail(Map<String,Object> map){
		return dataMapper.queryDataDetail(map);
	}
	/**
	 * 删除一条记录
	 * 动态要闻 在线培训 文件资料 媒体传真
	 * @param id
	 * @return
	 */
	public boolean deleteRow(Map<String,Object> map){
		return dataMapper.deleteRow(map);

	}
    /**
     * 查询所有资料 按照类别
     * @param map
     * @return
     */
	public List<VolunData> queryData(Map<String, Object> map) {
		return dataMapper.queryData(map);
	}
    /**
     * 保存一条资料
     * @param volunData
     * @return
     */
	public boolean saveVolunData(VolunData volunData) {
		return dataMapper.saveVolunData(volunData);
	}
    /**
     * 修改一条资料
     * @param volunData
     * @return
     */
	public boolean updateVolunData(VolunData volunData) {
		return dataMapper.updateVolunData(volunData);
	}
}
