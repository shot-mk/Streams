package com.myclass;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 28.10.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
public class IOTimeTest {
    @Ignore
    @Test
    public void testCreateFile() throws Exception {
        IOTime test = new IOTime();
        test.createFile();
    }
    @Ignore
    @Test
    public void testInputTime() throws Exception {
        IOTime test = new IOTime();
        long notBufferedTime = test.inputNotBuffered();
        long bufferedTime = test.inputBuffered();
        float difference = (float)notBufferedTime / (float)bufferedTime;
        System.out.println("Not buffered input time is " + notBufferedTime);
        System.out.println("Buffered input time is " + bufferedTime);
        System.out.println("Difference is " +  difference + " times");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
    }
    @Ignore
    @Test
    public void testOutputTime() throws Exception {
        IOTime test = new IOTime();
        long notBufferedTime,bufferedTime;
        float difference;
        notBufferedTime = test.outputNotBuffered();
        bufferedTime = test.outputBuffered();
        difference = (float)notBufferedTime / (float)bufferedTime;
        System.out.println("Not buffered output time is " + notBufferedTime);
        System.out.println("Buffered output time is " + bufferedTime);
        System.out.println("Difference is " +  difference + " times");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");

    }
    @Test
    public void testSerializeDesirealizeTime() throws Exception {
        IOTime test = new IOTime();
        long mySeri, defaultSeri;
        float difference;
        mySeri = test.myObjectStream();
        defaultSeri = test.defaultObjectStream();
        difference = (float)defaultSeri/(float)mySeri;
        System.out.println("My Seri/Deseri time is " + mySeri);
        System.out.println("Default Seri/Deseri time is " + defaultSeri);
        System.out.println("Difference is " +  difference + " times");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
    }
}
