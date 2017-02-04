package login;


import globle.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.inject.New;
import javax.mail.Address;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;


import net.sf.json.JSONArray;

import org.apache.commons.io.FilenameUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;

import com.mysql.jdbc.BalanceStrategy;

import utils.FileUtils;
import utils.JsonUtils;
import utils.TimeUtils;

import Dao.DaoComment;
import Dao.DaoMessage;
import Dao.DaoMicroBlog;
import Dao.DaoQuestion;
import Dao.DaoReply;
import Dao.DaoUsers;
import JPush.PushExample;
import Model.Comment;
import Model.Infom;
import Model.Message;
import Model.MicroBlog;
import Model.Question;
import Model.Reply;
import Model.UserInfoBean;
import Model.UserInfoDataBean;
import Model.Version;

public class Login extends HttpServlet {
	//个人信息变量
	private String username;
	private String password;
	private String newData;
	private String headurl;
	private String mood;
	private String imageurl;
	private String phone;
	private String time;
	private String id;
	private String userid;
	private String sex;
	private String address;
	private String money;
	//评论的变量
	private String microBlogId;
	private String senderid;
	private String receiverid;
	private String sendername;
	private String senderimg;
	private String sendersex;
	private String type;
	private String content;
	private String imgurl;
	//请求数据变量
	private int listviewSize; 
	//数据返回变量
//	private JSONArray json;
	private OutputStream outputStream;
	
	private static final int TEST = 0;
	private static final int WHAT_LOGIN = 1;
	private static final int WHAT_ASSIGN = 2;
	private static final int WHAT_PERSONAL_MSG = 3;
	private static final int WHAT_REFRESH_LAST_MICROBLOG = 4;
	private static final int WHAT_CREAT_MICROBLOG = 5;
	private static final int WHAT_PRAISE = 6;
	private static final int WHAT_REFRESH_HOT_MICROBLOG = 7;
	private static final int WHAT_UPDATE_SEX = 8;
	private static final int WHAT_UPDATE_ADDRESS = 9;
	private static final int WHAT_UPDATE_PHONE = 10;
	private static final int WHAT_UPDATE_PASSWORD = 11;
	private static final int WHAT_HOT_MICROBLOG = 12;
	private static final int WHAT_FEEDBACK = 13;
	private static final int WHAT_VERSION = 14;
	private static final int WHAT_UPLOAD_LAST_MICROBLOG = 15;
	private static final int WHAT_QQUSER_JUDGE = 16;
	private static final int WHAT_REFRESH_MYPHOTO = 17;
	private static final int WHAT_UPLOAD_MYPHOTO = 18;
	private static final int WHAT_DELETE = 19;
	private static final int WHAT_COMMENT = 20;
	private static final int WHAT_UPLOAD_COMMENT = 21;
	private static final int WHAT_FIND_PASSWORD_OF_PHONE = 22;
	private static final int WHAT_UPDATE_USERNAME = 23;
	private static final int Add_MONEY = 24;
	private static final int REDUCE_MONEY = 25;
	private static final int QUERY_BALANCE= 26;
	private static final int WHAT_PERSONAL_MSG_BY_ID= 27;
	private static final int WHAT_UPDATE_SIGNA= 28;
	private static final int WHAT_ABS= 29;
	private static final int WHAT_INFO= 30;
	private static final int WHAT_PUSH_ALIAS= 31;
	private static final int WHAT_GET_MESSAGE= 32;
	private static final int WHAT_CREATE_QUESTION= 33;
	private static final int WHAT_GET_UNSOLVED_QUESTION= 34;
	private static final int WHAT_UPLOAD_UNSOLVED_QUESTION= 35;
	private static final int WHAT_GET_REPLY_LIST= 36;
	private static final int WHAT_PUBLIC_REPLY= 37;
	private static final int WHAT_REPLY_UP= 38;
	private static final int WHAT_REPLY_DOWN= 39;
	private static final int WHAT_MODIFY_QUESTION_TYPE= 40;
	private static final int WHAT_DELETE_QUESTION= 41;
	private static final int WHAT_GET_SOLVED_QUESTION= 42;
	private static final int WHAT_UPLOAD_SOLVED_QUESTION= 43;
	private static final int WHAT_GET_MY_QUESTION= 44;
	private static final int WHAT_UPLOAD_MY_QUESTION= 45;
	private static final int WHAT_CHAT= 46;
	private static final int NEW_WHAT_QQUSER_JUDGE = 47;
	private static final int NEW_WHAT_Clear_Message_Num = 48;
	
