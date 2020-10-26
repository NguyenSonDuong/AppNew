package com.example.demod.model.wallpaper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class OutData {
    private String error;
    private int status;
    private ArrayList<Image> images;
}
