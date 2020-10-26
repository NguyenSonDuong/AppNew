package com.example.demod.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class DataVNExpress {
    private String status;
    private int code;
    private ArrayList<InforNews> contents;
}
