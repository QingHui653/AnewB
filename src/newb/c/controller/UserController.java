package newb.c.controller;

import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newb.c.model.RepList;
import newb.c.model.SessionB;
import newb.c.model.User;
import newb.c.model.UserCache;
import newb.c.model.UserData;
import newb.c.model.UserXL;
import newb.c.service.ResultService;
import newb.c.service.TestCacheService;
import newb.c.service.UserService;
import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private TestCacheService userCacheService;
	
	
	//常用工具类   时间不能用在这 ,不然每次进入不会重新new 一个对象,
	//会使得时间有BUG
//	GregorianCalendar cal=new GregorianCalendar();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 *  访问/user/newb/2
	 *  将user 绑定到modelMap
	 *  在JSP页面 使用 ${user.username}等访问
	 *  跳转到/user/showInfo
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	 @ApiOperation(value = "获取用户列表", notes = "") 
	 @RequestMapping(value="/newb/{userId}",method= {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public Object showUserInfo(ModelMap modelMap,@PathVariable int userId){
	        User user = userService.getUserById(userId);
//		 Example e= new Example(User.class);
//		 e.createCriteria().andGreaterThanOrEqualTo("oid", 0);
//		 List<User> userList= userService.selectByExample(e);
//	        modelMap.addAttribute("user", user);  
	     return user;  
	    } 
	 
	 /**
	  * 测试不同mapXML是否可行
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping("/selectQW/{userId}")
	 @ResponseBody
	 public String selectQW(ModelMap modelMap,@PathVariable int userId){
	        String password = userService.selectPW(userId);	     
	        return password;  
	    } 
	 
	 /**
	  * save 测试事务
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/user/save",method= RequestMethod.GET)
	 @ResponseBody
	 public String insertUser(ModelMap modelMap){
		 	/**
		 	 * 测试插入10w数据，用时，在服务器开启后第一次用时10s，后面用时2-4s
		 	 */
		 	List<User> userList = new ArrayList<User>();
		 	for (int i = 0; i < 100000; i++) {
		 		User user =new User();
		 		user.setOid(i);
		 		user.setUsername(i+"");
		 		user.setPassword(i+"");
		 		userList.add(user);
			}
		 	userService.insertAll(userList);
		 	/**
		 	 * 测试查询10w条数据，user在oid加入索引 类型Unique 方法：BTREE
		 	 */
		 	/*Example e= new Example(User.class);
		 	e.createCriteria().andGreaterThanOrEqualTo("oid", 0);
		 	List<User> userList = userService.selectByExample(e);
		 	System.out.println("有索引--"+userList.size());*/
	        return "/user/showInfo";  
	    } 
	 
	 /**
	  * save 测试事务
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/user/selectuser",method= RequestMethod.GET)
	 @ResponseBody
	 public String selectUserForUpdate(ModelMap modelMap){
		 	List<User> userList = userService.selectAllForUpdate();
		 	System.out.println("有索引 forupdate--"+userList.size());
	        return "/user/showInfo";  
	    }
	 
	 /**
	  * userCahce表，测试插入10w条数据
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/userCache/save",method= RequestMethod.GET)
	 @ResponseBody
	 public Object insertCache(ModelMap modelMap){
		 /**
		 	 * 测试插入10w数据，用时，在服务器开启后第一次用时10s，后面用时2-4s
		 	 */
		 	List<UserCache> userCacheList = new ArrayList<UserCache>();
		 	for (int i = 0; i < 1000000; i++) {
		 		UserCache userCache =new UserCache();
		 		userCache.setId(i);
		 		userCache.setName(i+"");
		 		userCache.setAge(i);
		 		userCacheList.add(userCache);
			}
		 	userCacheService.insertAll(userCacheList);
		 	
		 	/**
		 	 * 测试查询10w条数据，user_cache 未加索引
		 	 */
		 	/*Example e= new Example(UserCache.class);
		 	e.createCriteria().andGreaterThanOrEqualTo("id", 0);
		 	List<UserCache> userCacheList =userCacheService.selectByExample(e);
		 	System.out.println("无索引--"+userCacheList.size());*/
	        return "userCacheList";  
	    } 
	 
	 /**
	  * del 测试事务 具体看BaseServiceImpl
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/newb/del",method= RequestMethod.POST)
	 @ResponseBody
	 public String del(ModelMap modelMap){
		 	int test=userService.delete(null);
	        return test+"";  
	    }
	 /**
	  *  返回中文字符串,需要在MVC中配置 不然会自动加引号
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/newbb",method= RequestMethod.POST) //,produces ="text/html;charset=UTF-8" 返回UTF-格式
	 @ResponseBody
	 public String showUser(ModelMap modelMap, int userId){  
	        User user = userService.getUserById(userId);
	        modelMap.addAttribute("user", user);
	        String str="hello word 你好世界";
	        return str;  
	    }
	 /**
	  * 返回JSON ,需要在MVC中配置
	  * @return
	  */
	 @RequestMapping(value="/newbs",method=RequestMethod.GET)
	 @ResponseBody   //注释返回JSON 或 String 必须加此注解
	 public Object showUserInfos(){  
	        List<User> user = userService.getUsers();  
	        return user;  
	    } 
	 
	 @RequestMapping(value="/newbs2",method=RequestMethod.GET)   //未加会返回 user 至 newbs2.jsp 页面
	 public Object showUserInfo(){  
	        List<User> user = userService.getUsers();  
	        return user;  
	    } 
	 
	 @RequestMapping(value="/login",method=RequestMethod.POST) 
	 public String login(ModelMap modelMap, String username, String password,HttpSession httpSession){  
		 	int userId=2;
		 	User user = userService.getUserById(userId);
		 	SessionB session = new SessionB(user);
	        modelMap.addAttribute("user", user);
	        //添加到session
	        httpSession.setAttribute("session", session);
	        return "/admin/showInfo";  
	    }
	 /**
	  * session测试,关闭浏览器服务器端的session不会丢失,但浏览器
	  * 端的sessionid会丢失,服务器靠这个sessionid查找session,因此
	  * 下次访问生成时新的sessionid.
	  * @param request
	  * @param response
	  * @throws ServletException
	  * @throws IOException
	  */
	 @RequestMapping(value="/sessionTest",method=RequestMethod.POST) 
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 response.setContentType("text/html;charset=UTF-8");
			 HttpSession session = request.getSession(true);
			 String heading = null;
			 Integer accessCount = (Integer) session.getAttribute("accessCount");
			 if (session.getAttribute("accessCount") == null) {
			 accessCount = new Integer(1);
			 heading = "欢迎您首次登陆这个页面";
			 } else {
			 heading = "欢迎您再次登陆这个页面";
			 accessCount = accessCount + 1;
			 }
			 session.setAttribute("accessCount", accessCount);
			 PrintWriter out = response.getWriter();
			 out.println("<HTML>");
			 out.println("  <HEAD><TITLE>session tracking example</TITLE></HEAD>");
			 out.println("  <BODY>");
			 out.println("  <center>");
			 out.println("<h4>" + heading + "<a href='session'>再次访问</a>"
			 + "</h4>");
			 out.println("<table border='0'>");
			 out.println("<tr bgcolor=\"ffad00\"><td>信息<td>值\n");
			 String state = session.isNew() ? "新会话" : "旧会话";
			 out.println("<tr><td>会话状态：<td>" + state + "\n");
			 out.println("<tr><td>会话ID:<td>" + session.getId() + "\n");
			 out.println("<tr><td>创建时间:<td>");
			 out.println("" + new Date(session.getCreationTime()) + "\n");
			 out.println("<tr><td>最近访问时间:<td>");
			 out.println("" + new Date(session.getLastAccessedTime()) + "\n");
			 out.println("<tr><td>最大不活动时间:<td>" + session.getMaxInactiveInterval()
			 + "S\n");
			 out.println("<tr><td>Cookie:<td>" + request.getHeader("Cookie") + "\n");
			 out.println("<tr><td>已被访问次数:<td>" + accessCount + "\n");
			 out.println("</table>");
			 out.println("  </center>");
			 out.println("  </BODY>");
			 out.println("</HTML>");
			 out.flush();
			 out.close();
			 }
	 /**
	  * 使用forward 使之 重新进入另一个controller /user/newbs
	  * @param modelMap
	  * @param username
	  * @param password
	  * @return
	  */
	 @RequestMapping(value="/loginFor",method=RequestMethod.POST) 
	 public String loginFor(ModelMap modelMap, String username, String password){  
		 	int userId=2;
		 	User user = userService.getUserById(userId);
	        modelMap.addAttribute("user", user);  
	        return "forward:/user/newbs";  
	    }
	 
	 /**
	  *  使用JQ serializeArray 表单格式化传入  这个看views下xzjl的JS
	  *  Spring MVC自动转换失败 只能使用Map<String Object>接受
	  *  使用GSON 转换为 bean
	  * @param 
	  * Spring MVC自动转换失败 使用Map<String Object>接受
	  * @return success字符串
	  */
	 @RequestMapping(value="/xzjl",method=RequestMethod.POST)
	 @ResponseBody
	 public String xzjl(@RequestBody Map<String, Object> userjl)  {  
		 	Gson gson= new Gson();
		 	//GSON 转换JSON to bean
		 	UserData userdata =gson.fromJson(userjl.get("userData").toString(), UserData.class);
		 	//GSON 转换JSON to List<T>
		 	List<UserXL> userxlList =	gson.fromJson(userjl.get("userXL").toString(), new TypeToken<List<UserXL>>(){}.getType());
		 
	        return "success";  
	    }
	 
	 
