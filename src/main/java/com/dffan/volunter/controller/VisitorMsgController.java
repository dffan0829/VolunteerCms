package com.dffan.volunter.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dffan.util.Utils;
import com.dffan.volunter.domain.ProjectType;
import com.dffan.volunter.domain.VisitorMsg;
import com.dffan.volunter.service.MessageService;
import com.dffan.volunter.service.ProjectTypeService;
import com.dffan.volunter.service.VisitorService;
import com.dffan.volunter.utils.Init;

/**
 * 互动空间controller
 * @author admin
 *
 */
@Controller
public class VisitorMsgController {
	@Autowired
	private VisitorService visitorService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ProjectTypeService projectService;
	
    /**
     * to
     * 互动空间
     * @param map
     * @return
     */
	@RequestMapping("/toHdkj")
	public String queryAllVisitorMsg(Map<String,Object> map,HttpServletRequest request){
		List<ProjectType> tlst = projectService.getAllProtype();//项目分类
		request.setAttribute("tlst", tlst);
		return "hdkj";
	}
	
	/**
	 * 查询用户留言
	 * @return
	 */
	@RequestMapping(value="/yhly")
	@ResponseBody
	public PageInfo queryYhlyWithJson(@RequestParam(value="pageNo",required=true,defaultValue="1")Integer pageNo,
			Map<String,Object> map){
		JSONObject jsonObject = new JSONObject();
		
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNo, Init.getPageSize());
		
		//按照时间降序排列
		map.put("sort", "msg_time");
		map.put("order", "desc");
		map.put("navli", "7");//选中栏目 css li的位置

		List<VisitorMsg> mlst = messageService.getAllMessage(map);//留言
		PageInfo pageinfo = new PageInfo<>(mlst);
		
		return pageinfo;
	}
	
	/**
	 * 查询用户留言
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/yhly1",method=RequestMethod.GET)
	@ResponseBody
	public String queryYhly(@RequestParam(value="pageNo",required=true,defaultValue="1") Integer pageNo,
			Map<String,Object> map) throws JSONException {
		
		
		
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNo, Init.getPageSize());
		
		//按照时间降序排列
		map.put("sort", "msg_time");
		map.put("order", "desc");
		List<VisitorMsg> mlst = messageService.getAllMessage(map);//留言

		PageInfo pageinfo = new PageInfo<>(mlst);
		
		System.out.println(pageinfo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pageinfo", new JSONObject(pageinfo));//分页相关信息
		jsonObject.put("mlst", mlst);//留言lst
		
		return jsonObject.toString();
	}
	/**
	 * 保存用户留言
	 * @param yhly
	 * @param result
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/saveYhly",method=RequestMethod.POST)
	public void saveYhly(@Valid VisitorMsg yhly,BindingResult result,
						HttpServletRequest request,
						HttpServletResponse response) throws IOException{	
		
		yhly.setMsgTime(Utils.getNowDate());//留言日期
		yhly.setIp(Utils.ip());//留言的ip
		yhly.setMsgStatus("0");
		System.out.println(yhly);
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		//表单验证 JSR303技术 出现错误返回登录页面	
		if(result.getErrorCount() > 0){
			for(FieldError error : result.getFieldErrors()){
				System.out.println(error.getField() + "："+error.getDefaultMessage());
				
			}
		    response.getWriter().write("<font color='red'>请填写完整信息</font>");
		}else{
			//未出现错误
			boolean f = messageService.saveYhly(yhly);
			response.getWriter().print(f);
		}
		

	}
	
	
}
