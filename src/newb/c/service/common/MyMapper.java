package newb.c.service.common;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

/**
 * 继承自己的MyMapper
 *
 */
public interface MyMapper<T> extends Mapper<T>{
	
	/**
	 * 通用查询使用反射
	 * @param queryStr 查询的条件
	 * @return
	 */
	@SelectProvider(type = MyMapperImpl.class, method = "dynamicSQL")
	List<T> CommonSelMapper(String queryStr);
	
	/**
	 * 通用删除使用反射
	 * @param cl  class文件
	 * @param queryStr 删除的条件
	 * @return
	 */
	@DeleteProvider(type = MyMapperImpl.class, method = "dynamicSQL")
	int CommonDelMapper(String queryStr);
}
