package com.ipartek.model;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class ConnectionManagerTest {

	@Test
	public void testDriver() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			assertTrue(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("No existe driver para mysql, � Tienes el .JAR ?");
		}
	}

	
	@Test
	public void testConnection() {
		
		/*
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
		*/
		
		
	}

	


}
