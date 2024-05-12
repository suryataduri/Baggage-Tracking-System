package com.gl.app;

import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.dao.impl.BaggageDAOImpl;
import com.gl.app.dao.impl.UserDAOImpl;
import org.junit.jupiter.api.Test;

import com.gl.app.entity.Baggage;
import com.gl.app.service.BaggageService;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

public class BaggageServiceTest {
   //write the code for the intialization of the object
    @Mock
 BaggageDAOImpl baggageDAO = mock(BaggageDAOImpl.class);

    @Mock
    UserDAOImpl userDAO = mock(UserDAOImpl.class);
    @InjectMocks
    BaggageServiceImpl baggageService;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        baggageService = new BaggageServiceImpl(baggageDAO);
        userService = new UserServiceImpl(userDAO);
    }

    @Test
    public void testGetBaggageStatus() throws SQLException, ClassNotFoundException {
        //Write your code here
        String expected = "Checked In";
        when(baggageDAO.getBaggageStatus("C011")).thenReturn(expected);
        String actual = baggageDAO.getBaggageStatus("C011");
        assertEquals(expected,actual);
    }

    @Test
    public void testBaggageInfo() throws SQLException, ClassNotFoundException {
        Baggage baggage = new Baggage("C010","Terminal 1","Claimed","005");
        when(userDAO.getBaggageInfo("C010")).thenReturn(baggage);
        Baggage baggage1 = userDAO.getBaggageInfo("C010");
        assertEquals(baggage,baggage1);
    }
}