package entity;

public class Cell {
	
	public Cell(Figure figure){
		this.figure=figure;
		isfigure=true;
	}
	public Cell(int way){
		this.way=way;
		isfigure=false;
	}
	boolean isfigure;
	Figure figure;
	int way;
	public boolean isFigure(){
		return isfigure;
	}
	
	public Figure getFigure(){
		return figure;
	}
}
