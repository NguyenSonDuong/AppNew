package com.example.demod.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class ReponsiveData {
    private String status;
    private int error;
    private Object resulf;
}
