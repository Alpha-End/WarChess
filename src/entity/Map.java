package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import data.ViewData;

public class Map {
	public Map(int h,int w,int[][] l){
		x=0;y=0;
		width=w;
		height=h;
		location=l;
		figurelocation=new Figure[w][h];
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				figurelocation[i][j]=null;
			}
		}
	}
	public void addFigure(int x,int y,Figure f){
		if(figurelocation[x][y]!=null){
			
		}
		else{
			figurelocation[x][y]=f;
		}
	}
	int height;
	int width;
	int x,y;//当地图大于显示框,x y表示地图最左上角的单位
	int[][] location;
	Figure[][] figurelocation;
	public int roundstate;//当前回合为红方/蓝方回合
	public static final int BLUE=-1,RED=1,EMPTY=0;
	public static final int UNREACH=0,MAXDISTANCE=100,WAY=1,BARRIER=2;//普通路，障碍路，普通单位不可达，全单位不可达,蓝方单位，红方单位
	int[][] reachable;
	BufferedImage map_img;
	
	static final int[] xstep={1,-1,0,0},ystep={0,0,1,-1};
	
	public void figureRemove(int x0,int y0,int x1,int y1,Figure f){
		if(reachable[x1][y1]>=0){
			figurelocation[x0][y0]=null;
			figurelocation[x1][y1]=f;
		}
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int[][] getLocation() {
		return location;
	}
	public void setLocation(int[][] location) {
		this.location = location;
	}
	public BufferedImage getMap_img() {
		return map_img;
	}
	public void setMap_img(BufferedImage map_img) {
		this.map_img = map_img;
	}
	public void paint(Graphics e){
		e.drawImage(map_img, -x*ViewData.MAP_PIXEL,- y*ViewData.MAP_PIXEL,null);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int[][] getReachable(int x,int y,int step){//通过单位位置及移动力获得可达范围
		reachable=new int[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				reachable[i][j]=-1;
			}
		}
		DFS(x,y,step);
		/*
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				String s=" "+reachable[i][j];
				s=String.format("%-5s", s);
				System.out.print(s);
			}
			System.out.println("");
		}*/
		return reachable;
	}
	int DFS(int x,int y,int step){
		if(x<0||y<0||x>=width||y>=height){
			return 0;
		}
		step-=stepDistance(x, y);
		if(step<=0||reachable[x][y]>=step){
			return 0;
		}
		reachable[x][y]=step;
		for(int i=0;i<4;i++){
			DFS(x+xstep[i],y+ystep[i],step);
		}
		return 0;
	}
	
	int stepDistance(int x,int y){
		if(figurelocation[x][y]==null){
			if(location[x][y]==WAY){
				return 1;
			}
			if(location[x][y]==BARRIER){
				return 2;
			}
		}
		else if(roundstate*figurelocation[x][y].getCamp()>0){//若当前回合与当前位置为同一方
			if(location[x][y]==WAY){
				return 1;
			}
			if(location[x][y]==BARRIER){
				return 2;
			}
		}
		return MAXDISTANCE;
	}
	
	
	public void refreshMap(){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(this.figurelocation[i][j]!=null){					
					if(!this.figurelocation[i][j].isAlive()){
						this.figurelocation[i][j]=null;
					}
				}
			}
		}
	}
	public boolean figureHaveEnermy(Figure figure,int relative_x,int relative_y){
		int[] addx={-1,1,0,0};
		int[] addy={0,0,1,-1};
		for(int i=0;i<4;i++){
			Cell cell=this.getCell(relative_x+addx[i], relative_y+addy[i]);
			if(cell.isfigure&&(figure.camp!=cell.getFigure().camp)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Figure> getEnermy(Figure figure,int relative_x,int relative_y){
		int[] addx={-1,1,0,0};
		int[] addy={0,0,1,-1};
		ArrayList<Figure> enermylist=new ArrayList<>();
		for(int i=0;i<4;i++){
			Cell cell=this.getCell(relative_x+addx[i], relative_y+addy[i]);
			if(cell.isfigure&&(figure.camp!=cell.getFigure().camp)){
				enermylist.add(cell.getFigure());
			}
		}
		return enermylist;
	}
	
	public Cell getCell(int relative_x,int relative_y){
		
		
		
		int x=relative_x+this.x,y=relative_y+this.y;
		
		//System.out.println("x="+x+" y="+y);
		//System.out.println(location[10][5]);
		Cell cell;
		cell=new Cell(location[x][y]);
		if(figurelocation[x][y]!=null){
			cell=new Cell(figurelocation[x][y]);
		}
		
		return cell;
	}

}
