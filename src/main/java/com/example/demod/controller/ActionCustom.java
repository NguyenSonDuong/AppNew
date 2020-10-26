package com.example.demod.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping
public class ActionCustom {
    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
    public String GET(String uri) throws Exception {
        URL url = new URL(uri);
        HttpURLConnection con = null;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
    public boolean CheckConnect(String uri) throws Exception {
        URL url = new URL(uri);
        HttpURLConnection con = null;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        con.connect();
        if(con.getResponseCode()==200)
            return true;
        else
            return false;
    }
    public static byte[] GETInput(String uri) throws Exception {
        URL url = new URL(uri);
        HttpURLConnection con = null;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");

        InputStream in = con.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = in.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }
    public static int DrawString(int x, int y, String content, Graphics graphics2D, Font font){

        FontMetrics metrics = graphics2D.getFontMetrics(font);
        graphics2D.setFont(font);
        int hgt = metrics.getHeight();
        int adv = metrics.stringWidth(content);
        double number = (double)800/((double)adv/(double)content.length());
        graphics2D.setColor(Color.BLACK);
        int end = 0;
        for (int i=0;i<10;i++){
            try{
                if(number>content.length())
                {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawString(content,x,(int)y+50);
                    end += number;
                    y+=hgt+10;
                    break;
                }else {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawString(content.substring(end,end+(int)number),x,(int)y+50);
                    end += number;
                    y+=hgt+10;
                }

                System.out.println(end);
            }catch (Exception ex){
                break;
            }
        }
        return y;
    }
    public static int DrawStringRight(int x, int y, String content, Graphics graphics2D, Font font){

        FontMetrics metrics = graphics2D.getFontMetrics(font);
        graphics2D.setFont(font);
        int hgt = metrics.getHeight();
        int adv = metrics.stringWidth(content);
        double number = (double)800/((double)adv/(double)content.length());
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(content,850-adv,(int)y+30);
        int end = 0;
//        for (int i=0;i<10;i++){
//            try{
//                if(number>content.length())
//                {
//                    graphics2D.setColor(Color.BLACK);
//                    graphics2D.drawString(content,x,(int)y+30);
//                    end += number;
//                    y+=hgt+10;
//                    break;
//                }else {
//                    graphics2D.setColor(Color.BLACK);
//                    graphics2D.drawString(content.substring(end,end+(int)number),x,(int)y+100);
//                    end += number;
//                    y+=hgt+10;
//                }
//
//                System.out.println(end);
//            }catch (Exception ex){
//                break;
//            }
//        }
        return y;
    }


    public static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
