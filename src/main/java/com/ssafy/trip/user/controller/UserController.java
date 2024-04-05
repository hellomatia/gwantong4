package com.ssafy.trip.user.controller;

import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		String path = "";
		try {
			System.out.println(action);
			switch (action) {
				case "login":
					path = login(request, response);
					redirect(request, response, path);
					break;
				case "logout":
					System.out.println("logout");
					path = logout(request, response);
					redirect(request, response, path);
					break;
				case "mvjoin":
					path = "/user/join.jsp";
					redirect(request, response, path);
					break;
				case "join":
					path = join(request, response);
					redirect(request, response, path);
					break;
				case "mvfindpw":
					path = "/user/findPw.jsp";
					redirect(request, response, path);
					break;
				case "findpw":
					path = findPw(request, response);
					forward(request, response, path);
					break;
				case "mvmodify":
					path = "/user/modify.jsp";
					forward(request, response, path);
					break;
				case "modify":
					path = modify(request, response);
					redirect(request, response, path);
					break;
				case "delete":
					path = delete(request, response);
					redirect(request, response, path);
					break;
				default:
					// 알 수 없는 액션에 대한 처리
					path = "/error.jsp";
					forward(request, response, path);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace(); // 서버 로그에 예외를 기록
			request.setAttribute("errorMessage", "처리 중 오류가 발생했습니다.");
			path = "/error.jsp"; // 오류 페이지로 이동
			forward(request, response, path);
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

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		StringBuilder hexString = new StringBuilder();
		for (byte b : digest) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	private String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		// 입력된 비밀번호를 해시합니다.
		String hashedPassword = hashPassword(userPwd);
		System.out.println(userId + " " + hashedPassword); // 로그인 시도 정보를 로그로 남깁니다.
		UserService userService = UserServiceImpl.getInstance();
		// userService.login 메서드가 해시된 비밀번호를 사용하도록 수정해야 합니다.
		UserDto user = userService.login(userId, hashedPassword);
		if (user != null) {
			System.out.println(user);
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", user);
			String saveid = request.getParameter("saveid");
			if (saveid != null) {
				Cookie cookie = new Cookie("tripid", userId);
				cookie.setMaxAge(60 * 60 * 24 * 7); // 1주일
				response.addCookie(cookie);
			} else {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("tripid")) {
							cookie.setMaxAge(0); // 쿠키 삭제
							response.addCookie(cookie);
						}
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
			throws ServletException, IOException, NoSuchAlgorithmException {
		UserDto userDto = new UserDto();
		System.out.println(request.getParameter("userid"));
		userDto.setUserId(request.getParameter("userid"));
		String hashedPassword = hashPassword(request.getParameter("userpwd"));
		userDto.setUserPwd(hashedPassword);
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
