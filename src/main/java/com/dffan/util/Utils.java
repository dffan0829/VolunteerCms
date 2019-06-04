package com.dffan.util;

import java.io.File;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
/**
 * 系统工具类
 * @author admin
 *
 */
public class Utils {

	//获取当前系统时间
	public static String getNowDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	//获取当前系统时间 不包括秒
	public static String getNowDateExcapeSs(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	//对从数据库取出的内容 因太长导致前台换行出现的js错误进行处理
	public static String format(String s) {
		System.out.println("处理字符串");
		if (s != null && s.length() > 0) {
			s = s.replaceAll("(\r|\n|\r\n|\n\r)", " ");
			s = s.replaceAll("\"", "\\\\" + "\"");
			s = s.replaceAll("\'", "\\\\" + "\'");
			return s;
		} else {
			return "";
		}
	}
	/**
	 * 获取ip地址
	 *
	 * @return
	 */
	public static String ip() {

		String ip = new Socket().getLocalAddress().toString();

		return ip;

	}
	/**
	 * 前台页面日期大小的比较
	 * @param nowDate
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean compareDate(String nowDate,String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date.equals("") || date == null){
			return false;
		}

		Date now = sdf.parse(nowDate);
		Date d = sdf.parse(date);
		System.out.println(now+","+d+","+now.getTime()+","+d.getTime());
		if(now.getTime() < d.getTime()){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	public static void uploadFile(MultipartFile file,String l){
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = l+file.getOriginalFilename();
				File tempFile = null;
				tempFile = new File(filePath);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				if (!tempFile.exists()) {
					tempFile.createNewFile();
				}
				// 转存文件
				file.transferTo(tempFile);
				System.out.println(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
