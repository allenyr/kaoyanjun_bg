package utils;

import globle.Constant;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.New;

import Model.Infom;
import Model.Users;

public class FileUtils {
	
	public static final String NAME1 = "allen"; 
	
	public static void saveStringToFile(String string) {
		String temp =string+"\r\n";
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(Constant.TXT_PATH+"pay.txt",true);//true表示在文件末尾追加  
			fos.write(temp.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//保存聊天记录
	public static void saveChatToFile(String sign,String record) {
		System.out.println(sign); 
		String temp =record+"\r\n";
		File dir = new File(Constant.CHAT_PATH +"\\"+sign+"\\");// 目录路径
        if (!dir.exists()) {// 如果不存在，则创建路径名
        	dir.mkdirs();
        }
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(Constant.CHAT_PATH +"\\"+sign+"\\"+TimeUtils.getCurrentTimeBaseDay()+".txt",true);//true表示在文件末尾追加  
			fos.write(temp.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	  * @param filePathAndName 含路径文件名
	  * @param fileContent   写入文件的字符串
	  */
	public static void writeFile(String dirPath,String filePathAndName, String fileContent) {
		try {
			File dir = new File(dirPath);// 目录路径
			if (!dir.exists()) {// 如果不存在，则创建路径名
				dir.mkdirs();
			}
        
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			//追加输入,定义编码
			OutputStreamWriter oWriter = new OutputStreamWriter(new FileOutputStream(f,true), "UTF-8");
			BufferedWriter bWriter = new BufferedWriter(oWriter);
			bWriter.write(fileContent);
			bWriter.close();
			} catch (Exception e) {
				System.out.println("写文件内容操作出错");
				e.printStackTrace();
			}
	 }  
	
	
	public static List<Infom> getFiles(String filePath){
		List<Infom> filelist = new ArrayList<Infom>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files){     
			//new Infom必须放里面
			Infom infom = new Infom();
			if(file.isDirectory()){
				infom.setNature("0");
			}else{
				infom.setNature("1");
			}
			infom.setName(file.getName());
			filelist.add(infom);   
		}
		return filelist;
	}
	
}
