package com.example.demod.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/active")
public class ActiveApplication {
    @RequestMapping(value = "/active",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object GetActive(@RequestParam("deviceID")String  deviceID ){
        try {
            ArrayList<String> data = SaveFile.FileRead(SaveFile.PATH);
            for (String item:data
                 ) {
                if(deviceID.equals(item)){
                    return "ok";
                }
            }
            return "no";
        }catch (Exception ex){
            return "no";
        }
    }
    @RequestMapping(value = "/SignActive",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object SignActive(@RequestParam("deviceID")String  deviceID ){
        try{
            SaveFile.FileWrite(SaveFile.PATH,deviceID+"\n");
            return "ok";
        }catch (Exception ex){
            return "no";
        }

    }
}
