package com.example.demod.model.mihoyo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Cover{

	private String size;

	private int width;

	private String format;

	private String url;

	private int height;


}