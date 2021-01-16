package com.z1software.binaryfile.video;

import com.z1software.binaryfile.BinaryFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class mp4File extends BinaryFile {
    public static final byte ATOM_SIZE_B=4;
    private static String atom_types[] = {"ftyp","pdin","moov","moof","mfra","mdat","stts","stsc","stsz","meta","mvhd","trak","udta","iods", "free"};
    public static List<String> ATOM_TYPES = Arrays.asList(atom_types);

    ArrayList<mp4Atom> atoms = new ArrayList<>();

    private class mp4Atom{
        public int size;
        public String type;
        public byte[] chunk;

        public mp4Atom(int size, String type, byte[] chunk){
            this.size = size;
            this.type = type;
            this.chunk = chunk;
        }
    }


    public mp4File(String fileName){
        InputStream istream = null;
        String type;
        byte size_b[], chunk_b[], string_b[];
        int readbytes, size;
        mp4Atom atom;
        try{
            istream = new FileInputStream(fileName);
            do{
                //read atom size
                size_b = new byte[ATOM_SIZE_B];
                readbytes = istream.read(size_b, 0, ATOM_SIZE_B);
                if(readbytes <= 0){
                    break;
                }

                string_b = new byte[ATOM_SIZE_B];
                istream.read(string_b, 0, ATOM_SIZE_B);
                type = new String(string_b);
                if(!ATOM_TYPES.contains(type)) {
                    System.out.println("Type " + type + " not found");
                    throw new InputMismatchException();
                }

                //read chunk
                size = byteArrayToInt(size_b);
                chunk_b = new byte[size - 2*ATOM_SIZE_B];
                istream.read(chunk_b, 0, size - 2*ATOM_SIZE_B);

                //printByteArray(chunk_b);
                //System.out.println();
                atom = new mp4Atom(size, type, chunk_b);
                atoms.add(atom);
            }while(true); //&& !atom.isFinal()
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println(atoms.size());
        System.out.println("================");
        for(int i=0; i< atoms.size(); i++){
            System.out.println(atoms.get(i).size + ": " + atoms.get(i).type);
        }
    }

}