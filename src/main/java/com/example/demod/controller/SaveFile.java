package com.example.demod.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveFile {
    public static String PATH = "src/main/resources/database.txt";
    public static String PATH_USER = "src/main/resources/userinfor.db";
    public static ArrayList<String> FileRead(String path) throws Exception{
        try{
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            ArrayList<String> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null){
                list.add(line);
            }
            bufferedReader.close();
            return list;
        }catch (Exception ex){
            throw ex;
        }
    }
    public static boolean FileWrite(String path,String content) throws Exception{
        try{
            File file = new File(path);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.append(content);
            bufferedWriter.close();
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }
}
