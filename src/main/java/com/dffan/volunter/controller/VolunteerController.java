package com.dffan.volunter.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dffan.util.Utils;
import com.dffan.volunter.service.ProjectServices;
import com.dffan.volunter.service.TeamService;
import com.dffan.volunter.service.VolunteerService;
import com.dffan.volunter.utils.Init;
import com.dffan.volunter.domain.AddProject;
import com.dffan.volunter.domain.Team;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.domain.Volunteer;

/**
 * 志愿者控制器
 * @author admin
 *
 */
@Controller
public class VolunteerController {

	@Autowired
	private VolunteerService volunteerService;
	@Autowired
	private ProjectServices projectServices;
	@Autowired
	private TeamService teamService;

	@RequestMapping(value="/register")
	public String volunRegister(HttpServletRequest request,Map<String,Object> map){
		//注册时 查询所有的服务队伍 注册页面显示 用户选择队伍
		List<Team> tlst = teamService.getAllTeam(map);
		
		request.setAttribute("tlst", tlst);
		
		return "register";
	}
	/**
	 * 保存注册
	 * @param volun
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveVolun",method=RequestMethod.POST)
	public void saveVolun(@Valid Volunteer volun,BindingResult result,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	/*	经验：
	 *  1，如果中文返回出现？？字符，这表明没有加response.setCharacterEncoding("UTF-8");这句话。
	    2，如果返回的中文是“烇湫”这种乱码，说明浏览器的解析问题， 应该检查下是否忘加
	     response.setHeader("Content-type", "text/html;charset=UTF-8");这句话。
	 */
		//	response.getWriter().write("<script>alert('注册成功！请等待后台管理员审核!');window.location.href='index.jsp';</script>");
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		System.out.println(volun);
		//表单验证 JSR303技术 出现错误返回登录页面	
		if(result.getErrorCount() > 0){
			for(FieldError error : result.getFieldErrors()){
				System.out.println(error.getField() + "："+error.getDefaultMessage());
				
			}
		  response.getWriter().write("<script>window.location.href='register';</script>");
		}
	    //验证通过
		volun.setRegisTime(Utils.getNowDateExcapeSs());
		boolean f = volunteerService.saveVolun(volun);
		if(f){
			response.getWriter().write("<script>alert('注册成功！请等待后台管理员审核!');window.location.href='index.jsp';</script>");
		}
		
	}
	
	/**
	 * 跳转到登录页面
	 */
	@RequestMapping("/toLogin")
	public String toLogin(@ModelAttribute("msg") String msg,Map<String,Object> map){
		System.out.println("mag-----------:"+msg);
		map.put("msg", msg);//放入请求欲中
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Map<String,Object> map,RedirectAttributes attr){
		System.out.println("用户登录!");
		String redirectUrl;
		String msg = "";//出错信息
		//获取参数
		String name = request.getParameter("name").trim();
		String password = request.getParameter("password").trim();
		String verfycode = request.getParameter("verfycode").trim();
		if(name != null && password != null && verfycode != null){
			//比对验证码
			String verifyCode = request.getServletContext().getAttribute("VerifyCode").toString();
			System.out.println("set VerifyCode=" + verifyCode);
			if(!verifyCode.equalsIgnoreCase(verfycode)){
				msg = "验证码错误";
				redirectUrl = "redirect:toLogin";
			}else{
				map.put("name", name);
				map.put("password", password);
				Volunteer volunteer = volunteerService.volunLogin(map);
				//登陆是否成功
				if(volunteer !=null){
					//当前用户是否已经审核通过为志愿者
					if(volunteer.getRegisstatus().equals("0")){
						//当前未审核通过
						msg = "审核还未通过";
						redirectUrl = "redirect:toLogin";
					}else{
						//审核通过 可以登录
						redirectUrl = "redirect:index";
						request.getSession().setAttribute("volun", volunteer);
					}
				}else{
					msg = "用户名或者密码错误";
					redirectUrl = "redirect:toLogin";
				}
			}
		}else{
			msg = "请填写完整信息";
			redirectUrl = "redirect:toLogin";
		}
		
		attr.addFlashAttribute("msg", msg);
		System.out.println("错误的消息:"+attr.getFlashAttributes().get("msg"));
       //request.setAttribute("error", msg);
		
		
        return redirectUrl;
	}
	/**
	 * 志愿者参加志愿活动
	 * @param request
	 * @param response
	 * @param id
	 * @param prototal
	 * @param godate
	 * @throws IOException
	 */
	@RequestMapping("/addProject")
	public void addProject(HttpServletRequest request,
			              HttpServletResponse response,
			              @RequestParam(value="id",required=true,defaultValue="") Integer id,//活动的id
			              @RequestParam(value="totalperson",required=true,defaultValue="") Integer prototal,//活动总人数
			              @RequestParam(value="godate",required=true,defaultValue="") String godate,//活动开始的日期
			              Map<String,Object> map) throws IOException{
		Volunteer u = (Volunteer) request.getSession().getAttribute("volun");
		String returnStr = "";
		
		//当前用户未登录
		if(u == null){
			returnStr = "0";
			response.getWriter().print(returnStr);
		    return;
		}
		//查看活动是否已经加入过了
		map.put("id", u.getId());
		map.put("proid", id);
		int count = projectServices.queryProByMyId(map);
		if(count==1){
			returnStr = "exist";
			response.getWriter().print(returnStr);
		    return;
		}
		
		
        //查看该志愿者是否参加了其它活动 在 活动的日期	是否冲突
		//活动开始的时间gotime
		List<TeamProject> tlst = projectServices.queryAddPro(u.getId());
		if(!tlst.isEmpty()){
			for(TeamProject t : tlst){
				//活动日期冲突
				if(t.getProGotime().equals(godate)){
					returnStr = "1";
					response.getWriter().print(returnStr);
					return ;
				}
			}
		}
		
		//查看当前活动的的状态 是否已经完成了
		String status = projectServices.queryProjectStatus(id);
		if("3".equals(status)){
			returnStr = "2";
			response.getWriter().print(returnStr);
			return ;
		}
		//查看当前活动加入人数是否已满
		List<Volunteer>  vlst  = projectServices.queryAddPersonByPro(id);
        if(!vlst.isEmpty()){
        	if(prototal <= vlst.size()){
        		//人数已满
        		returnStr = "3";
    			response.getWriter().print(returnStr);
    			return;
        	}else{
        		//可以加入
         	   AddProject addProject = new AddProject(id, u.getId());
         	   boolean f = projectServices.addProByVolun(addProject);//志愿者参加志愿者活动
         	   response.getWriter().print(f);
        	}
        }else{
        	   //可以加入
        	   AddProject addProject = new AddProject(id, u.getId());
        	   boolean f = projectServices.addProByVolun(addProject);//志愿者参加志愿者活动
        	   response.getWriter().print(f);
        }			
		
	}
	
	/**
	 * 查看志愿组织
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/VolunTeam")
	public String queryVolunTeam(HttpServletRequest request,HashMap<String,Object> map,
			                    @RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo){
		List<Map<String,Object>> htlst = teamService.getHotteam();//查询活跃组织
		PageHelper.startPage(pageNo,5);//分页
		List<Team> tlst = teamService.getAllTeam(map);//所有组织
		PageInfo pageInfo = new PageInfo<>(tlst);
		map.put("tlst", tlst);//所有组织
		map.put("pageinfo", pageInfo);//分页以及数据信息
		map.put("hrefname", "VolunTeam");//超链接的name
		map.put("htlst", htlst);//热门组织
		map.put("navli", "4");//选中栏目 css li的位置

		return "orgLst";
	}
	
	/**
	 * 根据组织的id查询组织的详细信息 分页
	 * @param request
	 * @param map
	 * @param pageNo
	 * @param tid
	 * @return
	 */
	@RequestMapping(value="/queryTeamDetail/{id}/{pageNo}",method = RequestMethod.GET)
	public String queryTeamDetail(HttpServletRequest request,HashMap<String,Object> map,
			@PathVariable(value = "pageNo") Integer pageNo,
            @PathVariable(value="id") Integer tid)//组织的id
	{
        map.put("id", tid);//组织的id
		Team t = teamService.getDetailTeam(map);//查询组织详情
		map.put("team", tid);
		List<Map<String,Object>> htlst = teamService.getHotteam();//查询活跃组织
		PageHelper.startPage(pageNo,8);//分页
		List<TeamProject> plst = projectServices.getAllProject(map);//查询组织的所有项目 （已审核）
		PageInfo pageinfo = new PageInfo<>(plst);
		
		map.put("hrefname", "queryTeamDetail");//超链接的name
		map.put("pageinfo", pageinfo);//分页相关信息和数据
		map.put("htlst", htlst);//热门组织
		map.put("t", t);//放入请求域中
		map.put("p", plst);//放入请求域中
		
		return "orginfo";
	}
}
