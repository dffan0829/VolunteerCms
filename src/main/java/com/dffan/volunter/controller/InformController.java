package com.dffan.volunter.controller;

import java.io.UnsupportedEncodingException;
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
import com.dffan.volunter.domain.BaseNews;
import com.dffan.volunter.domain.Inform;
import com.dffan.volunter.service.InformService;
import com.dffan.volunter.utils.Init;
/**
 * 平台公告
 * @author admin
 *
 */
@Controller
public class InformController {

	@Autowired
	private InformService informService;

	/**
	 * 通知公告详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryInformDetail/{id}",method=RequestMethod.GET)
	public String queryInformDetail(
			HttpServletRequest request,
			@PathVariable Integer id) {

		Inform inform = informService.queryInformDetail(id);
		request.setAttribute("inform", inform);
		request.setAttribute("title", "通知公告");

		return "details";
	}
	/**
	 * 查询所有平台公告 分页
	 * @param map
	 * @param request
	 * @param pageNo
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
    @RequestMapping(value="/queryAllInform")
	public String queryAllInform(
			Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "keyword", required = false) String keyword) throws UnsupportedEncodingException {
		
		Page<Object> page = PageHelper.startPage(pageNo,Init.getPageSize());//设置分页 列表页显示8条记录 返回分页的一些相关信息
		
		if(keyword != null){
        	map.put("keyword", new String(keyword.getBytes("ISO-8859-1"),"utf-8"));//搜索的关键词
        }
		
		List<Inform> informLst = informService.getAllInform(map);
		
		PageInfo pageinfo = new PageInfo<>(informLst,5);
		request.setAttribute("pageinfo", pageinfo);
		
		request.setAttribute("informlst", informLst);
		request.setAttribute("title", "通知公告");

		return "list";
	}
}
