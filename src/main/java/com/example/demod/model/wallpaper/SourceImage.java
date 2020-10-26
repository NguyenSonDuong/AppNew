package com.example.demod.model.wallpaper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class SourceImage {
    private String url;
    private int width;
    private int height;


}
