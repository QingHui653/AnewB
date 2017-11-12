package newb.c.util.db;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DataHandle {
	
	
	private PreparedStatement pstmt;
	private Statement st;
	private ResultSet rs;


	/**
	 * 得到数组集合<br/>
	 * 
	 * @param sql
	 * @return dataArrayList
	 * @throws SQLException
	 */
	public List<String[]> getDataArrayList(String sql,Connection conn) throws SQLException {
		List<String[]> dataArrayList = new ArrayList<String[]>();
		try {
			setResultSet(sql,conn);
			int colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				String[] dataArray = getArray(rs, colCount);
				dataArrayList.add(dataArray);
			}
			return dataArrayList;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 得到字符串集合<br/>
	 * 
	 * @param sql
	 * @return dataArrayList
	 * @throws SQLException
	 */
	public List<String> getDataStringList(String sql,Connection conn) throws SQLException {
		List<String> dataArrayList = new ArrayList<String>();
		try {
			setResultSet(sql,conn);
			while (rs.next()) {
				dataArrayList.add(rs.getString(1));
			}
			return dataArrayList;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 得到对象集合<br/>
	 * 
	 * @param sql
	 * @return cls,rs
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public List<Object> getDataObjectList(Class<?> cls, String sql,Connection conn)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<Object> dataObjectList = new ArrayList<Object>();
		try {
			setResultSet(sql,conn);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Object object = getObject(cls, rsmd);
				
				dataObjectList.add(object);
			}
			return dataObjectList;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 得到对象集合<br/>
	 * 
	 * @param sql
	 * @return dataObject
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getDataObject(Class<?> cls, String sql,Connection conn) throws SQLException,
			InstantiationException, IllegalAccessException {
		try {
			setResultSet(sql,conn);
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()) {
				Object object = getObject(cls, rsmd);
				return object;
			}
			return null;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 得到一个数组
	 * 
	 * @param sql
	 * @return dataArray
	 * @throws SQLException
	 */
	public String[] getDataArray(String sql,Connection conn) throws SQLException {
		String[] dataArray = null;
		try {
			setResultSet(sql,conn);
			int colCount = rs.getMetaData().getColumnCount();
			if (rs.next())
				dataArray = getArray(rs, colCount);
			return dataArray;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 得到单个字符
	 * 
	 * @param sql
	 * @return object
	 * @throws SQLException
	 */
	public String getDataString(String sql,Connection conn) throws SQLException {
		String object = null;
		try {
			setResultSet(sql,conn);
			if (rs.next())
				object = rs.getString(1);
			return object;
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 更新单条数据
	 * 
	 * @param sql
	 * @return 0/1
	 * @throws SQLException
	 */
	public int excuteUpdate(String sql,Connection conn) throws SQLException {
		try {
			pstmt = conn.prepareStatement(sql);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}
	
	/**
	 * 更新CLOB数据类型
	 * 
	 * @param sql,Strin TEXT
	 * @return 0/1
	 * @throws SQLException
	 * SQL:update
	 */
	public int excuteText(String sql,String text,Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		String sql = "insert into demo(pkid,clob) values('77',?)";
		
		try {
			pstmt=conn.prepareStatement(sql);
//			System.out.println("TEXT  "+text);
			Reader clobReader = new StringReader(text);
			pstmt.setCharacterStream(1,clobReader,text.length());
//			System.out.println("预编译"+pstmt);
			int num = pstmt.executeUpdate();
			if (num>0) {
				System.out.println("成功");
			}else {
				System.out.println("失败");
			}
			clobReader.close();
			return num;
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			refeases(pstmt, rs, pstmt);
		}
		return 0;
	}
	
	/**
	 * 更新单条数据
	 * 
	 * @param sql
	 * @param values
	 * @return 0/1
	 * @throws SQLException
	 */
	public int excuteUpdate(String sql, List<Object> values,Connection conn)
			throws SQLException {
		try {
			setPreparedStatement(sql, values,conn);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	/**
	 * 批Sql处理
	 * 
	 * @param sqlList
	 * @return true/false
	 * @throws SQLException
	 */
	public int excuteUpdates(List<String> sqlList,Connection conn) throws SQLException {
		int count = 0;
		try {
			st = conn.createStatement();
			conn.setAutoCommit(false);
			for (String sql : sqlList) {
				st.executeUpdate(sql);
				count++;
			}
			conn.commit();
			conn.setAutoCommit(true);
			return count;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			refeases(pstmt, rs , st);
		}
	}

	private void setResultSet(String sql,Connection conn) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}

	private void setPreparedStatement(String sql, List<Object> values,Connection conn)
			throws SQLException {
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == null)
				pstmt.setObject(i + 1, values.get(i), Types.VARCHAR);
			else
				pstmt.setObject(i + 1, values.get(i));
		}
	}

	private Object getObject(Class<?> cls, ResultSetMetaData rsmd)
			throws InstantiationException, IllegalAccessException, SQLException {
		Object object = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			Object value = rs.getObject(i);
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				if (field.getName().equalsIgnoreCase(rsmd.getColumnName(i))) {
					boolean flag = field.isAccessible();
					field.setAccessible(true);
					field.set(object, value);
					field.setAccessible(flag);
				}
			}
		}
		return object;
	}

	private String[] getArray(ResultSet rs, int colCount) throws SQLException {
		String[] dataArray = new String[colCount];
		for (int i = 1; i <= colCount; i++) {
			if (rs.getString(i) == null&&rs.getString(i) != null){
				System.out.println("RS"+rs.getString(i));
				dataArray[(i - 1)] = "";
			}else{
				ResultSetMetaData rsmd = rs.getMetaData(); 
				String type = rsmd.getColumnTypeName(i);
				String result = "";
				if("CLOB".equals(type)){
					Clob clob = rs.getClob(i);
					result = clob.getSubString((long)1,(int)clob.length());
				}else{
					result = rs.getString(i);
				}
					dataArray[(i - 1)] = result;
			}
		}
		return dataArray;
	}

	public void refeases(PreparedStatement pstmt, ResultSet rs, Statement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}