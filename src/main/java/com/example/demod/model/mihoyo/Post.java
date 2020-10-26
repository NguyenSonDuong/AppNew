package com.example.demod.model.mihoyo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Post{

	private List<String> images;

	private List<Object> topicIds;

	private int isOriginal;

	private String subject;

	private String replyTime;

	private int createdAt;

	private String cover;

	private String uid;

	private int fForumId;

	private int isDeleted;

	private String postId;


}