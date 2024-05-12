package com.gl.app.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.BaggageService;
import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;

public class BaggageServiceImpl implements BaggageService{
	
	BaggageDAO baggageDAO = new BaggageDAOImpl();

	public BaggageServiceImpl(){}
	public BaggageServiceImpl(BaggageDAOImpl baggageDAO) {
	}


	@Override
	public String getBaggageStatus(String claimTagId) throws Exception {
		// TODO Auto-generated method stub
		//write the code to get baggage status
		String status = baggageDAO.getBaggageStatus(claimTagId);
		if(status==null){
			throw new BaggageNotFoundException("Cannot retrieve the Baggage status");
		}

		return status;
	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to update baggage status
        try {
            baggageDAO.updateBaggageStatus(claimTagId,status);
        } catch (Exception e) {
            throw new BaggageNotFoundException("Unable to update baggage status");
        }

    }

	@Override
	public String getBaggageLocation(String claimTagId) throws Exception {
		// TODO Auto-generated method stub
		//write the code to get baggage location
		String b_location = baggageDAO.getBaggageLocation(claimTagId);
		if(b_location.isEmpty()){
			throw new BaggageNotFoundException("Baggage location not found");
		}
		return b_location;
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to update baggage location
		try {
			baggageDAO.updateBaggageLocation(claimTagId,location);
		} catch (Exception e) {
			throw new BaggageNotFoundException("Unable to update baggage location");
		}
	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to claim baggage
        try {
            baggageDAO.claimBaggage(claimTagId);
        } catch (Exception e) {
            throw new BaggageNotFoundException("Unable to claim baggage");
        }
    }

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws Exception {
		// TODO Auto-generated method stub
		List<Baggage> baggages = new ArrayList<>();
		baggages.addAll(baggageDAO.getAllCheckedInBaggage());
		if(baggages.isEmpty()){
			throw new Exception("Cannot retrieve the list of Checked in baggages");
		}
		return baggages;
	}
	

}
