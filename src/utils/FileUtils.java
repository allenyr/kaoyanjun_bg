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
			fos = new FileOutputStream(Constant.TXT_PATH+"pay.txt",true);//true��ʾ���ļ�ĩβ׷��  
			fos.write(temp.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//���������¼
	public static void saveChatToFile(String sign,String record) {
		System.out.println(sign); 
		String temp =record+"\r\n";
		File dir = new File(Constant.CHAT_PATH +"\\"+sign+"\\");// Ŀ¼·��
        if (!dir.exists()) {// ��������ڣ��򴴽�·����
        	dir.mkdirs();
        }
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(Constant.CHAT_PATH +"\\"+sign+"\\"+TimeUtils.getCurrentTimeBaseDay()+".txt",true);//true��ʾ���ļ�ĩβ׷��  
			fos.write(temp.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	  * @param filePathAndName ��·���ļ���
	  * @param fileContent   д���ļ����ַ���
	  */
	public static void writeFile(String dirPath,String filePathAndName, String fileContent) {
		try {
			File dir = new File(dirPath);// Ŀ¼·��
			if (!dir.exists()) {// ��������ڣ��򴴽�·����
				dir.mkdirs();
			}
        
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			//׷������,�������
			OutputStreamWriter oWriter = new OutputStreamWriter(new FileOutputStream(f,true), "UTF-8");
			BufferedWriter bWriter = new BufferedWriter(oWriter);
			bWriter.write(fileContent);
			bWriter.close();
			} catch (Exception e) {
				System.out.println("д�ļ����ݲ�������");
				e.printStackTrace();
			}
	 }  
	
	
	public static List<Infom> getFiles(String filePath){
		List<Infom> filelist = new ArrayList<Infom>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files){     
			//new Infom���������
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