	private List<MicroBlog> microBlogList = new ArrayList<MicroBlog>();
	private List<Infom> filelist = new ArrayList<Infom>(); 
	private List<Message> messagesList = new ArrayList<Message>(); 
	private List<UserInfoDataBean> userList = new ArrayList<UserInfoDataBean>(); 
	private List<Question> questionList = new ArrayList<Question>(); 
	private List<Comment> commentList = new ArrayList<Comment>(); 
	private List<Reply> replyList = new ArrayList<Reply>(); 
	private String temp;
	private int tempInt;
	private String path;
	private String questionId;
	private String replyId;
	private String microBlogListJson;
	private String mJsonString;
	
	
	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("收到get请求");	
		//创建输出流
		outputStream = response.getOutputStream();
		String what = request.getParameter("what");
		System.out.println(what);
		int what1 = Integer.parseInt(what);

		switch (what1) {
			case TEST:		
//				PushExample.testSendPush(request.getParameter("param1"));
		        System.out.println("success");  
				break;
		
			//登录
			case WHAT_LOGIN:	
				phone = request.getParameter("param1");
				password = request.getParameter("param2");
				int i = DaoUsers.Rechecking(DaoUsers.STABLE_NAME, phone, password);
				switch(i){
					case 1:
//						outputStream.write("登录成功".getBytes("utf-8"));
						//返回个人数据
						getUserData(phone,"login");
						break;
					case 2:
						outputStream.write("密码错误".getBytes("utf-8"));
						break;
					case 3:
						outputStream.write("没有此用户".getBytes("utf-8"));
						break;
				}
				break;
				//注册
			case WHAT_ASSIGN:	
				password = request.getParameter("param1");
				headurl = request.getParameter("param2");
				phone = request.getParameter("param3");
				if(!phone.equals("")&&phone!=null){
					if(DaoUsers.Assign(DaoUsers.STABLE_NAME, phone)){
						DaoUsers.insert(DaoUsers.STABLE_NAME,new UserInfoDataBean("ID"+(DaoUsers.getMaxId()+1), password, headurl, phone,"男","中国",0,"",0));
						outputStream.write("注册成功".getBytes("utf-8"));
					}
					else {
						outputStream.write("此用户名已被使用".getBytes("utf-8"));
					}
				}
				
				break;
				
				//个人信
			case WHAT_PERSONAL_MSG:
				phone = request.getParameter("param1");
				//返回个人数据
				getUserData(phone,"success");
				
				break;
				//最新列表
			case WHAT_REFRESH_LAST_MICROBLOG:
				System.out.println("phone="+request.getParameter("param1"));
				microBlogList = DaoMicroBlog.getLastMicroBlogList(DaoMicroBlog.STABLE_NAME);
				microBlogListJson = JsonUtils.toJson(microBlogList);
				System.out.println(microBlogListJson); 
				outputStream.write(microBlogListJson.getBytes("utf-8"));
				break;	
				//发心情
			case WHAT_CREAT_MICROBLOG:
				mood = request.getParameter("param1");
				username = request.getParameter("param2");
				headurl = request.getParameter("param3");
				imageurl = request.getParameter("param4");
				time = request.getParameter("param5");
				id = request.getParameter("param6");
				sex = request.getParameter("param7");
				DaoMicroBlog.insert(DaoMicroBlog.STABLE_NAME, new MicroBlog(mood, imageurl,0,username,headurl,time,0,Integer.parseInt(id),sex));
				//积分+5
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(id),5);
				
				outputStream.write("成功".getBytes("utf-8"));
				break;
					
				//点赞
			case WHAT_PRAISE:
				microBlogId = request.getParameter("param1");
				senderid = request.getParameter("param2");
				receiverid = request.getParameter("param3");
				sendername = request.getParameter("param4");
				senderimg = request.getParameter("param5");
				sendersex = request.getParameter("param6");
				type = request.getParameter("param7");
				content = request.getParameter("param8");
				time = request.getParameter("param9");
				imgurl = request.getParameter("param10");
					
