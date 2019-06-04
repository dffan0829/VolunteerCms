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
import com.dffan.volunter.service.BaseNewsService;
import com.dffan.volunter.utils.Init;

@Controller
public class BaseNewsController {

	@Autowired
	private BaseNewsService baseNewsService;

	/**
	 * 基层速递详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryBaseNewsDetails/{id}",method=RequestMethod.GET)
	public String queryBaseNewsDetail(
			HttpServletRequest request,
			@PathVariable Integer id) {

		BaseNews baseNews = baseNewsService.queryBaseDetail(id);
		request.setAttribute("baseNews", baseNews);

		return "details";
	}

	/**
	 * 查询所有基层速递 分页
	 * @param map
	 * @param request
	 * @param pageNo
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
    @RequestMapping(value="/queryAllBaseNews")
	public String queryAllBaseNews(
			Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "keyword", required = false) String keyword) throws UnsupportedEncodingException {
		
		Page<Object> page = PageHelper.startPage(pageNo,Init.getPageSize());//设置分页 列表页显示8条记录 返回分页的一些相关信息
		
		if(keyword != null){
        	map.put("keyword", new String(keyword.getBytes("ISO-8859-1"),"utf-8"));//搜索的关键词
        }
		
		List<BaseNews> baseLst = baseNewsService.getAllBaseNews(map);
		
		PageInfo pageinfo = new PageInfo<>(baseLst,5);
		request.setAttribute("pageinfo", pageinfo);

		
		request.setAttribute("title", "基层速递");
		request.setAttribute("baselst", baseLst);
		request.setAttribute("page", page);
		request.setAttribute("navli", "2");//选中栏目 css li的位置
		request.setAttribute("banner", "xwzx");//页面banner图


		return "list";
	}

}
