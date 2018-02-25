package com.octopus.consumer;

import org.dom4j.DocumentException;

import com.xbeer.common.XpathMessageObject;
import com.xbeer.exception.OctBaseException;

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
    		
			XpathMessageObject obj = (XpathMessageObject) new XpathMessageObject().with("<?xml version=\"1.0\" encoding=\"UTF-8\"?><service xmlns=\"http://www.w3cschool.com.cn\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3cschool.com.cn\"><SYS_HEAD><SvcCd>30033000016</SvcCd><SvcScn>06</SvcScn><SvcVer>V001</SvcVer><CnsmrId>101010</CnsmrId><CnsmrSeqNo>10101010015528</CnsmrSeqNo><TxnDt>20180112</TxnDt><TxnTm>092858</TxnTm><TmlCd/><ChnlTp>016</ChnlTp><OrigCnsmrId>101010</OrigCnsmrId><OrigCnsmrSeqNo>10101010015528</OrigCnsmrSeqNo><OrigTxnDt>20180112</OrigTxnDt><OrigTxnTm>092858</OrigTxnTm><TxnMd>ONLINE</TxnMd><UsrLng/><MacNode/><MacValue/></SYS_HEAD><APP_HEAD><TlrNo>7001763</TlrNo><BrId>00101</BrId></APP_HEAD><BODY><BizOrgCd>99</BizOrgCd><QryNum/><PageNo>1</PageNo><RecTotNum/><UUID>S10101020180112010101010015528</UUID><PinNode>0499990014</PinNode><PayChan>8100</PayChan><CardNo>6226270100006208</CardNo><InstlmtTp>ZD</InstlmtTp></BODY></service>");
			
			System.out.println(obj.getValue("/service/SYS_HEAD"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
