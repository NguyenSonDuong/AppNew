package com.example.demod.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class ControlMRCong {

    public static final String HOST = "https://mrcong.com/";

    @RequestMapping(value = "/GetImageMrCong",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object GetImage(@RequestParam("id") String id){
        try {
            Document doc = Jsoup.connect(HOST+id).get();
            Elements images = doc.select("");
            for(Element ele : images){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
