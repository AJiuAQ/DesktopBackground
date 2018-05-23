package Main;
/* 
 * 该类创建（修改）一个批处理文件 使用 Process 运行批处理文件 更改桌面背景
 */
import java.io.FileOutputStream;
import java.io.IOException;
//设置背景
public class SetBackground {
	Process process;
	public void init(String imgurl) {//批处理文件写入
		String str1 = "@echo off\r\n";
		String str2 = "title 更改桌面背景的批处理：\r\n";
		String str3 = "reg add \"hkcu\\control panel\\desktop\" /v wallpaper /d \"" + imgurl + "\" /f\r\n";
		String str4 = "RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters\r\n";
		String[] cmd = {str1, str2, str3, str4};
//		String[] cmd = new String[] { "cmd.exe", "/C", "ipconfig" };
//		String cmd = "cmd.exe /C start " + imgurl;
		try {
			FileOutputStream f = new FileOutputStream("D:\\BingBackground\\A.bat");
			for(int i = 0; i < 4; i++) {
				f.write(cmd[i].getBytes());
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setBackground() {//运行批处理文件
		try {
			process = Runtime.getRuntime().exec("D:\\BingBackground\\A.bat");
			process.waitFor();
			int i = process.exitValue();
			if(i == 0)
				System.out.println("1");
			else
				System.out.println("2");
			process.destroy();
			process = null;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