				//评论
//				DaoComment.creatCommentTable(microBlogPosition2);
//				DaoComment.insert(microBlogPosition2,new Comment(Integer.parseInt(senderid2),senderimg2,sendername2,detail2,time2,sendersex2));
//				DaoMicroBlog.updateCommentCount(DaoMicroBlog.STABLE_NAME, microBlogPosition2);
				//积分+1
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(senderid),1);
				//积分+1
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(receiverid),1);
				//存消息
				DaoMessage.creatMessageTable(receiverid);
				DaoMessage.insert(receiverid,new Message(Integer.parseInt(senderid),Integer.parseInt(receiverid),sendername,senderimg,sendersex,type,content,time,imgurl));
				//未读消息数目+1
				Dao.DaoUsers.updateUnreadMessage(DaoUsers.STABLE_NAME, Integer.parseInt(receiverid), 1);
				//发通知
				PushExample.testSendPush(receiverid,sendername+":"+content);		
				//赞+1
				DaoMicroBlog.updateThank(DaoMicroBlog.STABLE_NAME,Integer.parseInt(microBlogId));
				outputStream.write("done".getBytes("utf-8"));
				break;	
				//刷新人气列表
			case WHAT_REFRESH_HOT_MICROBLOG:
				id = request.getParameter("param1");
				int unreadNum = DaoMessage.getMaxId(id);
				temp = unreadNum+"";
				System.out.println("temp="+temp);
				outputStream.write(temp.getBytes("utf-8"));
				break;		
				//更新SEX
			case WHAT_UPDATE_SEX:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updateSex(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				DaoMicroBlog.updataSex(DaoMicroBlog.STABLE_NAME,Integer.parseInt(id),newData);
				
				outputStream.write("修改成功".getBytes("utf-8"));
				break;	
				//更新address
			case WHAT_UPDATE_ADDRESS:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updateAddress(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				outputStream.write("修改成功".getBytes("utf-8"));
				break;	
				//更新phone
			case WHAT_UPDATE_PHONE:
				id = request.getParameter("param1");
				phone = request.getParameter("param2");
				
				if(DaoUsers.Assign(DaoUsers.STABLE_NAME, phone)){
					DaoUsers.updatePhone(DaoUsers.STABLE_NAME,Integer.parseInt(id),phone);
					outputStream.write("修改成功".getBytes("utf-8"));
				}else{
					outputStream.write("手机已绑定其他用户".getBytes("utf-8"));
				}
				
				break;
				//更新password
			case WHAT_UPDATE_PASSWORD:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updatePassword(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				outputStream.write("修改成功".getBytes("utf-8"));
				break;
				
				//更新头像
			case WHAT_HOT_MICROBLOG:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updateHeadurl(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				DaoMicroBlog.updataHeadurl(DaoMicroBlog.STABLE_NAME,Integer.parseInt(id),newData);
				outputStream.write("修改成功".getBytes("utf-8"));
				break;
				
				//反馈
			case WHAT_FEEDBACK:
				id = request.getParameter("param1");
				temp = request.getParameter("param2");
				FileUtils.saveStringToFile("用户:"+id+",反馈信息:"+temp);
				outputStream.write("反馈信息已收到!".getBytes("utf-8"));
				break;	
				
				//获取版本
			case WHAT_VERSION:
				List<Version> vList = new ArrayList<Version>();
				Version version = new Version();
				version.setVersionName("2.3.5");
				version.setVersionCode(35);
				version.setDes("新版本更新了,祝同学们新年快乐！");
				version.setDownLoadUrl("http://119.29.170.73:8080/version/kaoyanjun.apk");
				vList.add(version);
				mJsonString = JsonUtils.toJson(vList);
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				
				//加载最新列表
			case WHAT_UPLOAD_LAST_MICROBLOG:
				temp = request.getParameter("param1");
				listviewSize= Integer.parseInt(temp); //string转int
				microBlogList = DaoMicroBlog.getLastMicroBlogList(DaoMicroBlog.STABLE_NAME,listviewSize);
				mJsonString = JsonUtils.toJson(microBlogList);
				System.out.println(mJsonString); 
//				json = JSONArray.fromObject(microBlogList); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;	
				
				//QQ登录判断是否已注册
//			case WHAT_QQUSER_JUDGE:	
//				username = request.getParameter("param1");
//				password = request.getParameter("param2");
//				headurl = request.getParameter("param3");
//				phone = request.getParameter("param4");
//				sex = request.getParameter("param5");
//				address = request.getParameter("param6");
//				if(!phone.equals("")&&phone!=null){
//					//注册
//					if(DaoUsers.Assign(DaoUsers.STABLE_NAME, phone)){
//						DaoUsers.insert(DaoUsers.STABLE_NAME,new UserInfoDataBean(username, password, headurl, phone, sex,address,0,"",0));
//						System.out.println("注册成功");
//					}
//					//登录
//					else {
//						if(DaoUsers.Rechecking(DaoUsers.STABLE_NAME, phone, password) ==1){
//							System.out.println("登录成功");
//							//返回个人数据
//						}
//					}
//					getUserData(phone);
//				}
//				break;
//				
				//刷新我的相册
			case WHAT_REFRESH_MYPHOTO:
				userid = request.getParameter("param1");
//				microBlogList = new ArrayList<MicroBlog>(); 
				microBlogList = DaoMicroBlog.getMyPhotoMicroBlogList(DaoMicroBlog.STABLE_NAME,Integer.parseInt(userid));
				microBlogListJson = JsonUtils.toJson(microBlogList);
//				json = JSONArray.fromObject(microBlogList); 
				System.out.println(microBlogListJson); 
				outputStream.write(microBlogListJson.getBytes("utf-8"));
				break;
			//加载我的相册
			case WHAT_UPLOAD_MYPHOTO:
				temp = request.getParameter("param1");
				userid = request.getParameter("param2");
				listviewSize= Integer.parseInt(temp); //string转int
//				microBlogList = new ArrayList<MicroBlog>(); 
				microBlogList = DaoMicroBlog.getMyPhotoMicroBlogList(DaoMicroBlog.STABLE_NAME,listviewSize,Integer.parseInt(userid));
				mJsonString = JsonUtils.toJson(microBlogList);
//				json = JSONArray.fromObject(microBlogList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
			//删除相册
			case WHAT_DELETE:
				id = request.getParameter("param1");
				DaoMicroBlog.delete(DaoMicroBlog.STABLE_NAME,Integer.parseInt(id));
				outputStream.write("done".getBytes("utf-8"));
				break;
			//评论
			case WHAT_COMMENT:
					
				microBlogId = request.getParameter("param1");
				senderid = request.getParameter("param2");
				receiverid = request.getParameter("param3");
				sendername = request.getParameter("param4");
				senderimg = request.getParameter("param5");
				sendersex = request.getParameter("param6");
				type = request.getParameter("param7");
				content = request.getParameter("param8");
				time = request.getParameter("param9");
				imgurl = request.getParameter("param10");
				
				//评论
				DaoComment.creatCommentTable(microBlogId);
				DaoComment.insert(microBlogId,new Comment(Integer.parseInt(senderid),senderimg,sendername,content,time,sendersex));
				DaoMicroBlog.updateCommentCount(DaoMicroBlog.STABLE_NAME, microBlogId);
				//积分+1
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(senderid),1);
				//存消息
				DaoMessage.creatMessageTable(receiverid);
				DaoMessage.insert(receiverid,new Message(Integer.parseInt(senderid),Integer.parseInt(receiverid),sendername,senderimg,sendersex,type,content,time,imgurl));
				//未读消息数目+1
				Dao.DaoUsers.updateUnreadMessage(DaoUsers.STABLE_NAME, Integer.parseInt(receiverid), 1);
				//发通知
				PushExample.testSendPush(receiverid,sendername+":"+content);
				
				getComment(microBlogId);
				break;
				
			//加载评论
			case WHAT_UPLOAD_COMMENT:
				id = request.getParameter("param1");
				getComment(id);
				break;

			//找回密码
			case WHAT_FIND_PASSWORD_OF_PHONE:
				phone = request.getParameter("param1");
				password = request.getParameter("param2");
				if(DaoUsers.judgePhone(DaoUsers.STABLE_NAME,phone)){
					DaoUsers.updatePasswordOfPhone(DaoUsers.STABLE_NAME,phone,password);
					outputStream.write("修改成功".getBytes("utf-8"));
				}else{
					outputStream.write("没有此用户".getBytes("utf-8"));
				}
				break;
			//修改昵称
			case WHAT_UPDATE_USERNAME:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updateUsername(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				DaoMicroBlog.updataUsername(DaoMicroBlog.STABLE_NAME,Integer.parseInt(id),newData);

				outputStream.write("修改成功".getBytes("utf-8"));
				break;
				
				//加积分
			case Add_MONEY:
				id = request.getParameter("param1");
				money = request.getParameter("param2");
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(id),Integer.parseInt(money));
				outputStream.write("done".getBytes("utf-8"));
				break;
				
				//扣积分
			case REDUCE_MONEY:
				id = request.getParameter("param1");
				phone = request.getParameter("param2");
				money = request.getParameter("param3");
				System.out.println("用户="+id+",充值手机号码="+phone+",充值金额="+money);
				if(DaoUsers.reduceMoney(DaoUsers.STABLE_NAME,Integer.parseInt(id),Integer.parseInt(money))){
					outputStream.write("done".getBytes("utf-8"));
					String currenTime = TimeUtils.getCurrentTime(); 
					FileUtils.saveStringToFile("用户="+id+",手机号码="+phone+",消费金额="+money+",时间="+currenTime);
				}else {
					outputStream.write("余额不足".getBytes("utf-8"));
				}
				
				break;
				
			//查询余额
			case QUERY_BALANCE:
				id = request.getParameter("param1");		
				tempInt = DaoUsers.queryMoney(DaoUsers.STABLE_NAME, Integer.parseInt(id));
				money = tempInt+"";
				outputStream.write((money).getBytes("utf-8"));
				break;
				
				//个人信息
			case WHAT_PERSONAL_MSG_BY_ID:
				id = new String(request.getParameter("param1").getBytes("iso8859-1"),"utf-8");
				userList = DaoUsers.usernameGetUserList(DaoUsers.STABLE_NAME,Integer.parseInt(id));
				mJsonString = JsonUtils.toJson(userList);
//				json = JSONArray.fromObject(userList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
			break;
			
			//个性签名
			case WHAT_UPDATE_SIGNA:
				id = request.getParameter("param1");
				newData = request.getParameter("param2");
				DaoUsers.updateSigna(DaoUsers.STABLE_NAME,Integer.parseInt(id),newData);
				outputStream.write("修改成功".getBytes("utf-8"));
				break;
			//微信聊天记录器（跟考研君没关）
			case WHAT_ABS:
				String text = request.getParameter("param1");
				String sign = request.getParameter("param2");
				String currenTime = TimeUtils.getCurrentTimeBaseDay(); 
				String dirPath = Constant.CHAT_PATH +sign;
				String filePath = Constant.CHAT_PATH +sign+"\\"+currenTime+".txt";
				System.out.println("filePath="+filePath);
				FileUtils.writeFile(dirPath,filePath,TimeUtils.getCurrentTime2()+"\r\n"+text+"\r\n");
				break;
			//获取文件名
			case WHAT_INFO:
				path = request.getParameter("param1");
				filelist = FileUtils.getFiles(Constant.INFOM_PATH+path);
				mJsonString = JsonUtils.toJson(filelist);
//				json = JSONArray.fromObject(filelist); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
			//发通知（测试）
			case WHAT_PUSH_ALIAS:
				String alias = request.getParameter("param1");
				String alert = request.getParameter("param2");
				PushExample.testSendPush(alias,alert);
				break;
			//获取消息列表
			case WHAT_GET_MESSAGE:
				id = request.getParameter("param1");
				DaoMessage.creatMessageTable(id);
				messagesList = DaoMessage.getSQLDataToList(id);
				mJsonString = JsonUtils.toJson(messagesList);
				System.out.println(mJsonString); 
//				json = JSONArray.fromObject(messagesList); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				
				//提问
			case WHAT_CREATE_QUESTION:
				id = request.getParameter("param1");
				username = request.getParameter("param2");
				headurl = request.getParameter("param3");
				sex = request.getParameter("param4");
				content = request.getParameter("param5");
				time = request.getParameter("param6");
				if(DaoQuestion.insert(DaoQuestion.STABLE_NAME, new Question(Integer.parseInt(id), username,headurl,sex,content,time,"0",0,"")) == 1){
					outputStream.write("提问成功".getBytes("utf-8"));
					//积分+1
					DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(id),1);
				}else {
					outputStream.write("提问失败".getBytes("utf-8"));
				}
				
				break;
				//获取未解决问题list
			case WHAT_GET_UNSOLVED_QUESTION:
				questionList = DaoQuestion.getQuestionList(DaoQuestion.STABLE_NAME);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				//上拉加载未解决问题list
			case WHAT_UPLOAD_UNSOLVED_QUESTION:
				listviewSize= Integer.parseInt(request.getParameter("param1"));
				questionList = DaoQuestion.getQuestionList(DaoQuestion.STABLE_NAME,listviewSize);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;	
				
				//加载评论
			case WHAT_GET_REPLY_LIST:
				id = request.getParameter("param1");
				getReply(id);
				break;
				
				//问题回答
			case WHAT_PUBLIC_REPLY:
				id = request.getParameter("param1");
				receiverid = request.getParameter("param2");
				userid = request.getParameter("param3");
				username = request.getParameter("param4");
				headurl = request.getParameter("param5");
				sex = request.getParameter("param6");
				time = request.getParameter("param7");
				content = request.getParameter("param8");
				
				//评论
				DaoReply.creatReplyTable(id);
				DaoReply.insert(id,new Reply(Integer.parseInt(userid),headurl,username,sex,time,"",content,0,0));
				DaoQuestion.updateReplyCount(DaoQuestion.STABLE_NAME, id);
				//积分+1
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(userid),1);
				//存消息
				DaoMessage.creatMessageTable(receiverid);
				DaoMessage.insert(receiverid,new Message(Integer.parseInt(userid),Integer.parseInt(receiverid),username,headurl,sex,"",content,time,"/head/ico_message_reply.png"));
				//未读消息数目+1
				Dao.DaoUsers.updateUnreadMessage(DaoUsers.STABLE_NAME, Integer.parseInt(receiverid), 1);
				//发通知
				PushExample.testSendPush(receiverid,username+":"+content);
				
				getReply(id);
				break;
				
				//赞
			case WHAT_REPLY_UP:
				questionId = request.getParameter("param1");
				replyId = request.getParameter("param2");
				receiverid = request.getParameter("param3");
				userid = request.getParameter("param4");
				username = request.getParameter("param5");
				headurl = request.getParameter("param6");
				sex = request.getParameter("param7");
				type = request.getParameter("param8");
				time = request.getParameter("param9");
				//被赞人积分+1
				DaoUsers.updateMoney(DaoUsers.STABLE_NAME,Integer.parseInt(receiverid),1);
				//评论数目+1
				DaoReply.updateReplyUpCount(questionId,replyId);
				//返回评论列表
				getReply(questionId);
				//发通知
				PushExample.testSendPush(receiverid,username+"赞了你的回答!");
				//存消息
				DaoMessage.creatMessageTable(receiverid);
				DaoMessage.insert(receiverid,new Message(Integer.parseInt(userid),Integer.parseInt(receiverid),username,headurl,sex,type,username+"赞了你的回答!",time,"/head/ico_message_reply.png"));
				//未读消息数目+1
				Dao.DaoUsers.updateUnreadMessage(DaoUsers.STABLE_NAME, Integer.parseInt(receiverid), 1);
				break;
				//踩
			case WHAT_REPLY_DOWN:
				questionId = request.getParameter("param1");
				replyId = request.getParameter("param2");
				DaoReply.updateReplyDownCount(questionId,replyId);
				getReply(questionId);
				break;
				
				//设置问题类型
			case WHAT_MODIFY_QUESTION_TYPE:
				type = request.getParameter("param1");
				questionId = request.getParameter("param2");
				System.out.println("type="+type+",questionId="+questionId);
				//修改类型
				DaoQuestion.modifyType(DaoQuestion.STABLE_NAME, type,questionId);
				//获取问题列表
				questionList = DaoQuestion.getQuestionList(DaoQuestion.STABLE_NAME);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				//删除问题
			case WHAT_DELETE_QUESTION:
				questionId = request.getParameter("param1");
				DaoQuestion.delete(DaoQuestion.STABLE_NAME, questionId);
				outputStream.write("删除成功".getBytes("utf-8"));
				break;
				//获取解决的问题
			case WHAT_GET_SOLVED_QUESTION:
				questionList = DaoQuestion.getSolvedQuestionList(DaoQuestion.STABLE_NAME);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				//加载解决的问题
			case WHAT_UPLOAD_SOLVED_QUESTION:
				listviewSize= Integer.parseInt(request.getParameter("param1"));
				questionList = DaoQuestion.getSolvedQuestionList(DaoQuestion.STABLE_NAME,listviewSize);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
			case WHAT_GET_MY_QUESTION:
				userid = request.getParameter("param1");
				questionList = DaoQuestion.getMyQuestionList(DaoQuestion.STABLE_NAME,userid);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
			case WHAT_UPLOAD_MY_QUESTION:
				listviewSize= Integer.parseInt(request.getParameter("param1"));
				userid = request.getParameter("param2");
				questionList = DaoQuestion.getMyQuestionList(DaoQuestion.STABLE_NAME,listviewSize,userid);
				mJsonString = JsonUtils.toJson(questionList);
//				json = JSONArray.fromObject(questionList); 
				System.out.println(mJsonString); 
				outputStream.write(mJsonString.getBytes("utf-8"));
				break;
				
			case WHAT_CHAT:
				senderid = request.getParameter("param1");
				receiverid = request.getParameter("param2");
				sendername = request.getParameter("param3");
				senderimg = request.getParameter("param4");
				sendersex = request.getParameter("param5");
				type = request.getParameter("param6");
				content = request.getParameter("param7");
				time = request.getParameter("param8");
				imgurl = request.getParameter("param9");
				//存消息
				DaoMessage.creatMessageTable(receiverid);
				DaoMessage.insert(receiverid,new Message(Integer.parseInt(senderid),Integer.parseInt(receiverid),sendername,senderimg,sendersex,type,content,time,imgurl));
				//发通知
				PushExample.testSendPush(receiverid,sendername+":"+content);	
				break;
				
				//QQ登录判断是否已注册
			case NEW_WHAT_QQUSER_JUDGE:	
				username = request.getParameter("param1");
				password = request.getParameter("param2");
				headurl = request.getParameter("param3");
				phone = request.getParameter("param4");
				sex = request.getParameter("param5");
				address = request.getParameter("param6");
				
				System.out.println("phone="+phone);
				
				if(!phone.equals("")&&phone!=null){
					//注册
					if(DaoUsers.Assign(DaoUsers.STABLE_NAME, phone)){
						DaoUsers.insert(DaoUsers.STABLE_NAME,new UserInfoDataBean(username, password, headurl, phone, sex,address,0,"",0));
						System.out.println("注册成功");
						//返回个人数据
						getUserData(phone,"assign");
//						outputStream.write("assign".getBytes("utf-8"));
					}
					//登录
					else {
						if(DaoUsers.Rechecking(DaoUsers.STABLE_NAME, phone, password) ==1){
							System.out.println("登录成功");
							//返回个人数据
							getUserData(phone,"login");
						}
					}
				}
				break;
				
			case NEW_WHAT_Clear_Message_Num:
				id = request.getParameter("param1");
				DaoUsers.clearUnreadMessage(DaoUsers.STABLE_NAME,Integer.parseInt(id));
				outputStream.write("done".getBytes("utf-8"));
				break;
		
		}
		
	}
	
	
	//返回个人数据
	private void getUserData(String photo1,String info){
		UserInfoDataBean userInfoDataBean = DaoUsers.usernameGetUserList(DaoUsers.STABLE_NAME,photo1);
		UserInfoBean userInfoBean = new UserInfoBean();
		if(userInfoDataBean != null && !userInfoDataBean.equals("")){
			userInfoBean.setData(userInfoDataBean);
			userInfoBean.setInfo(info);
			userInfoBean.setStatus(1);  
			String json = JsonUtils.toJson(userInfoBean);
			System.out.println(json.toString()); 
			try {
				outputStream.write(json.toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//获取评论
	private void  getComment(String position) {
		DaoComment.creatCommentTable(position);
		commentList = DaoComment.getSQLDataToList(position);
		mJsonString = JsonUtils.toJson(commentList);
//		json = JSONArray.fromObject(commentList); 
		System.out.println(mJsonString); 
		try {
			outputStream.write(mJsonString.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取回复
	private void  getReply(String position) {
		DaoReply.creatReplyTable(position);
		replyList = DaoReply.getSQLDataToList(position);
		mJsonString = JsonUtils.toJson(replyList);
//		json = JSONArray.fromObject(replyList); 
		System.out.println(mJsonString); 
		try {
			outputStream.write(mJsonString.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
