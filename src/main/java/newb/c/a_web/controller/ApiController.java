package newb.c.a_web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import newb.c.a_spring.api.weather.retrofit.RetrofitWeather;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import io.swagger.annotations.ApiOperation;
import newb.c.a_spring.a_module.qrcode.MatrixToImageWriter;
import newb.c.a_spring.api.weather.weather;
import newb.c.a_spring.backend.sql.model.RepList;
import newb.c.a_spring.backend.sql.model.basemodel.Result;
import newb.c.a_spring.backend.sql.model.basemodel.TOrder;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import newb.c.a_spring.backend.sql.service.ResultService;
import newb.c.a_spring.backend.sql.service.TOrderService;
import newb.c.util.ExcelUtil;
import newb.c.util.authCode.Captcha;
import newb.c.util.authCode.GifCaptcha;
import newb.c.util.authCode.SpecCaptcha;
import newb.c.util.db.DataHandle;
import tk.mybatis.mapper.entity.Example;


@Controller
@RequestMapping("api")
@Slf4j
public class ApiController {
	@Autowired
	private ResultService resultService;
	@Autowired
	private TOrderService tOrderService;

	public weather weather = new weather();

	public RetrofitWeather retrofitWeather = new RetrofitWeather();

	DataHandle data =new DataHandle();

	/**
	 *   获取excel 文档  JXL
	 */
	@RequestMapping(value="/getExcByJXL",method=RequestMethod.GET)
	@ApiOperation("测试使用JXL获取excel文档  已替换为POI 去掉JXL jar包")
	@Test
	@Deprecated
	public void getExcByJXL() throws SQLException,IOException{
		/*String[] title = {"姓名"};
		String filePath = "g:\\newbc.xls";
		Connection send = DriverManager.getConnection("proxool.local");
		String a0101 = "select username from user";
		List<String> a0101List = data.getDataStringList(a0101, send);
		WritableWorkbook wwb;
		OutputStream out = new FileOutputStream(filePath);
		wwb = Workbook.createWorkbook(out);
		WritableSheet sheet = wwb.createSheet("第一页", 0);
		Label label;
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			label = new Label(i, 0, title[i]);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}
		for (int j = 0; j < a0101List.size(); j++) {
			label = new Label(1, j+1, a0101List.get(j).toString());
			sheet.addCell(label);
		}
		log.info("保存路径为-- "+filePath);
		wwb.write();
		wwb.close();
		log.info("保存成功");*/
	}

	/**
	 *   获取excel 文档 POI
	 */
	@RequestMapping(value="/getExcByPOI",method=RequestMethod.GET)
	@ApiOperation("测试使用POI获取excel文档  ")
	@Test
	public void getExcByPOI(HttpServletResponse response) throws SQLException,IOException{
		ExcelUtil excelUtil =new ExcelUtil();
//		excelUtil.demoExport(response);

		String[] headList ={"姓名","班级"};
		List<String[]> bodyList =new ArrayList<String[]>();
			String[] body1 ={"李明","As178"};
			String[] body2 ={"李明2","As179"};
		bodyList.add(body1);
		bodyList.add(body2);

		excelUtil.simpleExport(response, headList,bodyList);

	}

	@RequestMapping(value="/getrep",method=RequestMethod.GET)
	@ApiOperation("测试使用通用mapper,自定义通用接口删除数据,重定向到bootstarap")
	public String getrep() {
		resultService.CommonDelMapper("50");
		return "forward:/views/jsp/bootstrap.jsp";
	}

	@RequestMapping(value="/getrep2",method=RequestMethod.GET)
	@ApiOperation("测试使用通用mapper获取表值,重定向到bootstarap")
	public Object getrep2(ModelMap modelMap) {
		Example example = new Example(Result.class);
		example.createCriteria().andEqualTo("f1", "QingHui653").andCondition("oid>50");
		List<Result>  r =resultService.selectByExample(example);
		modelMap.addAttribute("resultList", r);
		Result r1= resultService.selectByKey(53);
		modelMap.addAttribute("result", r1);
		return "forward:/views/jsp/bootstrap.jsp";
	}


	@RequestMapping(value="/rep/getRes",method=RequestMethod.GET)
	@ApiOperation("获取爬虫爬取信息")
	public String getRepRes(ModelMap modelMap) {
		List<RepList> repList= resultService.getRepList();
		modelMap.addAttribute("repList", repList);
		return "/views/jsp/rep/newb";
	}

