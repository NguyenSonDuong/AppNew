package com.example.demod.controller;

import com.example.demod.model.news.ContentNew;
import com.example.demod.model.news.DataNew;
import com.example.demod.model.news.DataVNExpress;
import com.example.demod.model.news.InforNews;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
@RestController
@RequestMapping
public class ControlNews {
    @CrossOrigin("*/*")
    @RequestMapping(value = "/getNews",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getNews(@RequestParam("link") String url){
        DataNew data = DataNew.of();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements title = doc.getElementsByClass("title-detail");
            Elements type = doc.getElementsByClass("breadcrumb");
            Elements datetime = doc.getElementsByClass("date");
            Elements content = doc.getElementsByClass("Normal");
            Elements decription = doc.getElementsByClass("description");
            Elements image = doc.getElementsByAttributeValue("itemprop","contentUrl");
            ContentNew contentNew = ContentNew.of();
            contentNew.setType(type.text());
            contentNew.setTitle(title.text());
            contentNew.setTime(datetime.text());
            contentNew.setDecription(decription.text());
            contentNew.setLink(url);
            contentNew.setImage(image.attr("data-src"));
            StringBuffer str = new StringBuffer();
            for (int i=0;i<content.size();i++) {
                str.append(content.get(i).text());
            }
            contentNew.setContent(str.toString());
            data.setCode(200);
            data.setStatus("success");
            data.setContent(contentNew);

        } catch (IOException e) {
            e.printStackTrace();
            data.setCode(500);
            data.setStatus(e.getMessage());
        }

        return data;
    }
    @CrossOrigin("*/*")
    @RequestMapping(value = "/getNewsVnexpress",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getNews(){
        //
        DataVNExpress data = DataVNExpress.of();
        data.setContents(new ArrayList<>());
        try{
            Document doc = Jsoup.connect("https://vnexpress.net/thoi-su").get();
            Elements link = doc.getElementsByClass("title-news");

            for (Element item: link) {
                InforNews info = InforNews.of();
                info.setTitle(item.text());
                info.setUrl(item.children().get(0).attr("href"));
                data.getContents().add(info);
            }
            data.setCode(200);
            data.setStatus("success");
            return  data;
        }catch (Exception exception){
            exception.printStackTrace();
            data.setStatus(exception.getMessage());
            data.setCode(500);
        }

        return data;
    }

    @CrossOrigin("*/*")
    @RequestMapping(value = "/getImage",method = RequestMethod.POST,produces = MediaType.IMAGE_PNG_VALUE)
    public Object getImage(@RequestBody ContentNew data){
        try{
            System.out.println(data.toString());
            ByteOutputStream bos = null;
            BufferedImage bufferedImage = new BufferedImage(900,1100,BufferedImage.TYPE_INT_RGB);
            Graphics graphics2D = bufferedImage.createGraphics();

            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(0,0,900,1100);
            double w =0;
            double h =0;
            try{
                ByteArrayInputStream by = new ByteArrayInputStream(ActionCustom.GETInput(data.getImage()));
                BufferedImage  im = ImageIO.read(by);
                double percen = (double) 800/(double) im.getWidth();
                w =  im.getWidth()*percen;
                h = im.getHeight()*percen;
                System.out.println(percen+"|"+im.getWidth());
                graphics2D.drawImage(im,50,80,(int)w, (int)h, null);
            }catch (Exception e){
                e.printStackTrace();
            }

            graphics2D.setColor(Color.BLACK);
            Font font = new Font("Times New Roman", Font.BOLD, 30);
            graphics2D.setFont(font);
            //graphics2D.drawString(data.getContent().getTitle(),50,(int)h+120);
            h = ActionCustom.DrawString(50,(int)h+70,data.getTitle(),graphics2D,font);

            Font font4 = new Font("Times New Roman", Font.BOLD, 15);
            graphics2D.setFont(font4);
            FontMetrics metrics = graphics2D.getFontMetrics(font4);
            int hgt = metrics.getHeight();
            int adv = metrics.stringWidth(data.getType());
            graphics2D.setColor(new Color(201, 28, 28));
            h+=30;
            graphics2D.fillRect(50,(int)h,adv+20,hgt+10);
            h+=hgt+3;
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(data.getType(),60,(int)h);

            Font font2 = new Font("Times New Roman", Font.BOLD, 20);
            h = ActionCustom.DrawString(50,(int)h,data.getDecription(),graphics2D,font2);
            Font font3 = new Font("Times New Roman", Font.PLAIN, 20);
            h = ActionCustom.DrawString(50,(int)h,data.getContent(),graphics2D,font3);
            Font font5 = new Font("Calibri", Font.BOLD, 15);
            h = ActionCustom.DrawStringRight(50,(int)h+30,data.getTime(),graphics2D,font5);
            bos = new ByteOutputStream();
            ImageIO.write(bufferedImage, "png", bos);
            bos.close();
            return bos.getBytes();
        }catch (Exception e){

        }
        return "";
    }
}