/*从上个项目复制的文件上传下载   开始----------------------------------------*/
	 /**
	  * 上传文件1  使用上传文件2
	  * @param request
	  * @throws IllegalStateException
	  * @throws IOException
	  */
	 @RequestMapping(value = "/upLoadFile.do", method = RequestMethod.POST)  
	    public void upLoadFile(HttpServletRequest request)  throws IllegalStateException, IOException {
		// @RequestParam("file") MultipartFile file,
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile f = multiRequest.getFile(iter.next());
				if (f != null) {
					// 取得当前上传文件的文件名称
					String myFileName = f.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (!"".equals(myFileName.trim()) ) {
						// 定义上传路径
						String path = "G:\\bean\\"+ myFileName;
						File localFile = new File(path);
						f.transferTo(localFile);
					}
				}
			}
		}
	}
	 
	 
	 @RequestMapping(value="/imgfile",method=RequestMethod.POST)
	 @ResponseBody
	    public String imageUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, ModelMap model,HttpSession httpSession) {  
	        // 获得原始文件名  
	        String fileName = file.getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);
	        int pkid =(Integer) httpSession.getAttribute("PKID");
	        // 新文件名  
	        int cc=fileName.lastIndexOf(".");
	        String newFileName ="tx"+fileName.substring(cc);
	        // 获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("\\img") + "\\"+pkid+"\\"; // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists())  
	            f.mkdirs();  
	        if (!file.isEmpty()) {  
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newFileName);  
	                InputStream in = file.getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        System.out.println("上传图片到:" + path + newFileName);  
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileUrl", path + newFileName);
//	        int re = userdataService.updateZp(pkid);
	        return "success";  
	    }
	 
	 
	 /**
	  * 上传文件2
	  * @param file
	  * @param request
	  * @param model
	  * @return
	  */
	 
	 
	 @RequestMapping(value="/onefile",method=RequestMethod.POST)
	 @ResponseBody
	    public String oneFileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, ModelMap model,HttpSession httpSession) {  
	        // 获得原始文件名  
	        String fileName = file.getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);
	        
	        int pkid =(Integer) httpSession.getAttribute("PKID");
	        // 新文件名  
	        String newFileName =fileName;  
	        // 获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("\\img") + "\\"+pkid+"\\"; // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists())  
	            f.mkdirs();  
	        if (!file.isEmpty()) {  
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newFileName);  
	                InputStream in = file.getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        System.out.println("上传图片到:" + path + newFileName);  
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileUrl", path + newFileName);  
	        return "success";  
	    }
	 
	 @RequestMapping(value="/listFile",method=RequestMethod.GET)  
	 public String listFile(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
	     // 获取上传文件的目录
		 int pkid =(Integer) httpSession.getAttribute("PKID");
	     ServletContext sc = request.getSession().getServletContext();  
	     // 上传位置  
	     String uploadFilePath = sc.getRealPath("\\img") + "\\"+pkid+""; // 设定文件保存的目录  
	     // 存储要下载的文件名
	     Map<String, String> fileNameMap = new HashMap<String, String>();  
	     // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中 
	     File fileList = new File(uploadFilePath);
	     
	     if(!fileList.exists()&&!fileList.isDirectory()){
	    	 fileList.mkdir();
	     }
	     System.out.println("路径"+uploadFilePath);
//	     File f=new File(uploadFilePath+"2zhang.gif");
	     listfile(fileList, fileNameMap);// File既可以代表一个文件也可以代表一个目录  
	     // 将Map集合发送到listfile.jsp页面进行显示  
	     request.setAttribute("fileNameMap", fileNameMap);  
	     return "listFile";  
	 }
	 
	public void listfile(File file, Map<String, String> map) {
		// 如果file代表的不是一个文件，而是一个目录
		if (!file.isFile()) {
			// 列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			// 遍历files[]数组
			for (File f : files) {
				// 递归
				listfile(f, map);
			}
		} else {
			/**
			 * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			 * file.getName().indexOf
			 * ("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
			 * 那么file.getName().substring(file.getName().indexOf("_")+1)
			 * 处理之后就可以得到阿_凡_达.avi部分
			 */
//			String realName = file.getName().substring(file.getName().indexOf("_") + 1);
			String realName = file.getName();
			// file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
		}

	}
	 
	 @RequestMapping(value="/downFile",method=RequestMethod.POST)  
	 public void downFile(HttpServletRequest request,  HttpServletResponse response,HttpSession httpSession) {  
	     System.out.println("1");  
	     // 得到要下载的文件名  
	     String fileName = request.getParameter("filename");  
	     System.out.println("2");  
	     try {  
	         fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");  
	         System.out.println("3");  
	         // 获取上传文件的目录  
	         ServletContext sc = request.getSession().getServletContext();  
	         System.out.println("4");  
	         // 上传位置 
	         int pkid =(Integer) httpSession.getAttribute("PKID");
	         String fileSaveRootPath = sc.getRealPath("\\img") + "\\"+pkid+"\\";   
	           
	         System.out.println(fileSaveRootPath + "\\" + fileName);  
	         // 得到要下载的文件  
	         File file = new File(fileSaveRootPath + "\\" + fileName);  
	           
	         // 如果文件不存在  
	         if (!file.exists()) {  
	             request.setAttribute("message", "您要下载的资源已被删除！！");  
	             System.out.println("您要下载的资源已被删除！！");  
	             return;  
	         }  
	         // 处理文件名  
	         String realname = fileName.substring(fileName.indexOf("_") + 1);  
	         // 设置响应头，控制浏览器下载该文件  
	         response.setHeader("content-disposition", "attachment;filename="  
	                 + URLEncoder.encode(realname, "UTF-8"));  
	         // 读取要下载的文件，保存到文件输入流  
	         FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);  
	         // 创建输出流  
	         OutputStream out = response.getOutputStream();  
	         // 创建缓冲区  
	         byte buffer[] = new byte[1024];  
	         int len = 0;  
	         // 循环将输入流中的内容读取到缓冲区当中  
	         while ((len = in.read(buffer)) > 0) {  
	             // 输出缓冲区的内容到浏览器，实现文件下载  
	             out.write(buffer, 0, len);  
	         }  
	         // 关闭文件输入流  
	         in.close();  
	         // 关闭输出流  
	         out.close();  
	     } catch (Exception e) {  
	     }  
	 }
/*从上个项目复制的文件上传下载   结束----------------------------------------*/ 
	 
}
