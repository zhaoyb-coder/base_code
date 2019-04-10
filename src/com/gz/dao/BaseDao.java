package com.gz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao extends SqlSessionDaoSupport {
	public abstract String getNameSpace();
	
	
	/**
	 * Autowired 必须要有
	 */
	@Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
    	
        super.setSqlSessionFactory(sqlSessionFactory);
    }
 
	/**
	 * ƴ�ӷ���,������ռ�ͷ������
	 * @param method
	 * @return
	 */
	public String getMethod(String method){
		return getNameSpace() + "." + method;
	}
	
	/**
	 * �������
	 * @param method mybatis�в�����ݵķ���
	 * @param param ���ݵĶ���
	 */
	public void insert(String method, Object param){
		getSqlSession().insert(getMethod(method), param);
	}
	/**
	 * �������
	 * @param method mybatis�в�����ݵķ���
	 * @param param ���ݵĶ���
	 */
	public void update(String method, Object param){
		getSqlSession().update(getMethod(method), param);
	}
	
	/**
	 * �������ɾ�����
	 * @param method
	 * @param param
	 */
	public void delete(String method, Object param){
		getSqlSession().delete(getMethod(method), param);
	}
	/**
	 * ���������ѯ���б����
	 * @param method mybatis�в�ѯ�ķ���
	 * @param param ���ݵĶ���
	 * @return
	 */
	public <E> List<E> selectList(String method, Object param){
		return getSqlSession().selectList(getMethod(method), param);
	}
	/**
	 * ��ݲ����ѯ��Ψһ����ֵ
	 * @param method mybatis�в�ѯ�ķ���
	 * @param param ���ݵĶ���
	 * @return
	 */
	public <E> E selectOne(String method, Object param){
		List<E> list = getSqlSession().selectList(getMethod(method), param);
		if(list != null && list.size() > 0)return list.get(0);
		return null;
	}
	
	/**
	 * ��ѯ����¼��
	 * @param method mybatis�в�ѯ�ķ���
	 * @param param ���ݵĶ���
	 * @return
	 */
	public int queryCount(String method, Object param){
		List<Integer> list = getSqlSession().selectList(getMethod(method), param);
		return (list == null || list.get(0) == null)?0:list.get(0);
	}
	
	/**
	 * list�б?������map��ʽ�����
	 * @param method mybatis�в�ѯ�ķ���
	 * @param param ���ݵĶ���
	 * @return ����Map��ʽ�����
	 */
	public List<Map<String,Object>> queryListMap(String method, Object param) {
		return getSqlSession().selectList(getMethod(method), param);
	}
	
	/**
	 * ���ص�����¼����¼ΪMap��ʽ
	 * @param method mybatis�в�ѯ�ķ���
	 * @param param ���ݵĶ���
	 * @return ����Map��ʽ�����
	 */
	public Map<String, Object> queryOneMap(String method, Object param) {
		List<Map<String,Object>> list = getSqlSession().selectList(getMethod(method), param);
		if(list != null && list.size() > 0)return list.get(0);
		return new HashMap<String, Object>();
	}
	
	
}
