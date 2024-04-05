package com.ssafy.trip.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.user.model.dto.UserDto;
import com.ssafy.util.DBUtil;

public class UserDaoImpl implements UserDao {

    private static final UserDao instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public UserDto login(String userId, String userPwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserDto user = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where user_id = ? and user_password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPwd);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserDto();
                user.setUserId(rs.getString("user_id"));
                user.setUserPwd(rs.getString("user_password"));
                user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserAddr(rs.getString("user_addr"));
                user.setUserGender(rs.getString("user_gender"));
                user.setJoinDate(rs.getString("join_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }

    @Override
    public int insertUser(UserDto user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into user(user_id, user_password, user_name, user_email, user_addr, user_gender) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPwd());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setString(5, user.getUserAddr());
            pstmt.setString(6, user.getUserGender());

            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public int updateUser(UserDto user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update user set user_password = ?, user_name = ?, user_email = ?, user_addr = ?, user_gender = ? where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserPwd());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserEmail());
            pstmt.setString(4, user.getUserAddr());
            pstmt.setString(5, user.getUserGender());
            pstmt.setString(6, user.getUserId());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public int deleteUser(String userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from user where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public UserDto findUser(String userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserDto user = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserDto();
                user.setUserId(rs.getString("user_id"));
                user.setUserPwd(rs.getString("user_password"));
                user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserAddr(rs.getString("user_addr"));
                user.setUserGender(rs.getString("user_gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }

    @Override
    public List<UserDto> findAllUser() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserDto> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDto user = new UserDto();
                user.setUserId(rs.getString("user_id"));
                user.setUserPwd(rs.getString("user_password"));
                user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserAddr(rs.getString("user_addr"));
                user.setUserGender(rs.getString("user_gender"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public String findPw(String userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userpw = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select user_password from user where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                userpw = rs.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return userpw;
    }

}
