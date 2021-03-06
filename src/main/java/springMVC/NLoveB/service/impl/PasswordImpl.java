package springMVC.NLoveB.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springMVC.NLoveB.service.iter.KeymIter;
import springMVC.NLoveB.service.iter.PasswordIter;
/**
 * 
 * @ClassName:  PasswordImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:26   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("utilpassword")
public class PasswordImpl implements PasswordIter{
	@Resource(name= "keym")
	private KeymIter keym;
	
	@Override
	public String getPass(int count){
		/*
		 * 0-9 A-Z a-z
		 */
		//String pass = "qrs^t2i<jkl(mn%8PL[KJHGF]abc67)efg#hN90?QWuv@wxyz+1ER-T=Y/d:opDSAZ*XCVB>345U!IO=M";
		String pass = "qrst2ijklmn8PLKJHGFabc67efghN90QWuvwxyz1ERTYdopDSAZXCVB345UIOM";
		String userpass="";
		for(int i=0;i<count;i++){
			userpass = userpass + pass.charAt((int) (Math.random() * 62));
		}
		return userpass;
	}
//	public static void main(String[] args) throws ParseException{
//		System.out.println(getEmailValue(getEmailKey(1)));
//
//	}
	@Override
	public String getSecretkey(int userid){
		//获得一个数据数
		String pass = getPass(64);
		int pl = pass.length();
		//获得时间
		String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String mishi = userid+"#"+format;
		mishi = keym.jiami("564565", mishi);
		int len = mishi.length();
		String str = "";
		int k = 0;
		for(int i=0;i<len;i++){
			if(k<pl){
				str=str+pass.charAt(k);
				k++;
			}
			str=str+mishi.charAt(i);
		}
		return str;
	}
	@Override
	public Map<String,Object> getDecryptKey(String key) throws ParseException{
		//获得这个key
		int length = key.length();
		int len = length/2;
		String str = "";
		for(int i=1;i<length;i+=2){
			str=str+key.charAt(i);
		}
		String jiemi = keym.jiemi("564565", str);
		int indexOf = jiemi.indexOf("#");
		int userid = Integer.parseInt(jiemi.substring(0, indexOf));
		String strdate = jiemi.substring(indexOf+1, jiemi.length());
		
		//将字符串转换为时间吧
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = format.parse(strdate);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("date", date);
		return map;
	}
	//判断时间是否过期（验证email链接是否过期，参数是emailvalue）
	@Override
	public boolean overdate(Date starttime) {
		long second = 0;
		int ends = 60;
	
		Date endtime = new Date();
		long diff = endtime.getTime() - starttime.getTime(); 	//相差多少毫秒  
		second = diff / (1000 * 60 );  								//多少分钟

		if(second >= ends || second < 0){
			return false;		//如果大于定义的分钟标识已过期
		}else{
			return true;
		}
	}
}
