package com.ssafy.trip.board.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.ssafy.trip.board.model.dto.BoardDto;
import com.ssafy.trip.board.model.service.BoardService;
import com.ssafy.trip.board.model.service.BoardServiceImpl;
import com.ssafy.trip.user.model.dto.UserDto;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService;
    
    public BoardController() {
        super();
        boardService = BoardServiceImpl.getBoardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";

		if("list".equals(action)) {
			path = list(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if("mvWrite".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/write.jsp");
			dispatcher.forward(request, response);
		} else if("write".equals(action)) {
			path = write(request, response);

			if(path == "") {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/board/error.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + path);
			}
		} else if("view".equals(action)) {
			path = view(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if("delete".equals(action)) {
			delete(request, response);
			response.sendRedirect(request.getContextPath() + "/board?action=list");
		} else if("mvModify".equals(action)) {
			path = mvModify(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/modify.jsp");
			dispatcher.forward(request, response);
		} else if("modify".equals(action)) {
			path = modify(request, response);
			response.sendRedirect(request.getContextPath() + path);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<BoardDto> list = boardService.list();
			request.setAttribute("board", list);
			return "/board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userinfo");
		
		BoardDto boardDto = new BoardDto();
		boardDto.setTitle(request.getParameter("title"));
		boardDto.setContents(request.getParameter("contents"));
		boardDto.setUser_id(userDto.getUserId());
		
		try {
			int result = boardService.write(boardDto);
			System.out.println(result);
			if (result == 1){
				return "/board?action=list";
			} else {
				return "";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String view (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("id"));
		
		try {
			BoardDto boardDto = boardService.view(board_id);
			request.setAttribute("board", boardDto);
			return "/board/view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("id"));
		
		try {
			boardService.delete(board_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String mvModify (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("id"));
		
		try {
			BoardDto boardDto = boardService.view(board_id);
			request.setAttribute("board", boardDto);
			return "/board/modify.jsp";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String modify (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("id"));
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_id(board_id);
		boardDto.setTitle(request.getParameter("title"));
		boardDto.setContents(request.getParameter("contents"));
		
		try {
			boardService.modify(boardDto);
			return "/board?action=view&id="+board_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/board?action=list";
	}
}
