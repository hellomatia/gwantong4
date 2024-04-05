package com.ssafy.trip.attraction.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ssafy.trip.attraction.model.dto.AttractionDto;
import com.ssafy.trip.attraction.model.service.AttractionService;
import com.ssafy.trip.attraction.model.service.AttractionServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AttractionService attractionService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		attractionService = AttractionServiceImpl.getAttractionService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";

		if("map".equals(action)) {
			PrintWriter out = response.getWriter();
			out.print(listMap(request, response));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String listMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String areaCode = request.getParameter("areaCode");
		String contentTypeId = request.getParameter("contentTypeId");

		try {
			String result = attractionService.listMap(areaCode, contentTypeId);
			return result;
		} catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
