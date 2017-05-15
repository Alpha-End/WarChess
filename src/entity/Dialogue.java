package entity;

import org.w3c.dom.Element; 

public class Dialogue {//台词
	String speaker;//发声者
	String dialogue;//台词
	String picturepath;//图片路径
	
	public String getSpeaker() {
		return speaker;
	}


	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}


	public String getDialogue() {
		return dialogue;
	}


	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}


	public String getPicturepath() {
		return picturepath;
	}


	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}


	@Override
	public String toString() {
		return "Dialogue [speaker=" + speaker + ", dialogue=" + dialogue + ", picturepath=" + picturepath + "]";
	}

	
	public Dialogue(String speaker, String dialogue, String picturepath) {
		super();
		this.speaker = speaker;
		this.dialogue = dialogue;
		this.picturepath = picturepath;
	}


	
}
