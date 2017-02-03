package login;

import globle.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class PhotoTest
 */
@WebServlet("/PhotoTest")
public class PhotoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private String path;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhotoTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		System.out.println("�յ�get����");
		request.setCharacterEncoding("UTF-8"); // ���ô�����������ı����ʽ
		response.setContentType("text/html;charset=UTF-8"); // ����Content-Type�ֶ�ֵ
		String what = request.getParameter("what");
		int what1 = Integer.parseInt(what); 
		switch (what1) {
		case 1:
			path=Constant.HEAD_PATH;
				
			break;
		case 2:
			path=Constant.IMG_PATH;

		default:
			break;
		}
		
        //��ô����ļ���Ŀ������  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�  
//        String path = request.getSession().getServletContext().getRealPath("/upload");  
//        String path = "E:/apache-tomcat-7.0.68/webapps/ROOT/img";  
        //������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�  
        factory.setRepository(new File(path));  
        //���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�  
        factory.setSizeThreshold(1024*1024);  
        //�ϴ��������ࣨ��ˮƽAPI�ϴ�������  
        ServletFileUpload upload = new ServletFileUpload(factory);  
          
        try{  
            //���� parseRequest��request������  ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(new ServletRequestContext(request));  
            for(FileItem item:list){  
                //��ȡ���������֡�  
                String name = item.getFieldName();  
                //�����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����  
                if(!name.equals("upload")){  
                    //��ȡ�û�����������ַ�����  
                    String value = item.getString();  
                    request.setAttribute(name, value);  
                }  
                //���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���  
                else{   
                    //��ȡ·����  
                    String value = item.getName();  
                    //ȡ�����һ����б�ܡ�  
                    int start = value.lastIndexOf("\\");  
                    //��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�  
                    String filename = value.substring(start+1);              
                    
                    request.setAttribute(name, filename);  
                     
                    /*�������ṩ�ķ���ֱ��д���ļ��С� 
                     * item.write(new File(path,filename));*/  
                    //�յ�д�����յ��ļ��С�  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                    InputStream in = item.getInputStream();  
                      
                    int length = 0;  
                    byte[] buf = new byte[1024];  
                    System.out.println("��ȡ�ļ�����������:"+ item.getSize());  
                    response.getWriter().print("ok:" + filename );
                    while((length = in.read(buf))!=-1){  
                        out.write(buf,0,length);  
                    }  
                    in.close();  
                    out.close();  
                    System.out.println("�ϴ��ɹ�");
                }  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}