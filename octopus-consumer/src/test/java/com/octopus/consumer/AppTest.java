package com.octopus.consumer;

import org.dom4j.DocumentException;

import com.gdrcu.common.XpathMessageObject;
import com.gdrcu.exception.OctBaseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws OctBaseException 
     */
    public void testApp() throws OctBaseException
    {
       
    	try {
    		
			XpathMessageObject obj = (XpathMessageObject) new XpathMessageObject().with("<service xmlns=\"http://www.w3cschool.com.cn\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3cschool.com.cn\"><s atr=\"atr\">a</s></service>");
			
			System.out.println(obj.getValue("/service/s[@atr='atr']"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
