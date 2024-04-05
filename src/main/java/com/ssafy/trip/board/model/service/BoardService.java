package com.ssafy.trip.board.model.service;

import java.util.List;

import com.ssafy.trip.board.model.dto.BoardDto;

public interface BoardService {
	List<BoardDto> list() throws Exception;
	int write(BoardDto boardDto) throws Exception;
	BoardDto view(int id) throws Exception;
	void delete(int id) throws Exception;
	void modify(BoardDto boardDto) throws Exception;
}
