package com.example.demod.model.pixiv;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class DataItem{

	private String title;

	private String userName;

	private String userId;

	private int width;

	private String id;

	private int height;

}