package com.z1software.binaryfile;

import java.nio.ByteBuffer;

public class BinaryFile {
    protected void printByteArray(byte array[]){
        for(int i=0; i<array.length; i++){
            System.out.print((char) array[i]);
        }
    }

    protected int byteArrayToInt(byte array[]){
        ByteBuffer wrapper = ByteBuffer.wrap(array);
        return wrapper.getInt();
    }

    protected byte[] intToByteArray(int value){
        ByteBuffer wrapper = ByteBuffer.allocate(4);
        wrapper.putInt(value);
        return wrapper.array();
    }
}
