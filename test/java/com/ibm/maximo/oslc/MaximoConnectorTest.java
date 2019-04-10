package com.ibm.maximo.oslc;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
 
public class MaximoConnectorTest {
 
	private MaximoConnector mc;

	@Before
	public void setUp() throws Exception {
		 mc = new MaximoConnector(new Options().user("maxadmin").password("maxadmin").lean(true).auth("maxauth").host("localhost").port(9080));
		mc.connect();
	}

	@After
	public void tearDown() throws Exception {
		mc.disconnect();
	}

	@Test
	public void testConnect() throws Exception {
		System.out.println("test-----------");
		 
		/**
		 * 算出筆數
		 * */
		ResourceSet rs = mc.resourceSet("mxwodetail").stablePaging(true).stablePaging(true).pageSize(10).fetch();
		
		showData(rs);
		
		/**
		 * 算出筆數，以及將此Table的所有欄位之值找出
		 * */
		rs = mc.resourceSet("mxwodetail").select("*").stablePaging(true).stablePaging(true).pageSize(10).fetch();
		
		showData(rs);
	}
	protected void showData( final ResourceSet rs) throws Exception{
		for(int i=0;i<rs.count();i++){
			Resource re = rs.member(i);
			 
			System.out.println(re.toString());
		}
	}

}
