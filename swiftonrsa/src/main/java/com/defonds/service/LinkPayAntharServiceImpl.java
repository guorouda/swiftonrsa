/**
 * 
 */
package com.defonds.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.defonds.dao.LinkPayAntharDao;
import com.defonds.entity.LinkPayAnthar;
import com.defonds.util.tool.ReadProperties;
import com.defonds.util.tool.RsaEncryptUtil;

/**
 *
 */
public class LinkPayAntharServiceImpl implements LinkPayAntharService {
	private static final Log logger = LogFactory.getLog(LinkPayAntharServiceImpl.class);
	private LinkPayAntharDao linkPayAntharDao;
	
	public void setLinkPayAntharDao(LinkPayAntharDao linkPayAntharDao) {
		this.linkPayAntharDao = linkPayAntharDao;
	}

	/* (non-Javadoc)
	 * @see com.cardinfolink.service.LinkPayAntharService#dealWithYearData(java.lang.String)
	 */
	public void dealWithYearData() {
		String year = ReadProperties.getValue("year");
		long encryptedNum = 0;
		long startTime = System.currentTimeMillis();
		
		// Jan
		List<LinkPayAnthar> linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-01-00", year + "-01-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Jan is finashed.encryptedNum is:" + encryptedNum);
		
		// Feb
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-02-00", year + "-02-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Feb is finashed.encryptedNum is:" + encryptedNum);
		
		// Mar
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-03-00", year + "-03-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Mar is finashed.encryptedNum is:" + encryptedNum);
		
		// April
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-04-00", year + "-04-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " April is finashed.encryptedNum is:" + encryptedNum);
		
		// May
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-05-00", year + "-05-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " May is finashed.encryptedNum is:" + encryptedNum);
		
		// June
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-06-00", year + "-06-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " June is finashed.encryptedNum is:" + encryptedNum);
		
		// July
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-07-00", year + "-07-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " July is finashed.encryptedNum is:" + encryptedNum);
		
		// Aug
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-08-00", year + "-08-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Aug is finashed.encryptedNum is:" + encryptedNum);
		
		// Sep
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-09-00", year + "-09-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Sep is finashed.encryptedNum is:" + encryptedNum);
		
		// Oct
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-10-00", year + "-10-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Oct is finashed.encryptedNum is:" + encryptedNum);
		
		// Nov
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-11-00", year + "-11-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Nov is finashed.encryptedNum is:" + encryptedNum);
		
		// Dec
		linkPayAntharList = this.linkPayAntharDao.getLinkPayAntharList(year + "-12-00", year + "-12-32");
		if (linkPayAntharList != null && linkPayAntharList.size() > 0) {
			encryptedNum += this.dealWithList(linkPayAntharList);
		}
		logger.debug(year + " Dec is finashed.encryptedNum is:" + encryptedNum);
		
		long endTime = System.currentTimeMillis();
		logger.info(year + ":converted linkpayanthar size is:" + encryptedNum + ";spent time is:" + (endTime - startTime) + "mm");
	}
	
	private int dealWithList(List<LinkPayAnthar> linkPayAntharList) {
		int succeedNum = 0;
		Iterator<LinkPayAnthar> iterator = linkPayAntharList.iterator();
		while (iterator.hasNext()) {
			LinkPayAnthar linkPayAnthar = iterator.next();
			if (!linkPayAnthar.getToAtmNo().equals("") && linkPayAnthar.getToAtmNo().length() > 10) {
				RsaEncryptUtil.rsaEncryptForAtmNo(linkPayAnthar);
				this.linkPayAntharDao.updateLinkPayAnthar(linkPayAnthar);
				succeedNum ++;
			}
		}
		return succeedNum;
	}
}
