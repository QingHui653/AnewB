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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import newb.c.backend.sql.model.SessionBean;
import newb.c.backend.sql.model.basemodel.User;
import newb.c.backend.sql.model.UserData;
import newb.c.backend.sql.model.UserXL;
import newb.c.backend.sql.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	//常用工具类   时间不能用在这 ,不然每次进入不会重新new 一个对象,
	//会使得时间有BUG
	//GregorianCalendar cal=new GregorianCalendar();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//1.8
	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	//google JSON工具
	Gson gson = new Gson();
	
	
	@RequestMapping("date")
	@ResponseBody
	public Object date(@DateTimeFormat(iso=ISO.DATE) Date date) {
		String dateString = sdf.format(date);
		return dateString;
	}
	
	/**
	 * 转换时间类型现在使用@DateTimeFormat代替
	 * 数字使用@NumberFormat 
	 * @param binder
	 */
	/* 与@DateTimeFormat注解只能存在一种 */
	/*@InitBinder
	public void dataBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    PropertyEditor propertyEditor = new CustomDateEditor(dateFormat, true); // 第二个参数表示是否允许为空
	    binder.registerCustomEditor(Date.class, propertyEditor);
	}*/
	
	@ApiOperation(value = "测试传入参数为单个bean 使用了 检验工具检验bean ", notes = "")
	@RequestMapping(value="/bean",method= {RequestMethod.POST})
	/*@ResponseBody*/
	// 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	public Object showUserInfoBean(ModelMap modelMap,@Valid User user,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> erroeList= result.getFieldErrors();
			for (FieldError error : erroeList) {
				System.out.println(error.getField()+" * "+error.getDefaultMessage());
				modelMap.put("ERR_"+error.getField(), error.getDefaultMessage());
			}
			return "web/upload";
		}
		System.out.println("Spring mvc自动序列化得到的user "+user.getOid()+"  "+user.getUsername()+" "+user.getPassword());
		return "/views/jsp/user/showInfo";
	}
	
	/**
	 * Spring MVC无法完成对多个参数,需要序列化和不需要序列化的参数进行注入
	 * 这时传入JSON手动进行反序列化
	 * 或 /AnewB/user/morebean?userId=3&json="+JSON.stringify(user),data:user,
	 * 这样不用序列化的直接传入,需要序列化通过ajax data传入
	 * 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	 * @param userId
	 * @param user
	 * @param json
	 * @return
	 */
	@ApiOperation(value = "测试传入参数为单个bean+其他参数", notes = "")
	@RequestMapping(value="/morebean",method= {RequestMethod.POST})
	@ResponseBody
	public Object showUserInfoMoreBean(ModelMap modelMap,Integer userId,User user,String json){
			System.out.println("接受到的 userId "+userId);
		    System.out.println("json序列化 "+json);
		    User userFromJson=gson.fromJson(json, User.class);
		    
		    JsonObject data = new JsonParser().parse(json).getAsJsonObject();
		    JsonArray list = data.get("list").getAsJsonArray();
		    for (JsonElement l : list) {
		    	JsonObject order = l.getAsJsonObject();
		    	String supplierCompanyCode = order.get("supplierCompanyCode").getAsString();// 供应商id
		    }
		    
		    System.out.println("反序列化得到的user "+userFromJson.getOid()+"  "+userFromJson.getUsername());
		    return "/views/jsp/user/showInfo";
	}
	
	
	@ResponseBody
	@RequestMapping("/addCookie")
	public void addCookie(HttpServletRequest request,HttpServletResponse response){
	    Cookie cookie=new Cookie("test","hello cookie"+System.currentTimeMillis());
	    cookie.setMaxAge(300);//设置生命周期以秒为单位
	    //cookie.setDomain("localhost");//设置域名，跨域访问时使用
	    //cookie.setPath("/");//设置路径，共享cookie时使用
	    response.addCookie(cookie);
	    return;
	}
	
	@ResponseBody
	@RequestMapping("/showCookie")
	public void showCookie(HttpServletRequest request,HttpServletResponse response){
	    Cookie[] cookies=request.getCookies();//获取请求中的所有cookie
	    if(null!=cookies) {
	        for (Cookie cookie : cookies) {
	            //输出cookie的标志(name)和值(value)
	            System.out.println(cookie.getName() + " - " + cookie.getValue());
	        }
	    }
	    else{
	        System.out.println("cookies is null");
	    }
	  
	    return;
	}
	
	@ResponseBody
	@RequestMapping("/getCookie")
	public void getCookieBySpring(@CookieValue(value = "test", defaultValue = "hello") String cookie) {
	        return;
	}
	
	 /**
	  * 测试session
	  * @param modelMap
	  * @param username
	  * @param password
	  * @param httpSession
	  * @return
	  */
	 @RequestMapping(value="/login",method=RequestMethod.GET)
	 @ApiOperation("测试session")
	 public String login(ModelMap modelMap, String username, String password,HttpSession httpSession){
		 	int userId=3;
		 	User user = userService.getUserById(userId);
		 	SessionBean session = new SessionBean(user);
	        modelMap.addAttribute("user", user);
	        //添加到session
	        httpSession.setAttribute("resources/session", session);
	        return "/views/jsp/admin/showInfo";
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
	 @RequestMapping(value="/sessionTest",method=RequestMethod.GET)
	 @ApiOperation("查看session")
	 public void doGet(HttpServletRequest request, HttpServletResponse response,HttpSession session)
			 throws ServletException, IOException {
			 response.setContentType("text/html;charset=UTF-8");
//			 HttpSession session = request.getSession(true);
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
			 out.println("<h4>" + heading + "<a href='sessionTest'>再次访问</a>"
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
	  *  使用JQ serializeArray 表单格式化传入  这个看views下xzjl的JS
	  *  Spring MVC自动转换失败 只能使用Map<String Object>接受
	  *  使用GSON 转换为 bean
	  * @param
	  * Spring MVC自动转换失败 使用Map<String Object>接受
	  * @return success字符串
	  */
	 @SuppressWarnings("unused")
	@RequestMapping(value="/xzjl",method=RequestMethod.POST)
	 @ApiOperation("招聘系统多表单序列化 ")
	 @ResponseBody
	 public String xzjl(@RequestBody Map<String, Object> userjl)  {
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
	 @ApiOperation("招聘系统中的文件上传1")
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
	 @ApiOperation("招聘系统中的文件上传3")
	 @ResponseBody
	    public String imageUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, ModelMap model,HttpSession httpSession) {
	        // 获得原始文件名
	        String fileName = file.getOriginalFilename();
	        System.out.println("原始文件名:" + fileName);
	        UUID pkid =UUID.randomUUID();
	        // 新文件名
	        int cc=fileName.lastIndexOf(".");
	        String newFileName =pkid+fileName.substring(cc);
	        // 获得项目的路径
	        ServletContext sc = request.getSession().getServletContext();
	        // 上传位置
	        String path = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
	        File f = new File(path);
	        if (!f.exists()){
				f.mkdirs();
			}
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
	        return "/AnewB/img/"+newFileName;
	    }


	 /**
	  * 上传文件2
	  * @param file
	  * @param request
	  * @param model
	  * @return
	  */


	 @RequestMapping(value="/onefile",method=RequestMethod.POST)
	 @ApiOperation("招聘系统中的文件上传2")
	 @ResponseBody
	    public String oneFileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, ModelMap model,HttpSession httpSession) {
		 	// 获得原始文件名
	        String fileName = file.getOriginalFilename();
	        System.out.println("原始文件名:" + fileName);
	        UUID pkid =UUID.randomUUID();
	        // 新文件名
	        int cc=fileName.lastIndexOf(".");
	        String newFileName =pkid+fileName.substring(cc);
	        // 获得项目的路径
	        ServletContext sc = request.getSession().getServletContext();
	        // 上传位置
	        String path = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
	        File f = new File(path);
	        if (!f.exists()){
				f.mkdirs();
			}
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
	 @ApiOperation("列出此id中所上传的全部文件 ")
	 public String listFile(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
	     // 获取上传文件的目录
	     ServletContext sc = request.getSession().getServletContext();
	     // 上传位置
	     String uploadFilePath = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
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
	     return "web/upload";
	 }
	/* 列出文件   */
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
	
	 @RequestMapping(value="/downFile",method=RequestMethod.GET)
	 @ApiOperation("下载文件")
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
	         String fileSaveRootPath = sc.getRealPath("\\img") + "\\";

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
	         response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(realname, "UTF-8"));
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
	    	 e.printStackTrace();
	     }
	 }
/*从上个项目复制的文件上传下载   结束----------------------------------------*/
	 
}
