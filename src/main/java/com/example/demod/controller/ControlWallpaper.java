package com.example.demod.controller;


import com.example.demod.model.wallpaper.Image;
import com.example.demod.model.wallpaper.OutData;
import com.example.demod.model.wallpaper.SourceImage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class ControlWallpaper {
    private String[] listSize = new String[] {"2160x3840","1440x2560","1366x768","1080x1920","720x1280","1350x2400","938x1668","800x1420"};

    @RequestMapping(value = "/getBackground", method = RequestMethod.GET)
    public OutData getBackground(@RequestParam("page") String page, @RequestParam("type") String type){
        OutData outData = OutData.of();

        try {
            outData.setError("0");
            outData.setStatus(200);
            String abc = "";
            //String out = GET("https://wallpaperscraft.com/catalog/animals");
            Document doc = Jsoup.connect("https://wallpaperscraft.com/catalog/"+type+"/page"+page).get();
            Elements newsHeadlines = doc.select(".wallpapers__image");
            Elements poi = doc.select(".wallpapers__info-rating");
            outData.setImages(new ArrayList<>());
            int i = 0;
            for (Element headline : newsHeadlines) {
                Image image = Image.of();
                image.setName("Hunterdemon9x99");
                image.setPoint(poi.get(i).text());
                image.setSource(new ArrayList<>());

                for (String sz:listSize) {
                    SourceImage sourceImage = SourceImage.of();
                    String src = headline.attr("src");
                    System.out.println(src);
                    sourceImage.setUrl(src.replaceAll("[0-9]{3,4}x[0-9]{3,4}",sz));
                    String[] szi = sz.split("x");
                    sourceImage.setHeight(Integer.parseInt(szi[1]));
                    sourceImage.setWidth(Integer.parseInt(szi[0]));
                    image.getSource().add(sourceImage);
                }
                outData.getImages().add(image);
                i++;
            }
            return outData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        outData.setStatus(404);
        outData.setError("1");
        return outData;
    }

}
