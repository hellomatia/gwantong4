package com.ssafy.trip.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.board.model.dto.BoardDto;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao{
	private static BoardDao boardDao = new BoardDaoImpl();
	
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	
	private BoardDaoImpl() {}

	@Override
	public List<BoardDto> list() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> list = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBoard_id(Integer.parseInt(rs.getString("board_id")));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setUser_id(rs.getString("user_id"));
				boardDto.setRegister_time(rs.getString("register_time"));
				list.add(boardDto);
			}
			
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public int write(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board (title, contents, user_id) \n");
			sql.append("value (?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setString(3, boardDto.getUser_id());

			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public BoardDto view(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto boardDto = null;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board \n");
			sql.append("where board_id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto = new BoardDto();
				boardDto.setBoard_id(id);
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setUser_id(rs.getString("user_id"));
				boardDto.setRegister_time(rs.getString("register_time"));
			}
			
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return boardDto;
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where board_id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id+"");

			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modify(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update board set title=? ,contents=? \n");
			sql.append(" where board_id=? ");
		
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setInt(3, boardDto.getBoard_id());


			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}
}
