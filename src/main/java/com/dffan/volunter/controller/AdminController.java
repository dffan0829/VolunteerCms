package com.dffan.volunter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dffan.util.PropertyUtil;
import com.dffan.util.Utils;
import com.dffan.volunter.domain.Admin;
import com.dffan.volunter.domain.DynamicNews;
import com.dffan.volunter.domain.Lunbo;
import com.dffan.volunter.domain.Mdsx;
import com.dffan.volunter.domain.ProjectType;
import com.dffan.volunter.domain.Reply;
import com.dffan.volunter.domain.Team;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.domain.VisitorMsg;
import com.dffan.volunter.domain.VolunData;
import com.dffan.volunter.domain.Volunteer;
import com.dffan.volunter.service.AdminService;
import com.dffan.volunter.service.DataService;
import com.dffan.volunter.service.DynamicNewsService;
import com.dffan.volunter.service.MessageService;
import com.dffan.volunter.service.ProjectServices;
import com.dffan.volunter.service.ProjectTypeService;
import com.dffan.volunter.service.TeamService;
import com.dffan.volunter.service.VolunteerService;

/**
 * 管理员控制器
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private VolunteerService volunteerService;//志愿者
	@Autowired
	private AdminService adminService;//管理员
	@Autowired
	private DynamicNewsService dynamicNewsService;//动态要闻
	@Autowired
	private DataService dataService;//资料
	@Autowired
	private MessageService messageService;//留言
	@Autowired
	private TeamService teamService;//组织
	@Autowired
	private ProjectServices projectServices;//招募活动
	@Autowired
	private ProjectTypeService projectTypeService;
	
	/**
	 * 查询管理员未处理的事项
	 */
	@RequestMapping("/myUodo")
	@ResponseBody
	public void getMyUodo(){
		//用户注册的未审核
	    //队长发布志愿者活动未审核的
		JSONObject json = new JSONObject();
	}
	/**
	 * 后台管理员注销
	 * @throws IOException 
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("管理员注销");
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.getSession().removeAttribute("admin");
		response.getWriter().write("<script>alert('已注销!');window.location.href='"+request.getContextPath()+"/adminLogin.jsp';</script>");
	}
	/**
	 * 后台登录
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(@RequestParam(value="name",required=true,defaultValue="") String name,
			            @RequestParam(value="password",required=true,defaultValue="") String password,
			            Map<String,Object> map,
			            HttpServletRequest request){
		
		String msg = "";
		String url = null;
		
		if(name!= "" && password!= ""){
			map.put("name", name);
			map.put("password", password);
			Admin admin = adminService.login(map);
			if(admin!=null){
				request.getSession().setAttribute("admin", admin);
				url = "/admin/index";
			}else{
				url = "/redirect";
				msg = "用户名或者密码错误";
			}
		}else{
			url = "/redirect";
			msg = "信息填写完整";
		}
		
		return url;
	}
	
	/**
	 * 跳转到志愿者列表
	 * @return
	 */
	@RequestMapping(value="/getAllVoluns")
	public String tovlst(HttpServletRequest request,@RequestParam(value="tid",required=false) Integer tid){
		request.setAttribute("f", "1");//已审核的志愿者
		System.out.println("tid:"+tid);
		request.setAttribute("tid", tid);
		return "admin/vlst";
	}
	/**
	 * 跳转到志愿者未审核列表
	 */
	@RequestMapping(value="/toVolunConfirm")
	public String toVolunConfirm(HttpServletRequest request){
		request.setAttribute("f", "0");//未审核的用户
		return "admin/vlst";
	}
	/**
	 * 跳转到动态要闻
	 * @return
	 */
	@RequestMapping(value="/toDymamic")
	public String toDynamic(){
		return "admin/clst";
	}
	/**
	 * 跳转到媒体传真
	 * @return
	 */
	@RequestMapping(value="/toData")
	public String toData(@RequestParam(value="type",required=true,defaultValue="1") Integer type
			          ,Map<String,Object> map){
		map.put("t", type);
		return "admin/mlst";
	}
	/**
	 * 跳转到我的队伍
	 * @return
	 */
	@RequestMapping(value="/toWddw")
	public String toWddw(Map<String,Object> map,HttpServletRequest request){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		map.put("id",admin.getTeam());
		Team t = teamService.getDetailTeam(map);
		map.put("team", t);
		return "admin/wddw";
	}
	/**
	 * 查询所有的志愿者
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/volunlst")
	@ResponseBody//返回json
	public String queryAllVolun(Map<String,Object> map,
			HttpServletRequest request,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows
			){
		List l = null ;
		String f = request.getParameter("f");//当前的查询状态 0：查询未审核 1：查询已审核的 志愿者
		
		String tid = request.getParameter("tid");//当前组织的id 查询该组织下的志愿者
		System.out.println("tid----:"+tid);
		
		JSONObject obj = new JSONObject();
		List<Volunteer> vlst = null;
		Page<Object> page =  PageHelper.startPage(PageNo, rows);// 设置分页// 返回分页的一些相关信息
		
		//当前查询的状态 
		map.put("f", f);
		
        //通过组织的id查询组织下的志愿者id
		if(!tid.equals(null) && tid != ""){
			map.put("tid", Integer.parseInt(tid));
			l = volunteerService.getVidByProId(map); 
			//如果该活动下的参加的志愿者id lst 不为0时 查询志愿者的信息
			if(l!=null && l.size()!= 0 ){
				map.put("ids", l);//当前志愿者的id集合
				vlst = volunteerService.getAllVolun(map);
			}
		}else{
	    //查询志愿者列表 即所有志愿者 （未审核 或者 审核）
			 vlst = volunteerService.getAllVolun(map);
		}
		
		
		
		try {
			obj.put("total", page.getTotal());
			obj.put("rows", vlst);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return  obj.toString();
	}
	/**
	 * 志愿者审核
	 * @throws IOException 
	 */
	@RequestMapping(value="/examineVolun/{id}",method=RequestMethod.GET)
	public void examineVolun(@PathVariable Integer id,Map<String,Object> map,HttpServletResponse response) throws IOException{
		map.put("id", id);
		map.put("status", "1");//将审核完成的志愿者 status 状态改为1
		boolean f = volunteerService.updateVolun(map);
		response.getWriter().print(f);
	}
	/**
	 * 删除志愿者
	 * @param id
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/deleteVolunt/{id}",method=RequestMethod.GET)
	public void deleteVolunById(@PathVariable Integer id,Map<String,String> map,HttpServletResponse response) throws IOException{
		//删除志愿者
		boolean flag = volunteerService.deleteVolunt(id);
		System.out.println("删除的id:"+id);
		System.out.println("flag:"+flag);
		//map.put("flag", Boolean.toString(flag));
		//List<String> lst = new ArrayList<>();
		//lst.add(Boolean.toString(flag));
		//删除志愿者参加的志愿者项目
			response.getWriter().print(flag);
		//return lst;
	}
	@RequestMapping(value="/detailVolun/{id}",method=RequestMethod.GET)
	//@ResponseBody
	public void detailVolun(@PathVariable Integer id,Map<String,Object> map,HttpServletResponse response){
		
		//查询用户详细信息
		map.put("id", id);//将志愿者的id放入map中
		Volunteer vt = volunteerService.queryVolunteer(map);
		
		String str = "<table cellpadding='5' cellpacing='1'>"
				+ "<tr>"
				+ "<td>编号</td>"
				+ "<td>"+vt.getId()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>用户名</td>"
				+ "<td>"+vt.getName()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>性别</td>"
				+ "<td>"+vt.getGender()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>出生日期</td>"
				+ "<td>"+vt.getDate()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>学历</td>"
				+ "<td>"+vt.getDegree()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>政治面貌</td>"
				+ "<td>"+vt.getPoliticalstus()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>职位</td>"
				+ "<td>"+vt.getPosition()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>地址</td>"
				+ "<td>"+vt.getAddress()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>联系电话</td>"
				+ "<td>"+vt.getMobilephone()+"</td>"
				+ "</tr>"
				+ "<tr>"				
				+ "<td>家庭电话</td>"
				+ "<td>"+vt.getHomephone()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>电子邮箱</td>"
				+ "<td>"+vt.getEmail()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>QQ</td>"
				+ "<td>"+vt.getQq()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>服务时间</td>"
				+ "<td>"+vt.getVoluntime()+"</td>"
				+ "</tr>"
				
				+ "</table>";
		
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return vt;
		
	}
	/**\
	 *删除资料
	 * @throws IOException 
	 */
	@RequestMapping(value="deleteData")
	public void deleteData(@RequestParam(value="id",required=true) Integer id,
			               Map<String,Object> map,
			               HttpServletResponse response) throws IOException{
		map.put("table", "volun_data");//要删除表内容的表明
		map.put("id", id);//主键id
		boolean f = dataService.deleteRow(map);
		response.getWriter().print(f);
	}
	/**
	 * 跳到编辑资料页面
	 * id:主键的
	 * @return
	 */
	@RequestMapping(value="editData")
	public String editData(@RequestParam(value="id",required=true,defaultValue="") Integer id,
			               @RequestParam(value="type",required=true,defaultValue="") String type,
                           Map<String,Object> map){
		
		    map.put("id", id);
		    map.put("type", type);//资料的类别
		    map.put("c", "data");//当前为资料的内容编辑
			VolunData volunData = dataService.queryDataDetail(map);
			map.put("content", volunData.getDataContent());
			map.put("title", volunData.getDataTitle());
			map.put("id", volunData.getId());
			
			return "admin/editContent";
	}
	/**
	 * 资料保存submit 
	 *  * 媒体传真 3，在线培训 1，文件资料 2
	 * @param title 标题
	 * @param content 内容
	 * @param volunData 封装的javabean
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value="/saveData")
	public void saveData(@RequestParam(value="title",required=true,defaultValue="") String title,
            @RequestParam(value="content",required=true,defaultValue="") String content,
            @RequestParam(value="id",required=true,defaultValue="") Integer id,
            HttpServletResponse response,
             HttpServletRequest request
            ) throws IOException{
		
			String flag = request.getParameter("f");
			boolean f;
			if("update".equals(flag)){
				//当前为修改操作
				 VolunData volunData = new VolunData(id, title, content);
				 f = dataService.updateVolunData(volunData);
			}else{
				VolunData volunData = new VolunData(title, content, Utils.getNowDate(), request.getParameter("t"));
				 f = dataService.saveVolunData(volunData);
			}
			response.getWriter().print(f);
	}
	/**
	 * 查询资料
	 * 媒体传真 3，在线培训 1，文件资料 2
	 * @param request
	 * @param PageNo
	 * @param rows
	 * @param sort
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping("/getData")
	@ResponseBody
	public String queryAllData(HttpServletRequest request,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows,
			@RequestParam(value = "sort", required = true, defaultValue = "dataTime") String sort,
			@RequestParam(value = "order", required = true, defaultValue = "asc") String order,
			Map<String, Object> map) throws JSONException {
		
		String type = request.getParameter("type");
		System.out.println("当前的类型:"+type);
		
		Page<Object> page = PageHelper.startPage(PageNo, rows);// 设置分页// 返回分页的一些相关信息
		
		if((sort=sort.trim()).equals("dataTime")){
			sort="data_time";
		}
		//排序 列名 和 排序方式 资料类型
		map.put("sort", sort);
		map.put("order", order);
		map.put("type", type);
		List<VolunData> dlst = dataService.queryData(map);
		
		
		JSONObject obj = new JSONObject();
		
		obj.put("total", page.getTotal());
		obj.put("rows", dlst);
		
		//System.out.println("发布时间："+lst1.get(0).getDynamicTime());
		
		System.out.println("--"+obj.toString());
		
		request.setAttribute("page", page);
		request.setAttribute("type", type);//当前资料的类型

		return obj.toString();
	}
	/**
	 * 查询所有动态要闻
	 * easyui 前台传递两个参数 pageNo rows
	 * sort:排序的列名  order：排序方式，可以是 'asc' 或者 'desc'，默认值是 'asc'。
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDtyw")
	@ResponseBody
	public String queryAllDynamicNews(
			HttpServletRequest request,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows,
			@RequestParam(value = "sort", required = true, defaultValue = "dynamic_time") String sort,
			@RequestParam(value = "order", required = true, defaultValue = "asc") String order,
			Map<String, Object> map) throws JSONException {

		Page<Object> page = PageHelper.startPage(PageNo, rows);// 设置分页// 返回分页的一些相关信息
		
		
		//System.out.println(sort+","+order);
		if((sort=sort.trim()).equals("dynamicTime")){
			//System.out.println(1234);
			sort="dynamic_time";
		}
		//排序 列名 和 排序方式
		map.put("sort", sort);
		//System.out.println(sort+":");
		map.put("order", order);
		List<DynamicNews> lst1 = dynamicNewsService.getAllDynamicNews(map);
		
		
		JSONObject obj = new JSONObject();
		obj.put("total", page.getTotal());
		obj.put("rows", lst1);
		
		//System.out.println("发布时间："+lst1.get(0).getDynamicTime());
		
		System.out.println("--"+obj.toString());
		
		request.setAttribute("page", page);
		request.setAttribute("title", "动态要闻");

		return obj.toString();
	}
	/**
	 * 删除动态要闻
	 * @param id
	 * @param map
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteDtyw")
	public void deleteDtyw(@RequestParam(value="id",required=true,defaultValue="1") Integer id,
			Map<String,Object> map,
			HttpServletResponse response) throws IOException{
		String table = "volun_dynamicnews";//表名称
		map.put("id", id);//主键的id
		map.put("table", table);//表名
		boolean flag = dataService.deleteRow(map);
		response.getWriter().print(flag);
	}
	/**
	 * 跳转到添加页面
	 * 	请求参数 c: 当前的添加的是那一模块的内容
	 * ueditor 添加页面
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public String addContent(@RequestParam(value="c",required=false) String c,
			                 @RequestParam(value="t",required=false) String t,
			                 Map<String,Object> map){
		map.put("c", c);//当前添加的是那一块的内容
		map.put("t", t);//当前的添加的是那一类别的 
		return "admin/addContent";
	}
	
	@RequestMapping(value="/toSclbt")
	public String toSclbt(){
		return "admin/addLbt";
	}
	/**
	 * 跳转到编辑内容页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/editDtyw")
	/*@ResponseBody*/
	public String editDtyw(@RequestParam(value="id",required=true,defaultValue="") Integer id,
			              Map<String,Object> map,HttpServletRequest request){
		
		DynamicNews dynamicNews = dynamicNewsService.queryDetail(id);
		JSONObject jso = new JSONObject();
		map.put("content", dynamicNews.getDynamicContent());
		map.put("title", dynamicNews.getDynamicTitle());
		map.put("id", dynamicNews.getId());
		map.put("c", "dtyw");//当前为动态要闻内容的编辑
		
		/*request.setAttribute("content", dynamicNews.getDynamicContent());
		request.setAttribute("title", dynamicNews.getDynamicTitle());
		request.setAttribute("id", dynamicNews.getId());
		request.setAttribute("c", "dtyw");//当前为动态要闻内容的编辑
*/		
		//map.put("id", id);
		
		return "admin/editContent";
		//return jso.toString();
	}
	/**
	 * 获取json
	 * @return
	 */
	@RequestMapping(value="/getJSONDtyw")
	@ResponseBody
	public DynamicNews getJSON(@RequestParam(value="id",required=true,defaultValue="") Integer id) throws JSONException {
		DynamicNews dynamicNews = dynamicNewsService.queryDetail(id);
		//map.put("dynamicNews", dynamicNews);//将查询出的动态要闻放入请求与中
		JSONObject jso = new JSONObject();
		jso.put("dynamicNews", dynamicNews);
		
		List<DynamicNews> l = new ArrayList<>();
		l.add(dynamicNews);
		
		System.out.println("id:"+jso.toString());
		
		return dynamicNews;
	}
	
	/**
	 * 保存添加 的内容
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveDynamicNews")
	public void saveDynamicNews(@RequestParam(value="title",required=true,defaultValue="") String title,
			            @RequestParam(value="content",required=true,defaultValue="") String content,
			            @RequestParam(value="id",required=false) Integer id,
			            HttpServletResponse response,
			             HttpServletRequest request
			            ) throws IOException{
		
		//判断标题和内容是否为空
		if(title == "" || content == ""){
			response.getWriter().print("<script>alert(\"标题和内容不能有空!\")</script>");
		}
		String f = request.getParameter("f");
		boolean flag;
		if("update".equals(f)){
			//当前为修改操作
			DynamicNews dynamicNews = new DynamicNews(id,title, content);
			System.out.println("update:"+dynamicNews);
			flag = dynamicNewsService.update(dynamicNews);
		}else{
			//添加保存操作
			DynamicNews dynamicNews1 = new DynamicNews(title,content,Utils.getNowDate());
			flag = dynamicNewsService.saveAdd(dynamicNews1);
		}
		response.getWriter().print(flag);
		
	}
	/**
	 * 返回到活动管理
	 * @return
	 */
	@RequestMapping(value="/toProject")
	public String toProject(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		System.out.println("id:"+id);
		map.put("id", id);
		return "admin/prolst";
	}
	/**
	 * 返回到留言列表
	 * @return
	 */
	@RequestMapping(value="/toComment")
	public String toComment(){
		return "admin/comlst";
	}
	/**
	 * 删除一条留言
	 * @param id
	 * @throws IOException 
	 */
	@RequestMapping("/deleteComment")
	public void deleteComment(@RequestParam(value="id",required=true,defaultValue="") Integer id,
			HttpServletResponse response,
			Map<String,Object> map) throws IOException{
    
		map.put("id", id);//主键id
		map.put("table", "volun_message");//要删除的数据表
		boolean f = dataService.deleteRow(map);
		response.getWriter().print(f);
	}
	/**
	 * 管理留言回复
	 * @param fkid
	 * @param reply
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="submitReply")
	public void submitReply(@RequestParam(value="id",required=true,defaultValue="1") Integer fkid,
			                @RequestParam(value="reply",required=true,defaultValue="") String reply,
                            HttpServletResponse response,
                            Map<String,Object> map) throws IOException{
		Reply r = new Reply(Utils.ip(), reply, Utils.getNowDate(), fkid);
		boolean f = adminService.replyYhly(r);
		            map.put("id", fkid);
		            messageService.updateMessage(map);//将留言表状态改为已回复
		response.getWriter().print(f);
	}
	
	/**
	 * 查询所有留言
	 * @param request
	 * @param PageNo
	 * @param rows
	 * @param sort
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getlyly")
	@ResponseBody
	public String getAllComment(HttpServletRequest request,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows,
			@RequestParam(value = "sort", required = true, defaultValue = "msgTime") String sort,//排序的时间 和 是否回复 msgStatus
			@RequestParam(value = "order", required = true, defaultValue = "asc") String order,//
			Map<String, Object> map) {
		
	    Page<Object> page = PageHelper.startPage(PageNo, rows);// 设置分页// 返回分页的一些相关信息
		//按照排序的时间 进行排序
		if((sort=sort.trim()).equals("msgTime")){
			sort="msg_time";
		}
		//按照是否回复 进行排序
		if((sort=sort.trim()).equals("msgStatus")){
			sort="msg_status";
		}
		//排序 列名 和 排序方式
		map.put("sort", sort);
		map.put("order", order);
		List<VisitorMsg> mlst = messageService.getAllMessage(map);
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("total", page.getTotal());
			//easyui 要求返回的 json 格式
			obj.put("rows", mlst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("--"+obj.toString());

		return obj.toString();
	}
	/**
	 * 查询所有志愿活动
	 * @param request
	 * @param PageNo
	 * @param rows
	 * @param sort
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getSyhd")
	@ResponseBody
	public String getSyhd(HttpServletRequest request,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer PageNo,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows,
			@RequestParam(value = "sort", required = true, defaultValue = "proTime") String sort,//排序的时间 和 是否回复 msgStatus
			@RequestParam(value = "order", required = true, defaultValue = "asc") String order,//
			@RequestParam(value = "id", required = false) Integer id,//有id的话 判断当前用户为队长oradmin 当前有队长查询查看 所属的或有活动 则 查看所有活动
			Map<String, Object> map){
		
		System.out.println("Syhd:id:"+id);
		
		Page<Object> page = PageHelper.startPage(PageNo, rows);
		//按照排序的时间 进行排序
		if((sort=sort.trim()).equals("proTime")){
			sort="pro_time";
		}
		//按照状态 招募中 进行中 已完成
		if((sort=sort.trim()).equals("proStatus")){
			sort="pro_status";
		}
		//按照活动的类别
		if((sort=sort.trim()).equals("proFkid")){
			sort="pro_fkid";
		}
		
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		System.out.println(admin+"-------------------------");
		
		if(admin.getRole().equals("各队负责人")){
			//不为空则当前为队长登录 查询
		    map.put("team", admin.getTeam());//志愿者队伍的id放入map中
		}else{
			//管理员查询当前队伍的志愿者活动
		    map.put("teamid", id);//志愿者队伍的id放入map中
		}
		
		//排序 列名 和 排序方式
		map.put("sort", sort);
		map.put("order", order);
		List<TeamProject> plst = projectServices.getAllProject(map);
		
		JSONObject obj = new JSONObject();
		
		//easyui 要求返回的 json 格式
		try {
			obj.put("total", page.getTotal());
			obj.put("rows", plst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("--"+obj.toString());

		return obj.toString();
	}
	/**
	 * 跳转到组织管理页面
	 * @return
	 */
	@RequestMapping(value="/toOrganization")
	public String toOrganization(){
		
		return "admin/org";
	}
	/**
	 * 查询 所有组织
	 * @param pageNo
	 * @param rows
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getZzlb")
	@ResponseBody
	public String getZzlb(@RequestParam(value="page",required=true,defaultValue="1") Integer pageNo,
			              @RequestParam(value="rows",required=true,defaultValue="10") Integer rows,
			              Map<String,Object> map) throws JSONException {
		
	    Page<Object> page = PageHelper.startPage(pageNo, rows);// 设置分页// 返回分页的一些相关信息
	    List<Team> tlst = teamService.getAllTeam(map);
	    
	    JSONObject jsonObject = new JSONObject();
	    //easyui 要求返回的 json 格式
	    jsonObject.put("total", page.getTotal());
	    jsonObject.put("rows", tlst);
		
		return jsonObject.toString();
	}
	/**
	 * 根据id查询组织详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getZzDetail")
	@ResponseBody
	public String getZzDetail(@RequestParam(value="id",required=true,defaultValue="1") Integer id,
			                 Map<String,Object> map){
		
		map.put("id", id);
	    Team t = teamService.getDetailTeam(map);
        JSONObject jsonObject = new JSONObject(t);
		return jsonObject.toString();
	}
	/**
	 * 查询所有的 项目类别
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getXmlb")
	@ResponseBody
	public String getXmlb(HttpServletRequest request,Map<String,Object> map){

		List l = new ArrayList<>() ;//list是可变长的
		//Object [] o = new Object[6];//数组是不可变长的
		List<ProjectType> t = projectTypeService.getAllProtype();
	 	for (int i = 0; i < t.size(); i++) {
        	System.out.println("------xmlb");
        	JSONObject jsonObject = new JSONObject(t.get(i));
        	l.add(jsonObject);
        	//o[i] = jsonObject;
        	System.out.println(jsonObject);
        	
        }
		
		return null;
	}
	/**
	 * 司法局作为超级管理员 前台通知我的事项
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/wdsx")
	@ResponseBody
	public int wdsx(HttpServletRequest request,Map<String,Object> map1,Map<String,Object> map2){
	    //查询我的事项中未审核的志愿者
		map1.put("f", "0");
		int i = volunteerService.getAllVolun(map1).size();
        //我的事项中未审核活动数量
		map2.put("status", "0");
		int j = projectServices.getAllProject(map2).size();
		
		return i+j;
	}
	
	/**
	 * 跳转到组织管理页面
	 * @return
	 */
	@RequestMapping(value="/toWdsx")
	public String toWdsx(){
		
		return "admin/wdsx";
	}
	
	/**
	 * 我的事项列表
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/queryWdsx")
	@ResponseBody
	public String queryWdsx(@RequestParam(value="page",required=true,defaultValue="1") Integer pageNo,
							@RequestParam(value="rows",required=true,defaultValue="10") Integer rows,
							Map<String,Object> map1,
							Map<String,Object> map2) throws JSONException {
		
		   List<Mdsx> mlst = new ArrayList<Mdsx>();
		
		   //查询我的事项中未审核的志愿者
		    Page<Object> page1 = PageHelper.startPage(pageNo, rows);// 设置分页// 返回分页的一些相关信息
		    map1.put("f", "0");
		    List<Volunteer> vlst = volunteerService.getAllVolun(map1);
		    long t1 =  page1.getTotal();
			//未审核的志愿者
		    for(Volunteer  v : vlst){
		    	Mdsx mdsx = new Mdsx();
		    	mdsx.setId(v.getId());
		    	mdsx.setSxmc(v.getName());//志愿者姓名 即审核事项名称
		    	mdsx.setSxsj(v.getRegisTime());//志愿者注册时间 及事项时间
		    	mdsx.setSxlx("注册");
		    	mlst.add(mdsx);
		    }
		    
		    
	        //我的事项中未审核活动数量
			Page<Object> page2 = PageHelper.startPage(pageNo, rows);// 设置分页// 返回分页的一些相关信息
			map2.put("status", "0");
			List<TeamProject> plst = projectServices.getAllProject(map2);
		    long t2 =  page2.getTotal();
		    for(TeamProject  p : plst){
		    	Mdsx mdsx = new Mdsx();
		    	mdsx.setId(p.getId());
		    	mdsx.setSxmc(p.getProTitle());//活动的标题 即审核事项名称
		    	mdsx.setSxsj(p.getProTime());//活动发布的时间 及事项时间
		    	mdsx.setSxlx("活动");
		    	mlst.add(mdsx);
		    }
		
		    JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", t1+t2);
			jsonObject.put("rows",mlst );
			
		return jsonObject.toString();
	}
	
	
	/**
	 * 管理员保存活动 提交
	 * @param pro
	 * @param request
	 * @param response
	 * @param proid 为修改时传递的志愿活动的主键id
	 * @throws IOException
	 */
	@RequestMapping("/tjhd")
	@ResponseBody
	public void tjhd(TeamProject pro,HttpServletRequest request,HttpServletResponse response,
			        @RequestParam(value="id",required=false) Integer proid) throws IOException{
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		System.out.println(proid);
		boolean f = false;
		if(proid!= null){
			//当前为修改保存操作
			pro.setProPubteam(admin.getTeam().toString());//发布队伍的id
			System.out.println(pro);
			f = projectServices.updateProjectByEntity(pro);
		}else{
			//添加保存操作
			pro.setProTime(Utils.getNowDate());//发布时间
			pro.setProStatus("0");//未审核
			pro.setProPubteam(admin.getTeam().toString());//发布队伍的id
			System.out.println(pro);
			f = projectServices.addProjectByAdmin(pro);
		}
		 response.getWriter().print(f);
	}
	/**
	 * 删除志愿者活动
	 * @param id
	 * @param map
	 * @throws IOException 
	 */
	@RequestMapping("/deletePro")
	public void deletePro(@RequestParam(value="proid",required=true,defaultValue="1") Integer proid,
			             Map<String,Object> map,HttpServletResponse response) throws IOException{
		map.put("proid", proid);
		boolean f = projectServices.deletePro(map);
		response.getWriter().print(f);
	}
	
	/**
	 * 管理员事项审核
	 * @param request
	 * @param response
	 * @param map
	 * @throws IOException 
	 */
	@RequestMapping("/sxsh")
	public void sxsh(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
		Integer id = Integer.parseInt(request.getParameter("id"));//事项的id
		String sxlx = request.getParameter("sxlx");//事项类型
		boolean f = false;
		map.put("id", id);//主键的id
		if("活动".equals(sxlx)){
			map.put("proStatus", "1");//将状态改为已审核状态
			f = projectServices.updateProject(map);
		}else if("注册".equals(sxlx)){
			map.put("regisStatus", "1");//将状态改为已审核状态
			f = volunteerService.updateVolun(map);
		}
		
		response.getWriter().print(f);
	}

	/**
	 队长对队伍的信息进行修改 保存
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveMyTeam")
	public void saveMyTeam(Team t,HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletResponse response) throws IOException{
		
		//表示未修改队伍的logo
		if(file.getSize() == 0){
			t.setTeamLogo(null);
		}else{
			String l = PropertyUtil.getProperty("teamImg");
			t.setTeamLogo("image/team/"+file.getOriginalFilename());
			// 转存文件
			Utils.uploadFile(file, l);
		}
		

        response.setContentType("text/html;charset=utf-8");
        boolean f = teamService.updateTeam(t);
        if(f){
        	response.getWriter().write("<script>alert('操作成功!');window.history.go(-1)</script>");
        }else{
        	response.getWriter().write("<script>alert('操作失败!');</script>");
        }
	}
	
	@RequestMapping("/uploadLbt")
	public void uploadLbt(@RequestParam(value="file") MultipartFile[] files,Map<String,Object> map){
		
		String imgAddr = PropertyUtil.getProperty("lunboImg");
		
		if(files.length !=0 && files !=null){
			Lunbo lb = new Lunbo();
		    //lb.setLunImage(lunImage);
		}
		
	}

	
}
