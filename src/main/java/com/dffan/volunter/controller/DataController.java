package com.dffan.volunter.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dffan.volunter.domain.VolunData;
import com.dffan.volunter.service.DataService;
import com.dffan.volunter.utils.Init;
/**
 * 在线培训
 * 文件资料
 * 媒体传真
 * @author admin
 *
 */
@Controller
public class DataController {
	
	@Autowired
	private DataService dataService;
	
	/**
	 * 资料详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryDataDetails/{type}/{id}")
	public String queryDataDetail(HttpServletRequest request
			,Map<String,Object> map
			,@PathVariable Integer id
			,@PathVariable Integer type)
	{
		
		map.put("id", id);
		map.put("type", type);
		
		VolunData volunData = dataService.queryDataDetail(map);//查询详情信息
		request.setAttribute("volunData", volunData);
		request.setAttribute("type", type);//当前资料的类型 1：在线培训 2：文件资料 3：媒体传真
		
		String title = null;
		
		switch(type){
		 case 1:
			 title = "在线培训";
		  break;
		 case 2:
			 title = "文件资料";
		  break;
		 case 3:
			 title = "媒体传真";
		  break;
		  default: title = " ";
		}
		
		request.setAttribute("title", title);//设置返回到jsp页面的标题名称

		return "details";
	}
	/**
	 * 查询所有的资料
	 * rest风格的url
	 * @param request
	 * @param pageNo
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/queryAllData")
	public String queryAllData(HttpServletRequest request
			,Map<String,Object> map
			,@RequestParam(value="pageNo",required=true,defaultValue="1") Integer pageNo
			,@RequestParam(value="type",required=true,defaultValue="1") Integer type
			,@RequestParam(value = "keyword", required = false) String keyword) throws UnsupportedEncodingException
	{
		
		map.put("type", type);//放置资料类型
		
		List<VolunData> dataLst = null;
		String title = null;
		String limap = "";
		String bannermap = "";
		Page<Object> page = PageHelper.startPage(pageNo, Init.getPageSize());//PageHelper插件设置分页
		
		if(keyword != null){
        	map.put("keyword", new String(keyword.getBytes("ISO-8859-1"),"utf-8"));//搜索的关键词
        }
		//判断是哪种类型的资料
		switch(type){
		 case 1:
			 title = "在线培训";
			 limap = "5";
			 bannermap = "pxzl";
			 dataLst = dataService.getAllOnline(map);
		  break;
		 case 2:
			 title = "文件资料";
			 limap = "6";
			 bannermap = "wjzl";
			 dataLst = dataService.getAllFile(map);
		  break;
		 case 3:
			 title = "媒体传真";
			 dataLst = dataService.getAllMedia(map);
		  break;
		  default: title = " ";
		}
		
		PageInfo pageinfo = new PageInfo<>(dataLst,5);
		request.setAttribute("pageinfo", pageinfo);
		
		request.setAttribute("dataLst", dataLst);//放入当前的列表
		request.setAttribute("title", title);//设置返回到jsp页面的标题名称
		request.setAttribute("type", type);//当前资料的类型 1：在线培训 2：文件资料 3：媒体传真
		request.setAttribute("navli", limap);//选中栏目 css li的位置
		request.setAttribute("banner", bannermap);//页面banner图


		
		return "list";
	}
	
}
