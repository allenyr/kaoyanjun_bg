package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Comment;
import Model.MicroBlog;
import Model.Reply;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DaoReply {
	
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
	
	//创建ReplyTable
	public static int creatReplyTable(String table){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "CREATE TABLE IF NOT EXISTS reply"+table+" (" +
	    		"id INT PRIMARY KEY AUTO_INCREMENT,"+
	    		"userid INT(11),"+
	    		"headurl VARCHAR(255),"+
	    		"username VARCHAR(255),"+
	    		"sex VARCHAR(255),"+
	    		"time VARCHAR(255),"+
	    		"type VARCHAR(255),"+
	    		"reply VARCHAR(255),"+
	    		"up INT(11),"+
	    		"down INT(11))";
	    
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
	public static int insert(String table,Reply reply) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into reply"+table+" (userid,headurl,username,sex,time,type,reply,up,down) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, reply.getUserid());
	        pstmt.setString(2, reply.getHeadurl());
	        pstmt.setString(3, reply.getUsername());
	        pstmt.setString(4, reply.getSex());
	        pstmt.setString(5, reply.getTime());
	        pstmt.setString(6, reply.getType());
	        pstmt.setString(7, reply.getReply());
	        pstmt.setInt(8, reply.getUp());
	        pstmt.setInt(9, reply.getDown());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//加载评论
	public static List<Reply> getSQLDataToList(String id) {
		String sql = "select * from reply"+ id +" ORDER BY id";
		List<Reply> replyList = new ArrayList<Reply>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行
	        int col = rs.getMetaData().getColumnCount();	//列
	        int count=0;
	        while (rs.next()) {
	        	Reply reply = new Reply();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	reply.setId(Integer.parseInt(data));
	                	break;
	                case 2:
	                	reply.setUserid(Integer.parseInt(data));
	                	break;
	                case 3:
	                	reply.setHeadurl(data);
						break;
					case 4:
						reply.setUsername(data);
						break;
					case 5:			
						reply.setSex(data);
						break;
					case 6:			
						reply.setTime(data);
						break;
					case 7:			
						reply.setType(data);
						break;
					case 8:			
						reply.setReply(data);
						break;
					case 9:			
						reply.setUp(Integer.parseInt(data));
						break;
					case 10:			
						reply.setDown(Integer.parseInt(data));
						break;
					default:
						break;
					}
	        	}
	        	replyList.add(reply);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return replyList;
	}	
	
	//赞
	public static int updateReplyUpCount(String table,String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update reply"+table+" set up = up+1 where id =" + id ;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//踩
	public static int updateReplyDownCount(String table,String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update reply"+table+" set down = down+1 where id =" + id ;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
}
