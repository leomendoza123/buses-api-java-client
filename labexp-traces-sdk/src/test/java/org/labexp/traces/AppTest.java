package org.labexp.traces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName){
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public final void testApp(){
        System.out.println("Test start");
        Trace trace;
        try {
            trace = new Trace("auto-maven-test-from-"+ InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            trace = new Trace("auto-maven-test");
        }
        trace.setApiBaseUrl("http://10.173.1.153");
        trace.start();
        Random  rnd = new Random();
        
        assertNotSame(trace.getTraceId(), "");
        System.out.println("TraceId: " + trace.getTraceId());
        MapPoint pathPoint = new MapPoint(rnd.nextInt(), rnd.nextInt());
        assertEquals(true, trace.addPoint(pathPoint));
        System.out.println("Added path point: " + pathPoint);
        Metadata metadata = new Metadata(
                "TestRouteCode",
                "TestRouteName",
                "TestRoutePrice"
                );
        assertEquals(true, trace.setMetadata(metadata));
        System.out.println("Added metadata: " + metadata);
        MapPoint stopPoint = new MapPoint(
                rnd.nextInt(),  rnd.nextInt());
        assertEquals(true, trace.addStop(stopPoint));
        System.out.println("Added stop point: " + stopPoint);
        if ( rnd.nextFloat() >= 0.5) {
            System.out.println("Trace marked as finished: ");
            assertEquals(true, trace.finished());
        }
        else {
            assertEquals(true, trace.discarded());
            System.out.println("Trace marked as discarted: ");
        }
        System.out.println("Test success");

    }
   
    
}
