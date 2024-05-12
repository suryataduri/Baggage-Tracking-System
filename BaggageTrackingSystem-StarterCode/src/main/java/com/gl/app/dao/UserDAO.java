package com.gl.app.dao;

import java.sql.SQLException;


import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;

public interface UserDAO {
	 public void registerNewUser(User user) throws Exception;
	 public void checkInBaggage(Baggage baggage) throws Exception;
	 public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException;
	
}
