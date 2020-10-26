package com.example.demod.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class ContentNew {
    private String title;
    private String time;
    private String image;
    private String link;
    private String content;
    private String decription;
    private String type;
}
