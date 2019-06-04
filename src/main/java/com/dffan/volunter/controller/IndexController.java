package com.dffan.volunter.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dffan.volunter.domain.BaseNews;
import com.dffan.volunter.domain.DynamicNews;
import com.dffan.volunter.domain.Inform;
import com.dffan.volunter.domain.Team;
import com.dffan.volunter.domain.TeamProject;
import com.dffan.volunter.domain.VolunData;
import com.dffan.volunter.service.BaseNewsService;
import com.dffan.volunter.service.DataService;
import com.dffan.volunter.service.DynamicNewsService;
import com.dffan.volunter.service.InformService;
import com.dffan.volunter.service.TeamService;
import com.dffan.volunter.service.ProjectServices;
/**
 * 首页控制器 进入到首页
 * 初始化
 * @author admin
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private DynamicNewsService dynamicNewsService;//动态要闻
	@Autowired
	private BaseNewsService baseNewsService;//基层速递
	@Autowired
	private ProjectServices projectServices;//活动招募
	@Autowired
	private InformService informService;
	@Autowired
	private DataService dataService;
	@Autowired
	private TeamService teamService;	
	
	@RequestMapping
	public String toIndex(HttpServletRequest request){
		System.out.println("请求测试------");
		Map<String,Object> map = new HashMap<String,Object>();
	    map.put("current", "index");//放入一个值区分是否为首页
	    map.put("ysh", "ysh");//首页活动招募的栏目显示的是已审核的活动
	    // PageHelper.startPage(1, 8);//首页只显示8条信息
				
	    //动态要闻
	    List<DynamicNews> dynamiclst =  dynamicNewsService.getAllDynamicNews(map);
		//基层速递
		List<BaseNews> baselst = baseNewsService.getAllBaseNews(map);
		//活动招募
		List<TeamProject> projectlst = projectServices.getAllProject(map);
		//平台公告
		List<Inform> informlst = informService.getAllInform(map);
		//在线培训
		List<VolunData> onlinelst = dataService.getAllOnline(map);
		//文件资料
		List<VolunData> filelst = dataService.getAllFile(map);
		//媒体传真
		List<VolunData> medialst = dataService.getAllMedia(map);
		//所有组织
        List<Team> orglst = teamService.getAllTeam(map);

		request.setAttribute("navli", "0");//选中栏目 css li的位置
        request.setAttribute("title", "index");//是否为首页
		request.setAttribute("orglst", orglst);
		request.setAttribute("projectlst", projectlst);
		request.setAttribute("medialst", medialst);
		request.setAttribute("dynamicnews", dynamiclst);
		request.setAttribute("datalst", filelst);
		request.setAttribute("InformLst", informlst);
		request.setAttribute("onlinelst", onlinelst);
		request.setAttribute("BaseNewslst", baselst);
		
		return "index";
	}
	
	@RequestMapping("/VerifyImage")
	public void VerifyImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//在内在缓存中生成一个图像
		int width = 60;
		int height = 20;
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics2D g = buffImg.createGraphics();
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        Font font=new Font("Dialog",Font.PLAIN,18);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width-1,height-1);
        //产生160条干扰线，图像中的认证码，不被看到
        g.setColor(Color.CYAN);
        for(int i = 0;i < 160;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x,y,x1,y1);
        }
        StringBuffer randomCode = new StringBuffer();//保存验证码
        int red = 0,green = 0,blue = 0;
        //控制:在图片上画出4个数字
        
       for(int i = 0;i < 4;i++){
            String strRand = String.valueOf(random.nextInt(10));
            red = random.nextInt(110);
            green = random.nextInt(50);
            blue = random.nextInt(50);
            g.setColor(new Color(red,green,blue));
            g.drawString(strRand,13*i+6,16);
            randomCode.append(strRand);
        }
        

        ServletContext application = request.getServletContext();
        //保存随机码到全局存储区间
        application.setAttribute("VerifyCode",randomCode.toString());
        System.out.println("set VerifyCode=" + request.getServletContext().getAttribute("VerifyCode"));
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");//很重要，指明返回的数据格式类型
       //将图像输出到Servlet输出流中，response的响应对象是一个图片
        	ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(buffImg,"jpeg",sos);
			sos.close();
		}
	}
	
