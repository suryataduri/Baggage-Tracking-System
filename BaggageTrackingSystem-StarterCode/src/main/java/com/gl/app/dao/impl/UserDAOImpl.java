package com.gl.app.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gl.app.dao.UserDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.exception.UserNotFoundException;
import com.gl.app.util.BaggageUtil;
public class UserDAOImpl  implements UserDAO{
	  BaggageUtil baggageUtil = new BaggageUtil();
	@Override
	public void registerNewUser(User user) throws Exception {
		//write the code to register user
		Connection con = BaggageUtil.getConnection();
		String query = "insert into users values(?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1,user.userId());
		statement.setString(2,user.firstName());
		statement.setString(3,user.lastName());
		statement.setString(4,user.email());

		int result = statement.executeUpdate();

		if (result==0){
			throw new Exception("Unable to Register user");
		}else{
			System.out.println("User registered successfully");
		}
	}

	public void checkInBaggage(Baggage baggage) throws Exception {
       //write the code to check-in baggage
		Connection con = BaggageUtil.getConnection();
		String query = "insert into baggage values(?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1,baggage.getClaimId());
		statement.setString(2,baggage.getLocation());
		statement.setString(3,baggage.getStatus());
		statement.setString(4,baggage.getUserId());

		int result = statement.executeUpdate();

		if(result==0){
			throw new UserNotFoundException("Unable to Check In");
		}else{
			System.out.println("Baggage Check In successful");
		}
    }
	
	public Baggage getBaggageInfo(String claimTagId) throws SQLException, ClassNotFoundException {
	            //write the code to get baggage info
		Connection con = BaggageUtil.getConnection();
		String query = "select * from baggage where claimid=?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1,claimTagId);
		boolean result = statement.execute();
		Baggage baggage = null;
		if(result){
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				baggage = new Baggage();
				baggage.setClaimId(rs.getString(1));
				baggage.setLocation(rs.getString(2));
				baggage.setStatus(rs.getString(3));
				baggage.setUserId(rs.getString(4));
			}
		}
	    return baggage;
	}

}
