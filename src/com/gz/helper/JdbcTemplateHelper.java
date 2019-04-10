package com.gz.helper;

import static com.gz.common.StaticData.SUCCESS;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
/**
 * @ClassName: JdbcTemplateHelper
 * @Description: JdbcTemplate帮助类
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB
 * @date 2018-07-19 上午9:27:09
 * @version V1.0
 */
public class JdbcTemplateHelper extends JdbcTemplate {
	
	/**
	 * 插入数据库表
	 * @param table 表名
	 * @param fields 字段名称
	 * @param values 字段对应的值
	 * @return
	 */
	public void add(String table,final String[] fields, final Object[] values) {
		if(fields.length == values.length){
			StringBuilder builder = new StringBuilder();
			builder.append("insert into ");
			builder.append(table);
			builder.append("(");
			for(int i = 0; i < fields.length; i++) {
				builder.append(fields[i]);
				if(i != fields.length - 1)builder.append(",");
			}
			builder.append(") values (");
			for(int i = 0; i < fields.length; i++) {
				builder.append("?");
				if(i != fields.length - 1)builder.append(",");
			}
			builder.append(")");
			executeSql(builder.toString(), values);
		}
	}
	
	/**
	 * 执行流程的存储过程:INSERT_K2_RQ_EXTENSION
	 * @param rqId
	 */
	public void callRqExtension(String rqId) {
		String eventid = WebAppHelper.newEventId();
		Object[] values = {eventid,rqId};
		executeSql("{call INSERT_K2_RQ_EXTENSION (?,?)}", values);
	}
	/**
	 * 执行流程的存储过程:INSERT_RQ_DATA_INSTANCE
	 * @param inputname 一般为表的中文名称
	 * @param tableName 表名称
	 * @param eventId table的eventid
	 * @param rqId 
	 */
	public void callRqInstance(String inputname, String tableName, String eventId, String rqId){
		String id = WebAppHelper.newEventId();
		Object[] values = {id, inputname, tableName, eventId, rqId};
		executeSql("{call INSERT_RQ_DATA_INSTANCE(?,?,?,?,?)}", values);
	}
	
	/**
	 * 执行流程的存储过程:INSERT_K2_RQ_EXTENSION和INSERT_RQ_DATA_INSTANCE
	 * @param inputname 一般为表的中文名称
	 * @param tableName 表名称
	 * @param eventId table的eventid
	 */
	public void addK2Flow(String inputName, String tableName, String eventId) {
		String rqId = WebAppHelper.newEventId();
		//1.调用存储过程INSERT_K2_RQ_EXTENSION
		callRqExtension(rqId);
		//2.调用存储过程callK2Ins
		callRqInstance(inputName, tableName, eventId, rqId);
	}	
	/**
	 * 删除流程中的记录
	 * @param eventId,table表中的eventid
	 */
	public void deleteRqK2(String eventId) {
		String sql1 = "delete from sys_wf_k2_rq_extension ex where ex.rqid in(" +
				"select t.rq_data_instance_id from sys_wf_rq_data_instance t where t.datainput_data_id=?)";
		String sql2 = "delete from sys_wf_rq_data_instance t where t.datainput_data_id=?";
		executeSql(sql1, new String[]{eventId});
		executeSql(sql2, new String[]{eventId});
	}
	/**
	 * 通用的sql执行,可以为insert,update,delete,调用存储过程
	 * @param sqlTemplate,格式为:insert into XXX (id, name) values (?,?);update XXX set name=? where id=?
	 * @param values,对其中?的填充值
	 * @return
	 */
	public String executeSql(String sqlTemplate, final Object[] values) {
		super.update(sqlTemplate,new PreparedStatementSetter() {
			//@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				for(int i = 1; i <= values.length; i++) {
					if(values[i-1] instanceof Date) {
						ps.setTimestamp(i, new Timestamp(((Date)values[i-1]).getTime()));
					} else {
						ps.setObject(i, values[i-1]);
					}
				}
			}
		});
		return SUCCESS;
	}
	
}
