package com.ssafy.trip.user.controller;

import java.io.IOException;

import com.ssafy.trip.user.model.*;
import com.ssafy.trip.user.model.dto.UserDto;
import com.ssafy.trip.user.model.service.UserService;
import com.ssafy.trip.user.model.service.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("dl");
		String path = "";
		System.out.println(action);
		if (action.equals("login")) {
			path = login(request, response);
			redirect(request, response, path);
		} else if (action.equals("logout")) {
			System.out.println("logout");
			path = logout(request, response);
			redirect(request, response, path);
		} else if (action.equals("mvjoin")) {
			path = "/user/join.jsp";
			redirect(request, response, path);
		} else if (action.equals("join")) {
			path = join(request, response);
			redirect(request, response, path);
		} else if (action.equals("mvfindpw")) {
			path = "/user/findPw.jsp";
			redirect(request, response, path);
		} else if (action.equals("findpw")) {
			path = findPw(request, response);
			forward(request, response, path);
		} else if (action.equals("mvmodify")) {
			path = "/user/modify.jsp";
			forward(request, response, path);
		} else if (action.equals("modify")) {
			path = modify(request, response);
			redirect(request, response, path);
		} else if (action.equals("delete")) {
			path = delete(request, response);
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		System.out.println(userId + " " + userPwd);
		UserService userService = UserServiceImpl.getInstance();
		UserDto user = userService.login(userId, userPwd);
		if (user != null) {
			System.out.println(user);
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", user);
			String saveid = request.getParameter("saveid");
			if (saveid != null) {
				Cookie cookie = new Cookie("tripid", userId);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(cookie);
			} else {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals("tripid")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			return "/index.jsp";
		} else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/user/login.jsp";
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

	private String join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDto userDto = new UserDto();
		System.out.println(request.getParameter("userid"));
		userDto.setUserId(request.getParameter("userid"));
		userDto.setUserPwd(request.getParameter("userpwd"));
		userDto.setUserName(request.getParameter("username"));
		userDto.setUserEmail(request.getParameter("useremail"));
		userDto.setUserAddr(request.getParameter("useraddr"));
		userDto.setUserGender(request.getParameter("usergender"));
		try {
			UserService userService = UserServiceImpl.getInstance();
			userService.insertUser(userDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/index.jsp";
	}

	private String findPw(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		UserService userService = UserServiceImpl.getInstance();
		String userpw = userService.findPw(userId);
		if (userpw != null) {
			request.setAttribute("userpw", userpw);
			return "/user/noticePw.jsp";
		} else {
			request.setAttribute("msg", "아이디 또는 이메일이 일치하지 않습니다.");
			return "/user/findPw.jsp";
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		UserDto userDto = new UserDto();
		userDto.setUserId(request.getParameter("userid"));
		userDto.setUserPwd(request.getParameter("userpwd"));
		userDto.setUserName(request.getParameter("username"));
		userDto.setUserEmail(request.getParameter("useremail"));
		userDto.setUserAddr(request.getParameter("useraddr"));
		userDto.setUserGender(request.getParameter("usergender"));
		UserService userService = UserServiceImpl.getInstance();
		userService.updateUser(userDto);
		HttpSession session = request.getSession();
		session.setAttribute("userinfo", userDto);
		return "/user/myPage.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		UserDto userDto = (UserDto) request.getSession().getAttribute("userinfo");
		UserService userService = UserServiceImpl.getInstance();
		userService.deleteUser(userDto.getUserId());
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}
}
