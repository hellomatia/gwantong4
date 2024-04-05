package com.ssafy.trip.attraction.model.service;

import java.util.List;

import com.ssafy.trip.attraction.model.dao.AttractionDao;
import com.ssafy.trip.attraction.model.dao.AttractionDaoImpl;
import com.ssafy.trip.attraction.model.dto.AttractionDto;

public class AttractionServiceImpl implements AttractionService{
	private static AttractionService attractionService = new AttractionServiceImpl();
	private static AttractionDao attractionDao;
	
	public static AttractionService getAttractionService() {
		return attractionService;
	}
	
	private AttractionServiceImpl() {
		attractionDao = AttractionDaoImpl.getAttractionDao();
	}

	@Override
	public String listMap(String areaCode, String contentTypeId) throws Exception {
		return attractionDao.listMap(areaCode, contentTypeId);
	}


}
