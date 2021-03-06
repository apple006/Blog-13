package springMVC.NLoveB.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
/*
 * 添加cookie 删除cookie 查找cookie 是否存在
 */
/**
 * 
 * @ClassName:  cookieUtils   
 * @Description:TODO(添加cookie 删除cookie 查找cookie 是否存在)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:18:05   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public final class cookieUtils {
	private cookieUtils(){
		
	}
	//添加
	public static void addCookie(Cookie cookie,Cookie[] cookies,HttpServletResponse response){
		if(null==cookies ||cookies.length==0){
			response.addCookie(cookie);
		}else{
			int i = 1;
			for(Cookie co:cookies ){
				//表示用户id一致，并且商品id一致
				if(co.getName().equals(cookie.getName())){
					co.setValue(cookie.getValue());
					response.addCookie(co);
					co.setMaxAge(86400);
					i=2;
					break;
				}
			}
			//如果循环完毕，都没有匹配
			if(i==1){
				response.addCookie(cookie);
			}
		}		
	}
	
	//删除
	public static void delCookie(String cookiename,Cookie[] cookies,HttpServletResponse response){
		if(null==cookies ||cookies.length==0){
		}else{
			for(Cookie co:cookies ){
				//表示用户id一致，并且商品id一致
				if(co.getName().equals(cookiename)){
					co.setMaxAge(0);
					break;
				}
			}
		}		
	}
	
	//验证某个cookie是否过期，未过期就返回起健值对
	public static Map<String,String> cookie(String cookiename,Cookie[] cookies){
		Map<String,String> map = new HashMap<String,String>();
		if(cookies == null||cookies.length==0){
			return null;
		}else{
			for(Cookie co:cookies ){
				/*
				 * 遍历查询
				 */
				if(co.getName().equals(cookiename)){
					map.put(co.getName(),co.getValue());
					return map;
				}
			}
			//如果循环完毕，都没有该cookie
			return null;
		}		
	}
}
