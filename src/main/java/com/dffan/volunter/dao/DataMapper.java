package com.dffan.volunter.dao;

import java.util.List;
import java.util.Map;

import com.dffan.volunter.domain.VolunData;
/**
 * 在线培训
 * 文件资料
 * 媒体传真
 * @author admin
 *
 */
public interface DataMapper {

	/**
	 * 在线培训
	 * @return
	 */
	public List<VolunData> getAllOnline(Map<String, Object> map);
	/**
	 * 文件资料
	 * @return
	 */
	public List<VolunData> getAllFile(Map<String, Object> map);
	/**
	 * 媒体传真
	 * @return
	 */
	public List<VolunData> getAllMedia(Map<String, Object> map);
	/**
	 * 所有资料详情详情
	 * @param id
	 * @return
	 */
	
	public VolunData queryDataDetail(Map<String, Object> map);
	/**
	 * 删除一条记录
	 * * 动态要闻 在线培训 文件资料 媒体传真
	 * @param map
	 * @return
	 */
	public boolean deleteRow(Map<String, Object> map);
	/**
	 * 查询所有资料 按照类型
	 * @param map
	 * @return
	 */
	public List<VolunData> queryData(Map<String, Object> map);
	/**
	 * 保存一条资料
	 * @param volunData
	 * @return
	 */
	public boolean saveVolunData(VolunData volunData);
	/**
	 * 修改一条资料
	 * @param volunData
	 * @return
	 */
	public boolean updateVolunData(VolunData volunData);

}
