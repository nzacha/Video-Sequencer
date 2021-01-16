package com.z1software;

import com.z1software.binaryfile.BinaryFile;
import com.z1software.binaryfile.video.mp4File;

public class Main {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        BinaryFile bf = new mp4File("samples/sample_iPod.m4v");
    }
}
