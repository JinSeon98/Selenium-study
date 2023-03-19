package com.practice;

public class Video {
	String title;
	String channel;
	int view;
	int like;
	
	public Video(String title, String channel, int view, int like) {
		super();
		this.title = title;
		this.channel = channel;
		this.view = view;
		this.like = like;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}

}
