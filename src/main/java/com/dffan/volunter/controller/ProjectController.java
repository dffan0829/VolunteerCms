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
import com.dffan.volunter.domain.ProjectType;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.service.ProjectServices;
import com.dffan.volunter.service.ProjectTypeService;

/**
 * 志愿者活动
 * 
 * @author admin
 * 
 */
@Controller
public class ProjectController {

	@Autowired
	private ProjectServices projectServices;
	@Autowired
	private ProjectTypeService projectTypeService;
	

	/**
	 * 志愿者项目详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryProjetDetail/{id}", method = RequestMethod.GET)
	public String queryProjetDetail(HttpServletRequest request,
			@PathVariable Integer id) {

		System.out.println("志愿者项目------");

		//志愿者活动的详情
		TeamProject teamProject = projectServices.queryProjectDetail(id);
		request.setAttribute("Project", teamProject);

		//左侧分类树
		List<ProjectType> tlst = projectTypeService.getAllProtype();
		request.setAttribute("tlst", tlst);

		return "ProjectDetail";
	}

	/**
	 * 查询所有志愿者项目
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/queryAllProject")
	public String queryAllProject(
			HttpServletRequest request,
			Map<String, Object> map,
			@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
			@RequestParam(value = "keyword", required = false) String keyword) throws UnsupportedEncodingException {

		Page<Object> page = PageHelper.startPage(pageNo, 5);//分页
		
        if(keyword != null){
        	map.put("keyword", new String(keyword.getBytes("ISO-8859-1"),"utf-8"));//搜索的关键词
        }
		
		List<TeamProject> prolst = projectServices.getAllProject(map);

		PageInfo pageinfo = new PageInfo<>(prolst,5);
		
		request.setAttribute("hrefname", "queryAllProject");//超链接的name
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("prolst", prolst);
		request.setAttribute("page", page);
		request.setAttribute("title", "项目发布");
		request.setAttribute("navli", "3");//选中栏目 css li的位置

		
		return "prolst";
	}


}
