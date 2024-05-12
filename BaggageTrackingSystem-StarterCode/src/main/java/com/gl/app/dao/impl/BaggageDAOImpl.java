package com.gl.app.dao.impl;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaggageDAOImpl implements BaggageDAO {
    private Connection connection;
    private BaggageUtil baggageUtils;
    

    public BaggageDAOImpl() {
       
    }
    @Override
    public String getBaggageStatus(String claimTagId) throws SQLException, ClassNotFoundException {
    //write the code to get baggage status
        Connection con = BaggageUtil.getConnection();
        String query = "select status from baggage where claimid=?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,claimTagId);
        boolean result = statement.execute();
        String status = null;
        if(result){
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                status = rs.getString("status");
            }
        }
        return status;
    }
    @Override
    public void updateBaggageStatus(String claimTagId, String status) throws SQLException, ClassNotFoundException {
    	//write the code to update baggage status
        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE baggage SET status = ? WHERE claimid  = ?");
        preparedStatement.setString(1, status);
        preparedStatement.setString(2, claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new BaggageNotFoundException("Unable to update baggage status with claimId " + claimTagId);
        }else{
            System.out.println("Updated Baggage status");
        }
    }
    @Override
    public String getBaggageLocation(String claimTagId) throws SQLException, ClassNotFoundException {
        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT b_location FROM baggage where claimid =?");
        preparedStatement.setString(1,claimTagId);
//        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = preparedStatement.execute();
//      List<Baggage> baggages = new ArrayList<>();
//        while (resultSet.next()) {
//            Baggage baggage = new Baggage();
//            baggage.setClaimId(resultSet.getString("claimid"));
//            baggage.setLocation(resultSet.getString("b_location"));
//            baggages.add(baggage);
//        }
        String b_location = null;
        if(result) {
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                b_location = resultSet.getString(1);
            }
        }
		return b_location;
     }
    @Override
    public void updateBaggageLocation(String claimTagId, String location) throws SQLException, ClassNotFoundException {
        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE baggage SET b_location = ? WHERE claimid  = ?");
        preparedStatement.setString(1, location);
        preparedStatement.setString(2, claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new BaggageNotFoundException("Baggage with claimId " + claimTagId + " not found.");
        }else{
            System.out.println("Updated Baggage Location");
        }
    }
    @Override
    public void claimBaggage(String claimTagId) throws SQLException, ClassNotFoundException {
		//write the code to claim baggage
        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from baggage WHERE claimid  = ?");
        preparedStatement.setString(1, claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new BaggageNotFoundException("Unable to Claim baggage with claimId " + claimTagId);
        }else{
            System.out.println("Claimed Baggage");
        }
	}
    @Override
    public List<Baggage> getAllCheckedInBaggage() throws SQLException, ClassNotFoundException {
    	//write the code to get all checked-in baggage
        Connection con = BaggageUtil.getConnection();
        List<Baggage> baggageList = new ArrayList<>();
        String query = "select * from baggage where status ='Checked In'";
        PreparedStatement statement = con.prepareStatement(query);
        boolean result = statement.execute();
        if(result){
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                String claimid = rs.getString(1);
                String b_location = rs.getString(2);
                String status = rs.getString(3);
                String userid = rs.getString(4);
                baggageList.add(new Baggage(claimid,b_location,status,userid));
            }
        }

            return baggageList;
    }
}

