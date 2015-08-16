package com.hand.service;

import java.sql.Connection;
import java.sql.ResultSet;
import com.hand.dao.UserDao;
import com.hand.dao.impl.UserDaoImpl;
import com.hand.entity.User;
import com.hand.util.ConnectionFactory;

public class CheckUserService {
	private UserDao userDao = new UserDaoImpl();
	
	
	public boolean check(User user){
		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			
			ResultSet resultSet = userDao.get(conn, user);
			
			while(resultSet.next()){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}finally{
			
			try {
				conn.close();
			} catch (Exception e3) {
				
				e3.printStackTrace();
			}
		}
		return false;
		
	}
}
