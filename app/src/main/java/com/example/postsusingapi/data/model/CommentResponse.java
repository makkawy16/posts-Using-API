package com.example.postsusingapi.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CommentResponse{

	@SerializedName("CommentResponse")
	private List<CommentResponseItem> commentResponse;

	public List<CommentResponseItem> getCommentResponse(){
		return commentResponse;
	}
}