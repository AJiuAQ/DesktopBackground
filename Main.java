package Main;
/* 
 * 小程序功能：下载必应主页背景图片，设置为桌面背景
 */
public class Main {
	public static String savepath = "D:/BingBackground/";//保存路径
	public static void main(String[] args) {
		DownloadImage di = new DownloadImage();//下载图片类
		SetBackground sb = new SetBackground();//设置背景类
		String log = null;//日志写入内容
		LogWrite lr = new LogWrite();//日志写入类
		lr.init();
		if(di.init(savepath)) {//判断网页是否可以连接
			try {
				for(int i = 1; ;i++) {//判断网页是否可以连接 不成功的话2s后再次尝试 成功下载图片
					di.gt.init();
					if(di.gt.isConnect()) {
						log = "网络连接成功" + di.reDate();
						lr.logWrite(log);
						break;
					}
					log = "第" + i + "连接失败" + di.reDate();
					lr.logWrite(log);
					Thread.sleep(2000);
				}
				di.download(savepath);
				log = "图片下载完成：" + di.gt.img_link;
				lr.logWrite(log);
				sb.init(savepath + di.filename);
				for(int k = 1; k < 10; k++) {//设置为背景  因为 批处理方法不够稳定  所以 尝试8次 每2s一次
					sb.setBackground();
					lr.logWrite(log);
					log = "第" + k +  "次更换背景。";
					Thread.sleep(2000);
				}
				log = "背景已更换";
				lr.logWrite(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {//如果图片已被下载 直接退出程序
			log = "图片已被下载，退出程序。时间：" + di.reDate();
			lr.logWrite(log);
		}
		lr.logClose();
	}
}
