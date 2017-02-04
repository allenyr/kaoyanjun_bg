package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.MicroBlog;
import Model.UserInfoDataBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DaoMicroBlog {
	
	public static final String STABLE_NAME = "microblog";
	private static final int REFRESH_LENGTH = 10;
	private static final int UPLOAD_LENGTH = 5;
	
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
	  
	//增加
	public static int insert(String table,MicroBlog microblog) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into "+table+" (mood,imageurl,thank,username,headurl,time,commentcount,userid,sex) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, microblog.getMood());
	        pstmt.setString(2, microblog.getImageurl());
	        pstmt.setInt(3, microblog.getThank());
	        pstmt.setString(4, microblog.getUsername());
	        pstmt.setString(5, microblog.getHeadurl());
	        pstmt.setString(6, microblog.getTime());
	        pstmt.setInt(7, microblog.getCommentcount());
	        pstmt.setInt(8, microblog.getUserId());
	        pstmt.setString(9, microblog.getSex());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	

	//增加点赞
	public static int updateThank(String table,int id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set thank = thank+1 where id =" + id ;
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
	
	
	//删除
	public static int delete(String table,int id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "DELETE FROM "+table+" WHERE id = "+id;
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
	
	//条目数量
	public static int updateCommentCount(String table,String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set commentcount = commentcount+1 where id =" + id ;
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
	
	//遍历
	public static Integer getAll(String table) {
	    Connection conn = getConn();
	    String sql = "select * from "+table+"";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	//刷新指定position人气信息
	public static List<MicroBlog> getHotMicroBlogList(String table) {
	    String sql = "select * from "+ table + " ORDER BY Thank DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
	//加载指定position人气信息
	public static List<MicroBlog> getHotMicroBlogList(String table,int position) {
	    String sql = "select * from "+ table + " ORDER BY Thank DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
	
	
	//刷新指定position最新信息
	public static List<MicroBlog> getLastMicroBlogList(String table) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}

	//加载指定position最新信息
	public static List<MicroBlog> getLastMicroBlogList(String table,int position) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//刷新相册信息
	public static List<MicroBlog> getMyPhotoMicroBlogList(String table,int userid) {

	    String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//加载我的相册信息
	public static List<MicroBlog> getMyPhotoMicroBlogList(String table,int position,int userid) {
		String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
		return getSQLDataToList(sql);
	}
		
	
	//解析
	private static List<MicroBlog> getSQLDataToList(String sql) {
		List<MicroBlog> microBlogList = new ArrayList<MicroBlog>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行
	        int col = rs.getMetaData().getColumnCount();	//列
	        int count=0;
	        while (rs.next()) {
	        	MicroBlog microBlog = new MicroBlog();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	microBlog.setId(Integer.parseInt(data));
	                	break;
	                case 2:
	                	microBlog.setMood(data);
						break;
					case 3:
						microBlog.setImageurl(data);
						break;
					case 4:			
						microBlog.setThank(Integer.parseInt(data));
						break;
					case 5:			
						microBlog.setUsername(data);
						break;
					case 6:			
						microBlog.setHeadurl(data);
						break;
					case 7:			
						microBlog.setTime(data);
						break;
					case 8:			
						microBlog.setCommentcount(Integer.parseInt(data));
						break;
					case 9:
						microBlog.setUserId(Integer.parseInt(data));
						break;
					case 10:
						microBlog.setSex(data);
						break;
					default:
						break;
					}
	        	}
	        	microBlogList.add(microBlog);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return microBlogList;
	}	
	
	//修改微博发帖人用户名
	public static int updataUsername(String table,int userid,String username){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+ table +" set username = '"+username+"' where userid ="+userid;
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
	
	//修改微博发帖人sex
	public static int updataSex(String table,int userid,String sex){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+ table +" set sex = '"+sex+"' where userid ="+userid;
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
	
	//修改微博发帖人headurl
	public static int updataHeadurl(String table,int userid,String headurl){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+ table +" set headurl = '"+headurl+"' where userid ="+userid;
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
