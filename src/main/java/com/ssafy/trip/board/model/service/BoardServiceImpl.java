package com.ssafy.trip.board.model.service;

import java.util.List;

import com.ssafy.trip.board.model.dao.BoardDao;
import com.ssafy.trip.board.model.dao.BoardDaoImpl;
import com.ssafy.trip.board.model.dto.BoardDto;

public class BoardServiceImpl implements BoardService{
	private static BoardService boardService = new BoardServiceImpl();
	private BoardDao boardDao;
	
	public static BoardService getBoardService() {
		return boardService;
	}
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getBoardDao();
	}

	@Override
	public List<BoardDto> list() throws Exception {
		return boardDao.list();
	}

	@Override
	public int write(BoardDto boardDto) throws Exception {
		return boardDao.write(boardDto);
	}

	@Override
	public BoardDto view(int id) throws Exception {
		return boardDao.view(id);
	}

	@Override
	public void delete(int id) throws Exception {
		boardDao.delete(id);
	}

	@Override
	public void modify(BoardDto boardDto) throws Exception {
		boardDao.modify(boardDto);
	}	
}
