package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Comment;
import Model.Message;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DaoMessage {
	//创建一个JDBC连接
	private static Connection getConn() {
	    Connection conn = null;
	    try {
	        Class.forName(Constant.DRIVER); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(Constant.JDBC_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
		
	//创建commentTable
	public static int creatMessageTable(String table){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "CREATE TABLE IF NOT EXISTS message"+table+" (" +
	    		"id INT PRIMARY KEY AUTO_INCREMENT,"+
	    		"senderid INT(11),"+
	    		"receiverid INT(11),"+
	    		"sendername VARCHAR(255),"+
	    		"senderimg VARCHAR(255),"+
	    		"sendersex VARCHAR(255),"+
	    		"type VARCHAR(255),"+
	    		"detail VARCHAR(255)," +
	    		"time VARCHAR(255),"+
	    		"imgurl VARCHAR(255))";
	    
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//增加
	public static int insert(String table,Message message) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into message"+table+" (senderid,receiverid,sendername,senderimg,sendersex,type,detail,time,imgurl) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, message.getSenderid());
	        pstmt.setInt(2, message.getReceiverid());
	        pstmt.setString(3, message.getSendername());
	        pstmt.setString(4, message.getSenderimg());
	        pstmt.setString(5, message.getSendersex());
	        pstmt.setString(6, message.getType());
	        pstmt.setString(7, message.getDetail());
	        pstmt.setString(8, message.getTime());
	        pstmt.setString(9, message.getImgurl());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
		
	//加载评论
	public static List<Message> getSQLDataToList(String id) {
		String sql = "select * from message"+ id +" ORDER BY id DESC limit " + 0 + "," +10;
		List<Message> messageList = new ArrayList<Message>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行
	        int col = rs.getMetaData().getColumnCount();	//列
	        int count=0;
	        while (rs.next()) {
	        	Message message = new Message();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	message.setId(Integer.parseInt(data));
	                	break;
	                case 2:
	                	message.setSenderid(Integer.parseInt(data));
	                	break;
	                case 3:
	                	message.setReceiverid(Integer.parseInt(data));
						break;
					case 4:
						message.setSendername(data);
						break;
					case 5:			
						message.setSenderimg(data);
						break;
					case 6:			
						message.setSendersex(data);
						break;
					case 7:			
						message.setType(data);
						break;
					case 8:			
						message.setDetail(data);
						break;
					case 9:			
						message.setTime(data);
						break;
					case 10:			
						message.setImgurl(data);
						break;
					default:
						break;
					}
	        	}
	        	messageList.add(message);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return messageList;
	}	
	
	
	//查询Id最大值  
	public static int getMaxId(String id){
		creatMessageTable(id);
		Connection conn = getConn();
		String sql = "select max(id) from message"+ id;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行元素
	        int col = rs.getMetaData().getColumnCount();	//列元素
	        while (rs.next()) {
	        	 return rs.getInt(1);    
	        	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return 0;
	}
	
}
