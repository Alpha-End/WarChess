package view;

import java.awt.Graphics;

import entity.Map;
import interFace.PaintInterface;

public class Game implements PaintInterface,Runnable,interFace.StateControl{
	Map map;
	public Game() {
		// TODO Auto-generated constructor stub
		
		int[][] l=new int[][]{{0,0,0,1,1,1,1,1,1,1},
		{0,1,0,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,2,1,1,1,1,1},
		{1,1,1,2,1,2,1,1,1,1},
		{1,1,1,1,2,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1}};
		map=new Map(12, 10, l);
		map.roundstate=map.BLUE;
		map.addFigure(1,1, 1);
		map.addFigure(6, 3, 1);
		map.getReachable(6, 5, 7);
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}	

}
