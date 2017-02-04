package globle;

public class Constant {
	
	public static final boolean LOCAL = false; 
	public static final String HEAD_LOCAL_PATH = "E:\\apache-tomcat-7.0.68\\webapps\\ROOT\\head\\";
	public static final String HEAD_SERVER_PATH = "C:\\Program Files\\apache-tomcat-7.0.69\\webapps\\ROOT\\head\\";
	public static final String HEAD_PATH = LOCAL ? HEAD_LOCAL_PATH : HEAD_SERVER_PATH; 
	
	public static final String IMG_LOCAL_PATH = "E:\\apache-tomcat-7.0.68\\webapps\\ROOT\\img\\";
	public static final String IMG_SERVER_PATH = "C:\\Program Files\\apache-tomcat-7.0.69\\webapps\\ROOT\\img\\";
	public static final String IMG_PATH = LOCAL ? IMG_LOCAL_PATH : IMG_SERVER_PATH; 
	
	public static final String TXT_LOCAL_PATH = "E:\\apache-tomcat-7.0.68\\webapps\\ROOT\\pay\\";
	public static final String TXT_SERVER_PATH = "C:\\Program Files\\apache-tomcat-7.0.69\\webapps\\ROOT\\pay\\";
	public static final String TXT_PATH = LOCAL ? TXT_LOCAL_PATH : TXT_SERVER_PATH; 
	
	
	public static final String CHAT_LOCAL_PATH = "E:\\apache-tomcat-7.0.68\\webapps\\ROOT\\chat\\";
	public static final String CHAT_SERVER_PATH = "C:\\Program Files\\apache-tomcat-7.0.69\\webapps\\ROOT\\chat\\";
	public static final String CHAT_PATH = LOCAL ? CHAT_LOCAL_PATH : CHAT_SERVER_PATH; 

	public static final String LOCAL_PATH = "E:\\apache-tomcat-7.0.68\\webapps\\ROOT\\";
	public static final String SERVER_PATH = "C:\\Program Files\\apache-tomcat-7.0.69\\webapps\\ROOT\\";
	public static final String INFOM_PATH = LOCAL ? LOCAL_PATH : SERVER_PATH; 
	
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "a7779486";
	public static final String DB_NAME = "school";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost:3308/"+DB_NAME+"?useSSL=false";
	
}
