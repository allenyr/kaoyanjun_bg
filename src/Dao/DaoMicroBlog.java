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
	
	//����һ��JDBC����
	private static Connection getConn() {
	    Connection conn = null;
	    try {
	        Class.forName(Constant.DRIVER); //classLoader,���ض�Ӧ����
	        conn = (Connection) DriverManager.getConnection(Constant.JDBC_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	  
	//����
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
	

	//���ӵ���
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
	
	
	//ɾ��
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
	
	//��Ŀ����
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
	
	//����
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
	
	
	//ˢ��ָ��position������Ϣ
	public static List<MicroBlog> getHotMicroBlogList(String table) {
	    String sql = "select * from "+ table + " ORDER BY Thank DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
	//����ָ��position������Ϣ
	public static List<MicroBlog> getHotMicroBlogList(String table,int position) {
	    String sql = "select * from "+ table + " ORDER BY Thank DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
	
	
	//ˢ��ָ��position������Ϣ
	public static List<MicroBlog> getLastMicroBlogList(String table) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}

	//����ָ��position������Ϣ
	public static List<MicroBlog> getLastMicroBlogList(String table,int position) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//ˢ�������Ϣ
	public static List<MicroBlog> getMyPhotoMicroBlogList(String table,int userid) {

	    String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//�����ҵ������Ϣ
	public static List<MicroBlog> getMyPhotoMicroBlogList(String table,int position,int userid) {
		String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
		return getSQLDataToList(sql);
	}
		
	
	//����
	private static List<MicroBlog> getSQLDataToList(String sql) {
		List<MicroBlog> microBlogList = new ArrayList<MicroBlog>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��
	        int col = rs.getMetaData().getColumnCount();	//��
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
	
	//�޸�΢���������û���
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
	
	//�޸�΢��������sex
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
	
	//�޸�΢��������headurl
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
