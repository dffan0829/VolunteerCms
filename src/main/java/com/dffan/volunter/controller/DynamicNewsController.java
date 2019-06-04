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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dffan.volunter.domain.DynamicNews;
import com.dffan.volunter.service.DynamicNewsService;
import com.dffan.volunter.utils.Init;

/**
 * 动态要闻控制器
 * 
 * @author admin
 * 
 */
@Controller
public class DynamicNewsController {

	@Autowired
	private DynamicNewsService dynamicNewsService;

	/**
	 * 动态要闻详情 rest风格的url
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryDynamicDetails/{id}",method = RequestMethod.GET)
	public String queryDynamicNewsDetail(
			HttpServletRequest request,
			@PathVariable Integer id) {

		DynamicNews dynamicNews = dynamicNewsService.queryDetail(id);
		request.setAttribute("dynamicNews", dynamicNews);
		request.setAttribute("title", "动态要闻");

		return "details";
	}

	/**
	 * 查询所有动态要闻
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/queryAllDynamicNews")
	public String queryAllDynamicNews(
			HttpServletRequest request,
			@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "keyword", required = false) String keyword,
			Map<String, Object> map) throws UnsupportedEncodingException {

		/*
		 * //设置分页 Page page = new Page(); //page.setCurPage(PageNo); int
		 * pageSize = page.getRowsPerPage();
		 * 
		 * int index = (PageNo-1)*page.getRowsPerPage();//分页的索引 int idx = index;
		 * 
		 * map.put("idx", idx); map.put("pageSize", pageSize);
		 * 
		 * System.out.println("idx:"+idx+"pageSize:"+pageSize);
		 */

		PageHelper.startPage(PageNo, Init.getPageSize());// 设置分页
/*																				// 返回分页的一些相关信息
		Page{count=true, pageNum=1, pageSize=10, startRow=0, 
				endRow=10, total=33, pages=4, reasonable=false, pageSizeZero=false}*/
		
		if(keyword != null){
        	map.put("keyword", new String(keyword.getBytes("ISO-8859-1"),"utf-8"));//搜索的关键词
        }

		List<DynamicNews> dynamicNewsLst = dynamicNewsService.getAllDynamicNews(map);
		request.setAttribute("dynamicNewsLst", dynamicNewsLst);
		
		PageInfo pageinfo = new PageInfo<>(dynamicNewsLst,5);
		
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("title", "动态要闻");
		request.setAttribute("navli", "1");//选中栏目 css li的位置
		request.setAttribute("banner", "xwzx");//页面banner图

		return "list";
	}

}
