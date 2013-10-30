package com.myclass;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 28.10.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class IOTime {
    String path = "/home/nikita/testfile";
    int size = 16;
    int bigsize = 100000000;
    int testTime = 1000;
    public void createFile() throws IOException {
        FileOutputStream nout = new FileOutputStream(path);
        BufferedOutputStream out = new BufferedOutputStream(nout);
        byte [] array = new byte[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = (byte)Math.random();
        }
        for(int i = 0; i < bigsize/size; i++ ) {
            out.write(array);
        }

    }

    public long inputNotBuffered() throws IOException {
        FileInputStream in = new FileInputStream(path);
        byte [] testArray = new byte[size];
        long start = System.currentTimeMillis();
        while(in.available()>0) {
            in.read(testArray);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long inputBuffered() throws IOException {
        FileInputStream sin = new FileInputStream(path);
        BufferedInputStream in = new BufferedInputStream(sin);
        byte [] testArray = new byte[size];
        long start = System.currentTimeMillis();
        while(in.available()>0) {
            in.read(testArray);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long outputNotBuffered() throws IOException {
        FileOutputStream out = new FileOutputStream("/home/nikita/testOutputNotBuffered");
        byte [] randomArray = new byte[size];
        long start, end;
        for(int i = 0 ; i < size; i++) {
            randomArray[i] =(byte) (Math.random() / size);
        }
        start = System.currentTimeMillis();
        for(int i = 0; i < bigsize / size; i++) {
            out.write(randomArray);
        }
        end = System.currentTimeMillis();
        return end - start;
    }

    public long outputBuffered() throws IOException {
        FileOutputStream sout = new FileOutputStream("/home/nikita/testOutputBuffered");
        BufferedOutputStream out = new BufferedOutputStream(sout);
        byte [] randomArray = new byte[size];
        long start, end;
        for(int i = 0 ; i < size; i++) {
            randomArray[i] =(byte) (Math.random() / size);
        }
        start = System.currentTimeMillis();
        for(int i = 0; i < bigsize / size; i++) {
            out.write(randomArray);
        }
        end = System.currentTimeMillis();
        return end - start;
    }

    public long defaultObjectStream() throws Exception {
        long start,end;
        start = System.currentTimeMillis();
        for(int i = 0; i < testTime; i++) {
            defaultSerialize();
            defaultDeserialize();
        }
        end = System.currentTimeMillis();
        return end - start;
    }

    private void defaultDeserialize() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("/home/nikita/defaultOutFile");
        ObjectInputStream in = new ObjectInputStream(fin);
        TestClass testObject2 = (TestClass)in.readObject();
        in.close();
    }

    private void defaultSerialize() throws IOException {
        FileOutputStream fout = new FileOutputStream("/home/nikita/defaultOutFile");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        TestClass testObject = new TestClass(1,1,"s");
        out.writeObject(testObject);
        out.close();
    }

    public long myObjectStream() throws Exception {
        long start,end;
        start = System.currentTimeMillis();
        for(int i = 0; i < testTime; i++) {
            mySerialize();
            myDeserialize();
        }
        end = System.currentTimeMillis();
        return end - start;
    }

    private static void mySerialize() throws Exception {
        FileOutputStream fout = new FileOutputStream("/home/nikita/myOutFile");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        TestClass testObject = new TestClass(1,1,"s");
        out.writeInt(testObject.i);
        out.writeLong(testObject.l);
        out.writeUTF(testObject.s);
        out.close();
    }
    private static void myDeserialize() throws Exception {
        FileInputStream fin = new FileInputStream("/home/nikita/myOutFile");
        ObjectInputStream in = new ObjectInputStream(fin);
        TestClass testObject = new TestClass();
        testObject.i = in.readInt();
        testObject.l = in.readLong();
        testObject.s = in.readUTF();
        in.close();
    }
}

class TestClass implements Serializable{
    int i;
    long l;
    String s;

    TestClass() {
        this.i = 0;
        this.l = 0l;
        this.s = "";
    }

    TestClass(int i, long l, String s) {
        this.i = i;
        this.l = l;
        this.s = s;
    }
}
