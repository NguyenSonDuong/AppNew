package com.example.demod.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class DataNew {
    private String status;
    private int code;
    private ContentNew content;
}
