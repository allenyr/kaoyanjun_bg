package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.MicroBlog;
import Model.Question;
import Model.UserInfoDataBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DaoQuestion {
	
	public static final String STABLE_NAME = "questions";
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
	public static int insert(String table,Question question) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into "+table+" (userid,username,headurl,sex,question,time,type,replycount,title) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, question.getUserid());
	        pstmt.setString(2, question.getUsername());
	        pstmt.setString(3, question.getHeadurl());
	        pstmt.setString(4, question.getSex());
	        pstmt.setString(5, question.getQuestion());
	        pstmt.setString(6, question.getTime());
	        pstmt.setString(7, question.getType());
	        pstmt.setInt(8, question.getReplycount());
	        pstmt.setString(9, question.getTitle());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	//删除
	public static int delete(String table,String id) {
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
	
	//回答数量
	public static int updateReplyCount(String table,String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set replycount = replycount+1 where id =" + id ;
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
	
	//修改问题类型
	public static int modifyType(String table,String type,String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set type = "+type+" where id =" + id ;
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
	
	
	//获取问题list
	public static List<Question> getQuestionList(String table) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
	//加载问题list
	public static List<Question> getQuestionList(String table,int position) {
	    String sql = "select * from "+ table + " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
	
	//获取我的问题list
	public static List<Question> getMyQuestionList(String table,String userid) {
	    String sql = "select * from "+ table + " WHERE userid = "+userid+ " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;

	    return getSQLDataToList(sql);
	}
	//加载我的问题list
	public static List<Question> getMyQuestionList(String table,int position,String userid) {
	    String sql = "select * from "+ table + " WHERE userid = "+userid+ " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
	
	
	//获取已解决问题list
	public static List<Question> getSolvedQuestionList(String table) {
	    String sql = "select * from "+ table + " where type = 1" + "ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
	//加载已解决问题list
	public static List<Question> getSolvedQuestionList(String table,int position) {
	    String sql = "select * from "+ table + " where type = 1" + " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
	
	//获取未解决问题list
	public static List<Question> getUnsolvedQuestionList(String table) {
	    String sql = "select * from "+ table + " where type = 0" +" ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}

	//加载未解决问题list
	public static List<Question> getUnsolvedQuestionList(String table,int position) {
	    String sql = "select * from "+ table + " where type = 0" +" ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//刷新相册信息
	public static List<Question> getMyQuestionList(String table,int userid) {

	    String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + 0 + "," +REFRESH_LENGTH;
	    return getSQLDataToList(sql);
	}
		
	//加载我的相册信息
	public static List<Question> getMyQuestionList(String table,int position,int userid) {
		String sql = "select * from "+ table +" WHERE userid = "+userid+ " ORDER BY id DESC limit " + position + "," +UPLOAD_LENGTH;
		return getSQLDataToList(sql);
	}
		
	
	//解析
	private static List<Question> getSQLDataToList(String sql) {
		List<Question> questionsList = new ArrayList<Question>();
	    Connection conn = getConn();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//行
	        int col = rs.getMetaData().getColumnCount();	//列
	        int count=0;
	        while (rs.next()) {
	        	Question question = new Question();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	question.setId(Integer.parseInt(data));
	                	break;
	                case 2:
	                	question.setUserid(Integer.parseInt(data));
						break;
					case 3:
						question.setUsername(data);
						break;
					case 4:			
						question.setHeadurl(data);
						break;
					case 5:			
						question.setSex(data);
						break;
					case 6:			
						question.setQuestion(data);
						break;
					case 7:			
						question.setTime(data);
						break;
					case 8:			
						question.setType(data);
						break;
					case 9:
						question.setReplycount(Integer.parseInt(data));
						break;
					case 10:
						question.setTitle(data);
					default:
						break;
					}
	        	}
	        	questionsList.add(question);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return questionsList;
	}	
	
	//修改提问人用户名
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
	
	//修改提问人sex
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
	
	//修改提问人headurl
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
