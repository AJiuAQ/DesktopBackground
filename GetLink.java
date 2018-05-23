package Main;
/* 
 * 该类通过正则表达式对必应主页源码分析 得到背景图片链接
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetLink {
	boolean con = true;//网络连接状态
	String main_link;//必应主页链接
	URL url;
	String result = null;//从必应主页获取的网页源码
	String line = null;
	Pattern pa;//正则表达式
	String regEx;
	Matcher ma;
	String img_link = null;//匹配得到的图片链接
	public void init() {
		try {//将网页源码保存到result 如果无法连接 con 为false
			main_link = "https://cn.bing.com/";
			url = new URL(main_link);
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			while((line = br.readLine()) != null) {
				result += line + "\r\n";
			}
			br.close();
			isr.close();
			is.close();
			con = true;
//			System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			con = false;
			System.out.println("0");
			e.printStackTrace();
		}
	}
	public boolean isConnect() {//返回con 网页是否连接成功
		return con;
	}
	public String getLink() {//用正则表达式匹配得到背景图片链接
		regEx = "url:\\s.+?jpg";
//		background-image:url(/az/hprichbg/rb/NamibFace_ZH-CN6782882876_1920x1080.jpg)
//		background-image:url
		pa = Pattern.compile(regEx);
		ma = pa.matcher(result);
		if(ma.find()) {
			System.out.println(ma.group());
			img_link = "https://cn.bing.com/" + ma.group().substring(ma.group().indexOf("\"") + 1, ma.group().length());
		}
		System.out.println(img_link);
		return img_link;
	}
	public static void main(String[] args) {
		
	}
}
