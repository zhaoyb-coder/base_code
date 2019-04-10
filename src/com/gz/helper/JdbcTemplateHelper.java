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
 * @Description: JdbcTemplate������
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB
 * @date 2018-07-19 ����9:27:09
 * @version V1.0
 */
public class JdbcTemplateHelper extends JdbcTemplate {
	
	/**
	 * �������ݿ��
	 * @param table ����
	 * @param fields �ֶ�����
	 * @param values �ֶζ�Ӧ��ֵ
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
	 * ִ�����̵Ĵ洢����:INSERT_K2_RQ_EXTENSION
	 * @param rqId
	 */
	public void callRqExtension(String rqId) {
		String eventid = WebAppHelper.newEventId();
		Object[] values = {eventid,rqId};
		executeSql("{call INSERT_K2_RQ_EXTENSION (?,?)}", values);
	}
	/**
	 * ִ�����̵Ĵ洢����:INSERT_RQ_DATA_INSTANCE
	 * @param inputname һ��Ϊ�����������
	 * @param tableName ������
	 * @param eventId table��eventid
	 * @param rqId 
	 */
	public void callRqInstance(String inputname, String tableName, String eventId, String rqId){
		String id = WebAppHelper.newEventId();
		Object[] values = {id, inputname, tableName, eventId, rqId};
		executeSql("{call INSERT_RQ_DATA_INSTANCE(?,?,?,?,?)}", values);
	}
	
	/**
	 * ִ�����̵Ĵ洢����:INSERT_K2_RQ_EXTENSION��INSERT_RQ_DATA_INSTANCE
	 * @param inputname һ��Ϊ�����������
	 * @param tableName ������
	 * @param eventId table��eventid
	 */
	public void addK2Flow(String inputName, String tableName, String eventId) {
		String rqId = WebAppHelper.newEventId();
		//1.���ô洢����INSERT_K2_RQ_EXTENSION
		callRqExtension(rqId);
		//2.���ô洢����callK2Ins
		callRqInstance(inputName, tableName, eventId, rqId);
	}	
	/**
	 * ɾ�������еļ�¼
	 * @param eventId,table���е�eventid
	 */
	public void deleteRqK2(String eventId) {
		String sql1 = "delete from sys_wf_k2_rq_extension ex where ex.rqid in(" +
				"select t.rq_data_instance_id from sys_wf_rq_data_instance t where t.datainput_data_id=?)";
		String sql2 = "delete from sys_wf_rq_data_instance t where t.datainput_data_id=?";
		executeSql(sql1, new String[]{eventId});
		executeSql(sql2, new String[]{eventId});
	}
	/**
	 * ͨ�õ�sqlִ��,����Ϊinsert,update,delete,���ô洢����
	 * @param sqlTemplate,��ʽΪ:insert into XXX (id, name) values (?,?);update XXX set name=? where id=?
	 * @param values,������?�����ֵ
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