	@RequestMapping(value="/rep/{group}",method=RequestMethod.GET)
	@ApiOperation("通过公司名获取爬虫爬取信息")
	public String getGroupRes(ModelMap modelMap,@PathVariable String group) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "/views/jsp/rep/GroupList";
	}

	@RequestMapping(value="/rep/{group}/{project}",method=RequestMethod.GET)
	@ApiOperation("通过公司名和工程名，获取爬虫爬取信息")
	public String getProject(ModelMap modelMap,@PathVariable String group,@PathVariable String project) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group).andEqualTo("f4", project);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "/views/jsp/rep/GroupList";
	}


	@ApiOperation(value="根据城市获取天气情况")
	@RequestMapping(value="/weather/{cityname}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryWeather(@PathVariable String cityname) throws IOException {
//		String res= weather.queryWeather(cityname);
		String res =retrofitWeather.queryWeather(cityname);
		return res;
	}

	@RequestMapping(value="/sharding",method=RequestMethod.GET)
	@ApiOperation("sharding-jdbc测试方法")
	@ResponseBody
	public Object shardingTest() {
		TOrder tO0=new TOrder(0, 2, "第0条数据");
		int bool0= tOrderService.insertByXML(tO0);
		TOrder tO1=new TOrder(1, 3, "第1条数据");
		tOrderService.insertByXML(tO1);
		TOrder tO2=new TOrder(2, 4, "第2条数据");
		tOrderService.insertByXML(tO2);

		/*TOrder tO1=new TOrder(1, 1, "第1条数据");
		int bool1= tOrderService.insertByMapper(tO1);
		TOrder tO2=new TOrder(2, 2, "第2条数据");
		int bool2= tOrderService.insertByComm(tO2); */
		/**
		 * 分页插件不支持主键自增,SELECT LAST_INSERT_ID();配置在model中
		 */
		/*User u = new User(10, "xx", "cc");
		int bool0=userService.save(u);*/

		return bool0;
	}

	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	@ApiOperation("获取验证码 gif版")
	public void getGifCode(HttpServletResponse response,HttpServletRequest request,HttpSession session){
		try {
			response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/gif");
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(146,33,4);
	        //存入Session
	        session = request.getSession(true);
	        System.out.println("hhh 1"+captcha);
	        System.out.println("hhh 2"+captcha.text());
	        System.out.println("hhh 3"+captcha.text().toLowerCase());
	        session.setAttribute("_code",captcha.text().toLowerCase());
	        //输出
	        captcha.out(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取验证码异常：%s",e.getMessage());
		}
	}

	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="getJPGCode",method=RequestMethod.GET)
	@ApiOperation("获取验证码 jpg版")
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpg");
			/**
			 * jgp格式验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new SpecCaptcha(146,33,4);
			HttpSession session = request.getSession(true);
			//存入Session
			session.setAttribute("_code",captcha.text().toLowerCase());
			//输出
			captcha.out(response.getOutputStream());
		} catch (Exception e) {
			log.error("获取验证码异常：%s",e.toString());
		}
	}


	@RequestMapping(value="throwEx",method=RequestMethod.GET)
	@ApiOperation("测试 抛出异常")
	public void throwEx() throws Exception {
		switch(1) {
        case 1:
            throw new Exception("浙江温州，浙江温州，浙江温州。最大皮革厂倒闭了");
        default:
        	throw new Exception("22222222222");
        }
	}

	@RequestMapping(value="shardDatasource",method=RequestMethod.GET)
	@ApiOperation("测试分库  为分布式事务先做 测试")
	public Object shardDatasource() throws Exception {
		TOrder t2= new TOrder(1,2 , "進入newb t_order_1"); //偶数进newb t_order_1
		TOrder t1= new TOrder(2,1 , "進入newb2 t_order_0"); //奇数进newb2 t_order_0
		tOrderService.save(t1);

		tOrderService.save(t2);

		return "测试分库表完成";
	}

	/**
	 * 未加分布式事务配置，事务暂时不可用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="shardDatasourceTestJTA",method=RequestMethod.GET)
	@ApiOperation("测试分库  为分布式事务先做 测试")
	public Object shardDatasourceTestJTA() throws Exception {
		tOrderService.testJTA();

		return "测试分布式 事务";
	}

	/**
	 * 同一个库的事务是可用的
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="TestOneData",method=RequestMethod.GET)
	@ApiOperation("测试一个库下的事务情况")
	public Object TestOneData() throws Exception {
		tOrderService.testOneData();

		return "一个库下的事务情况";
	}

	@RequestMapping(value="ZxingTest",method=RequestMethod.GET)
	@ApiOperation("测试二维码")
	public void zxingTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();

			String content = "这是测试xing二维码生成";
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object> ();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix;
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
			// 生成二维码
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);

			out.close();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * kindEditor 图片跨域上传的服务后台
	 */
	@RequestMapping(value = "/kindUpload", method = RequestMethod.POST)
    public Object kindUpload(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file,String redirectUrl) {
        try {
            String referer = request.getHeader("referer");
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);
            if (mathcer.find()) {
                String callBackPath = mathcer.group();// 请求来源a.com
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        		boolean success = false;
        		Map<String, Object> result = new HashMap<String, Object>();
        		String id = "2";
        		if (id != null) {
        			success = true;
        		}

        		String url =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

                Map<String, Object> map =new HashMap<>();
                map.put("error",0);
                map.put("url", url + "/file/download?id="+id);
                StringBuffer sBuffer2 = new StringBuffer("redirect:").append(redirectUrl);
                ModelAndView modelAndView = new ModelAndView(sBuffer2.toString());
                for (String key : map.keySet()) {
                    modelAndView.addObject(key, map.get(key));//将返回的参数设置到modelAndView中，最后也是以？之后带的参数出现
                }
                return modelAndView;

            } else {
                log.info("upload referer not find");
            }
        } catch (Exception e) {
            log.error("upload error", e);
        }
        return null;
    }

	/**
	 * gson在json中不允许空格 会报错
	 * @param data
	 */
	@GetMapping("fastJson")
	private void fastJson(String data) {
		log.info("接受到json "+data.toString());
		Map<String,Object> map = (Map<String, Object>) JSON.parse(data.toString());
		Map<String, String> params = (Map<String, String>) JSON.parse(map.get("parameters").toString());
		List<User> spuParamList = JSON.parseArray(map.get("list").toString(),User.class);
		log.info("转为params "+params.toString());
	}

	/**
	 * gson在json中不允许空格 会报错
	 * @param data
	 */
	@GetMapping("gson")
	private void gson(String data) {
		Gson gson =new Gson();
		log.info("接受到json "+data.toString());
		Map<String, Object> mapData = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		log.info("转为map "+mapData.toString());
		Map<String, String> params = gson.fromJson(mapData.get("parameters").toString(), new TypeToken<Map<String, String>>(){}.getType());
		List<User> spuParamList = gson.fromJson(mapData.get("list").toString(), new TypeToken<List<User>>(){}.getType());
		log.info("转为params "+params.toString());
	}
}
