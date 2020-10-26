package com.example.demod.model.wallpaper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Image {
    private ArrayList<SourceImage> source;
    private String name;
    private String point;
}
