package com.ssafy.trip.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.attraction.model.dto.AttractionDto;

public interface AttractionDao {
	String listMap(String areaCode, String contentTypeId, double lat, double lng, int sortType) throws SQLException;
}
