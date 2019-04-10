package com.gz.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.gz.dao.BaseDao;
import com.gz.dao.common.SysDomainDao;
import com.gz.service.BaseService;
import com.gz.vo.common.SysDomain;


@Service("sysDomainService")
public class SysDomainService extends BaseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysDomainDao sysDomainDao;

	@Override
	public BaseDao getDao() {
		return sysDomainDao;
	}
	/**
	 * 查询域值表中的数据,可以指定排序和过滤条件
	 * @param domain
	 * @param sort
	 * @param filter
	 * @return
	 */
	public List<SysDomain> querySysDomain(SysDomain domain, String sort, String filter) {
		if(StringUtils.isEmpty(sort))sort = "asc";
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("vo", domain);
	    params.put("order", " order by domain_order " + sort);
	    params.put("filter", filter);
		return sysDomainDao.selectList("queryAll", params);
	}
	
	
	/**
	 * 查询域值表中的数据,可以指定排序和过滤条件
	 * @param domain
	 * @param sort
	 * @param filter
	 * @return
	 */
	public List<SysDomain> querySysDomain(String select ,SysDomain domain, String sort, String filter) {
		if(StringUtils.isEmpty(sort))sort = "asc";
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("vo", domain);
	    params.put("order", " order by domain_order " + sort);
	    params.put("filter", filter);
	    params.put("select", select);
		return sysDomainDao.selectList("queryForSelect", params);
	}
	
	
	
	/**
	 * 根据域值表中的domain_name,查询出域值表数据
	 * @param domain_name,如：CYBBJX_SXDM
	 * @return
	 */
	public List<SysDomain> querySysDomain(String domain_name) {
		SysDomain domain = new SysDomain();
		domain.setDomain_name(domain_name);
		return querySysDomain(domain, null, null);
	}
	
	
	/**
	 * 根据域值表中的domain_name,并排序，过滤，查询出域值表数据
	 * @param domain_name,如：CYBBJX_SXDM sort 排序 acs desc ,filter 过滤条件sql
	 * @return
	 */
	public List<SysDomain> querySysDomain(String domain_name,String sort, String filter) {
		SysDomain domain = new SysDomain();
		domain.setDomain_name(domain_name);
		return querySysDomain(domain, sort, filter);
	}
}

