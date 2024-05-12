package com.gl.app.service.impl;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.UserService;
import com.gl.app.dao.UserDAO;
import com.gl.app.dao.impl.UserDAOImpl;

public class UserServiceImpl implements UserService{
	UserDAO userDAO=new UserDAOImpl();
	public UserServiceImpl() {

	}

	public UserServiceImpl(UserDAOImpl userDAO) {

	}

	@Override
	public void registerNewUser(User user) throws Exception {
		// TODO Auto-generated method stub
		//write the code to register user
		try{
			userDAO.registerNewUser(user);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void checkInBaggage(Baggage baggage) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//write the code to check-in baggage
        try {
            userDAO.checkInBaggage(baggage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

	@Override
	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//write the code to get baggage info
		Baggage baggage = new Baggage();
		baggage = userDAO.getBaggageInfo(claimTagId);
		if(baggage == null){
			throw new BaggageNotFoundException("Unable to get the baggage");
		}
		return baggage;
	}
	

}
