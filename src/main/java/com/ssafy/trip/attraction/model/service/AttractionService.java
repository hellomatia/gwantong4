package com.ssafy.trip.attraction.model.service;

import java.util.List;

import com.ssafy.trip.attraction.model.dto.AttractionDto;

public interface AttractionService {
	String listMap(String areaCode, String contentTypeId) throws Exception;
}
