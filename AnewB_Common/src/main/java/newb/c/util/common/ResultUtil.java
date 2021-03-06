package newb.c.util.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回参数工具类
 * successful:返回是否成功
 * Resultmessage:输出信息
 * ResultBean:返回bean
 * ResultBeanList：返回bean
 * ResultMap:返回多种bean
 * pageNo:分页页数
 * pageSize :分页数
 * @author woshizbh
 *
 */
public class ResultUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1003555356L;
	
	
	private boolean successful;		//返回结果
	
	private String Resultmessage;  //返回结果信息
	
	private Object ResultBean;  //返回单个bean
	
	private List<Object> ResultBeanList; //返回ListBean
	
	private Map<String,Object> ResultMap; //返回Map
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private Integer totalCount;
	
	public ResultUtil() {
		super();
	}

	public ResultUtil(boolean successful, String resultmessage) {
		super();
		this.successful = successful;
		Resultmessage = resultmessage;
	}

	public ResultUtil(boolean successful, String resultmessage, Object resultBean) {
		super();
		this.successful = successful;
		Resultmessage = resultmessage;
		ResultBean = resultBean;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public Object getResultBean() {
		return ResultBean;
	}

	public void setResultBean(Object resultBean) {
		ResultBean = resultBean;
	}

	public List<Object> getResultBeanList() {
		return ResultBeanList;
	}

	public void setResultBeanList(List<Object> resultBeanList) {
		ResultBeanList = resultBeanList;
	}

	public Map<String, Object> getResultMap() {
		return ResultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		ResultMap = resultMap;
	}

	public String getResultmessage() {
		return Resultmessage;
	}

	public void setResultmessage(String resultmessage) {
		Resultmessage = resultmessage;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		String retValue = "";
		retValue = "Result ( " + super.toString() + "    " + "successful = " + successful + "    " + "resultList = " + ResultBeanList + "    " + "resultMap = " + ResultMap + "    " + "result = " + ResultBean + "    " + "message = " + Resultmessage + "    " + " )";
		return retValue;
	}
	
	
}
