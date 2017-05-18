package entity;

public class Option {
	String text;
	Jump jump;
	public Option(String text, Jump jump) {
		super();
		this.text = text;
		this.jump = jump;
	}
	@Override
	public String toString() {
		return "Option [text=" + text + ", jump=" + jump + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Jump getJump() {
		return jump;
	}
	public void setJump(Jump jump) {
		this.jump = jump;
	}
	
}
