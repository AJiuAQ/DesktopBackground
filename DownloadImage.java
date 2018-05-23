package Main;
/* 
 * 该类将获取的图片链接下载 保存到存储路径  命名为当天日期
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
//下载图片
public class DownloadImage {
	OutputStream os;
	InputStream is;
	String urlString, filename;
	GetLink gt;//获取图片链接类
	Date d;
	public Date reDate() {
		d = new Date();
		return d;
	}
	public boolean init(String savepath) {
		boolean sign = true;//判断当天有没有下载过图片
		d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateyMd = sdf.format(d);
		System.out.println(dateyMd);
		gt = new GetLink();
		gt.init();
		filename = dateyMd + ".jpg";
		File f = new File(savepath + filename);
		System.out.println(f.getName());
		if(f.exists())
			sign = false;
		return sign;
	}
	public String download(String savepath) {//下载图片
		try {
			urlString = gt.getLink();//得到链接
			URL url = new URL(urlString);//建立网络连接
			URLConnection con = url.openConnection();
			con.setConnectTimeout(5000);
			is = con.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			File f = new File(savepath);
			os = new FileOutputStream(f.getPath() + "\\" + filename);
			while((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);;
			}
			os.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;
	}
	public static void main(String[] args) {
	}
}
