package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Comment;
import Model.MicroBlog;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DaoComment {
	
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
	public static int creatCommentTable(String table){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "CREATE TABLE IF NOT EXISTS comment"+table+" (" +
	    		"id INT PRIMARY KEY AUTO_INCREMENT,"+
	    		"userid INT(11),"+
	    		"headurl VARCHAR(255),"+
	    		"username VARCHAR(255),"+
	    		"comment VARCHAR(255),"+
	    		"time VARCHAR(255)," +
	    		"sex VARCHAR(255))";
	    
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
	public static int insert(String table,Comment comment) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into comment"+table+" (userid,headurl,username,comment,time,sex) values(?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, comment.getUserid());
	        pstmt.setString(2, comment.getHeadurl());
	        pstmt.setString(3, comment.getUsername());
	        pstmt.setString(4, comment.getComment());
	        pstmt.setString(5, comment.getTime());
	        pstmt.setString(6, comment.getSex());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//加载评论
	public static List<Comment> getSQLDataToList(String id) {
		String sql = "select * from comment"+ id +" ORDER BY id";
		List<Comment> commentList = new ArrayList<Comment>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行
	        int col = rs.getMetaData().getColumnCount();	//列
	        int count=0;
	        while (rs.next()) {
	        	Comment comment = new Comment();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	comment.setId(Integer.parseInt(data));
	                	break;
	                case 2:
	                	comment.setUserid(Integer.parseInt(data));
	                	break;
	                case 3:
	                	comment.setHeadurl(data);
						break;
					case 4:
						comment.setUsername(data);
						break;
					case 5:			
						comment.setComment(data);
						break;
					case 6:			
						comment.setTime(data);
						break;
					case 7:			
						comment.setSex(data);
						break;
					default:
						break;
					}
	        	}
	        	commentList.add(comment);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commentList;
	}	
	
	
}
