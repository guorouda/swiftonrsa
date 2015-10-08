package com.defonds.dao;

import java.util.List;

import com.defonds.entity.LinkPayAnthar;

public interface LinkPayAntharDao {
	public List<LinkPayAnthar> getLinkPayAntharList(String startDate, String endDate);
	public void updateLinkPayAnthar(LinkPayAnthar linkPayAnthar);
}
