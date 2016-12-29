/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package newb.c.backend.service.impl;

import newb.c.backend.model.basemodel.User;
import newb.c.backend.service.IService;
import newb.c.backend.service.common.MyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by liuzh on 2014/12/11.
 */
public abstract class BaseServiceImpl<T> implements IService<T> {

    @Autowired
    protected MyMapper<T> mapper;

    public MyMapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return mapper.insert(entity);
    }
    
    @SuppressWarnings("finally")
	@Transactional //事务注解
    public int delete(Object key) {
    	if(key==null){
    		try {
    			User u= (User) mapper.selectByPrimaryKey(4);
        		System.out.println("-------测试事务--还未删除所以能查询-"+u.toString());
    			mapper.deleteByPrimaryKey(4);
        		User u2= (User) mapper.selectByPrimaryKey(4);
        		System.out.println("-------测试事务--已删除在事务中不可读,但还未提交在数据库中可见-"+u2.toString());
        		throw new RuntimeException("test"); 
			} catch (Exception e) {
				System.out.println("测试事务");
			}finally {
				//还未回滚所以查询不出
				User u= (User) mapper.selectByPrimaryKey(4);
				System.out.println("-------测试事务已删除在事务中不可读,但还未提交在数据库中可见---"+u.toString());
				//正式删除,提交至数据库
		        return mapper.deleteByPrimaryKey(key);
			}
    	}
    	//正式删除,提交至数据库
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public List<T> CommonSelMapper(String key) {
        return mapper.CommonSelMapper(key);
    }
    //TODO 其他...
    public int CommonDelMapper(String key) {
        return mapper.CommonDelMapper(key);
    }
}
