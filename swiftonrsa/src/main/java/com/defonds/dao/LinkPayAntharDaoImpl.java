/**
 * 
 */
package com.defonds.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.defonds.entity.LinkPayAnthar;

/**
 *
 */
public class LinkPayAntharDaoImpl extends SqlMapClientDaoSupport implements LinkPayAntharDao {

	/* (non-Javadoc)
	 * @see com.cardinfolink.dao.LinkPayAntharDao#getLinkPayAntharList(java.lang.String, java.lang.String)
	 */
	public List<LinkPayAnthar> getLinkPayAntharList(String startDate, String endDate) {
		Map<String,Object> insertMap=new HashMap<String, Object>();
		insertMap.put("startDate", startDate);
		insertMap.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList("linkpayanthar.getLinkPayAntharList", insertMap);
	}

	/*
	 * (non-Javadoc)
	 * @see com.cardinfolink.dao.LinkPayAntharDao#updateLinkPayAnthar(com.cardinfolink.entity.LinkPayAnthar)
	 */
	public void updateLinkPayAnthar(LinkPayAnthar linkPayAnthar) {
		Map<String,Object> insertMap=new HashMap<String, Object>();
		insertMap.put("transid", linkPayAnthar.getTransid());
		insertMap.put("toAtmNo", linkPayAnthar.getToAtmNo());
		insertMap.put("simpleAcctNum", linkPayAnthar.getSimpleAcctNum());
		getSqlMapClientTemplate().update("linkpayanthar.updateLinkPayAntharByTransid", insertMap);
	}

}
