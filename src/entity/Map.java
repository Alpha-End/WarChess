package entity;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Map {
	public Map(int h,int w,int[][] l){
		width=w;
		height=h;
		location=l;
		figurelocation=new int[h][w];
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				figurelocation[i][j]=EMPTY;
			}
		}
	}
	public void addFigure(int x,int y,int figurestate){
		if(figurelocation[x][y]==EMPTY){
			figurelocation[x][y]=figurestate;
		}
	}
	int height;
	int width;
	int[][] location,figurelocation;
	public int roundstate;//当前回合为红方/蓝方回合
	public static final int BLUE=-1,RED=1,EMPTY=0;
	public static final int UNREACH=0,MAXDISTANCE=100,WAY=1,BARRIER=2;//普通路，障碍路，普通单位不可达，全单位不可达,蓝方单位，红方单位
	int[][] reachable;
	static final int[] xstep={1,-1,0,0},ystep={0,0,1,-1};
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
	public int[][] getReachable(int x,int y,int step){//通过单位位置及移动力获得可达范围
		reachable=new int[height][width];
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
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
		if(x<0||y<0||x>=height||y>=width){
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
		if(roundstate*figurelocation[x][y]>=0){//若当前回合与当前位置为同一方
			if(location[x][y]==WAY){
				return 1;
			}
			if(location[x][y]==BARRIER){
				return 2;
			}
		}
		return MAXDISTANCE;
	}
}