package entity;

import java.util.ArrayList;

public class EnermySelect {
	Figure figure;
	ArrayList<Figure> enermylist;
	int index;
	public EnermySelect(Figure figure) {
		// TODO Auto-generated constructor stub
		this.figure=figure;
		enermylist=new ArrayList<>();
		index=0;
	}
	public void addEnermy(Figure enermy){
		enermylist.add(enermy);
	}
}
