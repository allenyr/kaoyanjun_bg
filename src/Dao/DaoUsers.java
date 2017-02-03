package Dao;

import globle.Constant;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import Model.Users;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoUsers {

	public static final String STABLE_NAME = "user";
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
	
	//��ѯId���ֵ  
	public static int getMaxId(){
		Connection conn = getConn();
		String sql = "select max(id) from "+STABLE_NAME;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	 return rs.getInt(1);    
	        	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return 0;
	}
	
	//����
	public static int insert(String table,Users users) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into "+table+" (username,password,headurl,phone,sex,address,money,signa,messagenum) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, users.getUsername());
	        pstmt.setString(2, users.getPassword());
	        pstmt.setString(3, users.getHeadurl());
	        pstmt.setString(4, users.getPhone());
	        pstmt.setString(5, users.getSex());
	        pstmt.setString(6, users.getAddress());
	        pstmt.setInt(7, users.getMoney());
	        pstmt.setString(8, users.getSigna());
	        pstmt.setInt(9, users.getMessagenum());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//�����ǳ�
	public static int updateUsername(String table,int id,String username) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set username='" + username + "' where id='"+ id+"'";
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
	
	//����sex
	public static int updateSex(String table,int id,String sex) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set sex='" + sex + "' where id="+id ;
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
	
	//����address
	public static int updateAddress(String table,int id,String address) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set address='" + address + "' where id=" + id;
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
	
	//���¸���ǩ��
	public static int updateSigna(String table,int id,String signa) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set signa='" + signa + "' where id='"+ id+"'";
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
	
	//����phone
	public static int updatePhone(String table,int id,String phone) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set phone='" + phone + "' where id=" + id;
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
	
	//��������
	public static int updatePassword(String table,int id,String password) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set password='" + password + "' where id=" + id;
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
	
	//����ͷ��
	public static int updateHeadurl(String table,int id,String headurl) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set headurl='" + headurl + "' where id=" + id;
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
	
	//���ظ�����Ϣ
	public static List<Users> usernameGetUserList(String table,String phone) {
		List<Users> lUsers = new ArrayList<Users>();
		Users users = new Users();
	    Connection conn = getConn();
	    String sql = "select id,username,password,headurl,phone,sex,address,money,signa,messagenum from "+table+" where phone='"+phone+"'";
	    PreparedStatement pstmt;
	    try {
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	users.setId(Integer.parseInt(data));
	                	break;
	                case 2:			
						users.setUsername(data);
						break;
					case 3:
						users.setPassword(data);
						break;
					case 4:			
						users.setHeadurl(data);
						break;
					case 5:			
						users.setPhone(data);
						break;
					case 6:			
						users.setSex(data);
						break;
					case 7:			
						users.setAddress(data);
						break;
					case 8:			
						users.setMoney(Integer.parseInt(data));
						break;
					case 9:			
						users.setSigna(data);
						break;
					case 10:			
//						users.setMessagenum(DaoMessage.getMaxId(users.getId()+""));
						users.setMessagenum(Integer.parseInt(data));
						
						break;
					default:
						break;
					}
	        	}
            }
	        lUsers.add(users);
	        return lUsers;
	     
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//���ظ�����Ϣ(���Ͽ�)
	public static List<Users> usernameGetUserList(String table,int id) {
		List<Users> lUsers = new ArrayList<Users>();
		Users users = new Users();
	    Connection conn = getConn();
	    String sql = "select id,username,password,headurl,phone,sex,address,money,signa from "+table+" where id="+id;
	    PreparedStatement pstmt;
	    try {
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
	                case 1:
	                	users.setId(Integer.parseInt(data));
	                	break;
	                case 2:			
						users.setUsername(data);
						break;
					case 3:
						users.setPassword(data);
						break;
					case 4:			
						users.setHeadurl(data);
						break;
					case 5:			
						users.setPhone(data);
						break;
					case 6:			
						users.setSex(data);
						break;
					case 7:			
						users.setAddress(data);
						break;
					case 8:			
						users.setMoney(Integer.parseInt(data));
						break;
					case 9:			
						users.setSigna(data);
						break;
					default:
						break;
					}
	        	}
            }
	        lUsers.add(users);
	        return lUsers;
	     
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//�����û���Ϣ
	public static List<Users> getUserList(String table) {
		List<Users> lUsers = new ArrayList<Users>();
	    Connection conn = getConn();
	    String sql = "select * from "+table+"";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	        	Users users = new Users();
	        	for (int i = 1; i <= col; i++) {
	                String data = rs.getString(i);
	                switch (i) {
                	case 1:
                		users.setId(Integer.parseInt(data));
                		break;
	                case 2:
						users.setUsername(data);
						break;
					case 3:
						users.setPassword(data);
						break;
					case 4:			
						users.setHeadurl(data);
						break;
					case 5:			
						users.setPhone(data);
						break;
					case 6:			
						users.setSex(data);
						break;
					case 7:			
						users.setAddress(data);
						break;
					case 8:			
						users.setMoney(Integer.parseInt(data));
						break;
					case 9:			
						users.setSigna(data);
						break;

					default:
						break;
					}
	        	}
	        	lUsers.add(users);
            }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lUsers;
	}
	
	//��¼�ж�
	public static int Rechecking(String table,String phone,String password) {
		Connection conn = getConn();
	    String sql = "select * from "+table+" where phone = '" + phone + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	if (rs.getString(5).equals(phone)){
	        		if(rs.getString(3).equals(password)){
	        			//������ȷ
	        			return 1;
	        		}
	        		else{
	        			//�������
	        			return 2;
	        		}
	        	}  
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    //û�д��û�
		return 3;
	}
		
	
	//ɾ�������û���ɾ����
	public static int delete(String table,String phone) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from "+table+" where phone='" + phone + "'";
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

	//ע���ֻ��Ƿ����
	public static boolean Assign(String table,String phone) {
		Connection conn = getConn();
	    String sql = "select * from "+table+" where phone = '" + phone + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	if (rs.getString(5).equals(phone)){
	        		return false;       
	        	}
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return true;
	}
	
	//�����ֻ����޸�����
	public static int updatePasswordOfPhone(String table,String phone,String password) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set password='" + password + "' where phone='" + phone + "'";
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
	
	//�ж��ֻ��Ƿ����
	public static boolean judgePhone(String table,String phone) {
		Connection conn = getConn();
	    String sql = "select phone from "+table+" WHERE phone = '"+ phone + "'" ;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	if (rs.getString(1).equals(phone)){
	        		return true;       
	        	}
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}
	
	//���ӻ���
	public static int updateMoney(String table,int id,int money) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set money = money+"+money+" where id = " + id;
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
	
	//����δ����Ϣ
	public static int updateUnreadMessage(String table,int id,int messagenum) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update "+table+" set messagenum = messagenum+"+messagenum+" where id = " + id;
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
	
	//����δ����Ϣ
		public static int clearUnreadMessage(String table,int id) {
		    Connection conn = getConn();
		    int i = 0;
		    String sql = "update "+table+" set messagenum = 0"+" where id = " + id;
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
	
	//����ѯ
	public static int queryMoney(String table,int id) {
	    Connection conn = getConn();
	    int money = 0;
	    String sql = "select money from "+table+" where id="+id;
	    PreparedStatement pstmt;
	    try {
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();			//��Ԫ��
	        int col = rs.getMetaData().getColumnCount();	//��Ԫ��
	        while (rs.next()) {
	        	money = rs.getInt(1); //0����ӦID��1����Ӧ��ѯ��������
	        }
	     
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return money;
	}
	
	
	//�۳�����
	public static boolean reduceMoney(String table,int id,int money) {
		if(queryMoney(table,id)-money >= 0){
			Connection conn = getConn();
		    String sql = "update "+table+" set money = money-"+money+" where id = " + id ;
		    PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return true;    
		}else{
			return false;
		}
	}
	

	
}
