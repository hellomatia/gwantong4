package com.ssafy.trip.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.ssafy.trip.attraction.model.dto.AttractionDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
	private static AttractionDao attractionDao = new AttractionDaoImpl();
	
	public static AttractionDao getAttractionDao() {
		return attractionDao;
	}
	
	private AttractionDaoImpl() {
	}

	@Override
	public String listMap(String areaCode, String contentTypeId) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttractionDto> list = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from attraction_info \n");
			sql.append("where sido_code = ? AND content_type_id = ? \n");
			sql.append("limit 50");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(areaCode));
			pstmt.setInt(2, Integer.parseInt(contentTypeId));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AttractionDto dto = new AttractionDto();
				dto.setContent_id(rs.getInt("content_id"));
				dto.setContent_type_id(rs.getInt("content_type_id"));
				dto.setTitle(rs.getString("title"));
                dto.setAddr1(rs.getString("addr1"));
                dto.setAddr2(rs.getString("addr2"));
                dto.setZipcode(rs.getString("zipcode"));
                dto.setFirst_image(rs.getString("first_image"));
                dto.setFirst_image2(rs.getString("first_image2"));
                dto.setReadcount(rs.getInt("readcount"));
                dto.setSido_code(rs.getInt("sido_code"));
                dto.setGugun_code(rs.getInt("gugun_code"));
                dto.setLatitude(rs.getString("latitude"));
                dto.setLongitude(rs.getString("longitude"));
                dto.setMlevel(rs.getString("mlevel"));
                list.add(dto);
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		// JSON 형식으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		    if (!list.isEmpty()) {
		        // 리스트에 내용이 있는 경우에만 JSON 데이터 생성
		        StringBuilder jsonBuilder = new StringBuilder("{\"data\": {\"response\": {\"header\": {\"resultCode\": \"0000\"}, \"body\": {\"items\": {\"item\": [");
		        for (int i = 0; i < list.size(); i++) {
		            String json = objectMapper.writeValueAsString(list.get(i));
		            jsonBuilder.append(json);
		            if (i < list.size() - 1) {
		                jsonBuilder.append(",");
		            }
		        }
		        jsonBuilder.append("]}}}}}");
		        return jsonBuilder.toString();
		    } else {
		        // 리스트가 비어있는 경우에는 결과 코드와 메시지를 반환
		        return "{\"data\": {\"response\": {\"header\": {\"resultCode\": \"5001\", \"resultMsg\": \"No data found\"}}}}";
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    // 에러가 발생한 경우에는 결과 코드와 메시지를 반환
		    return "{\"data\": {\"response\": {\"header\": {\"resultCode\": \"5000\", \"resultMsg\": \"Error occurred\"}}}}";
		}

	}
}
