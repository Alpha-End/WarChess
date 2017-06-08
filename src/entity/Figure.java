package entity;

import java.awt.image.BufferedImage;

public class Figure {
	int x,y;
	String name;
	int hpmax,hp,attack,defense,EXP,distance;
	int camp;
	boolean moveable;
	BufferedImage figureimg;
	public Figure(int x, int y, String name, int hpmax, int hp, int attack, int defense, int eXP, int distance,
			int camp, BufferedImage figureimg) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.hpmax = hpmax;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		EXP = eXP;
		this.distance = distance;
		this.camp = camp;
		this.figureimg = figureimg;
		this.moveable=true;
	}
	
	public void moveTo(int x,int y){
		if(moveable){
			this.x=x;
			this.y=y;
			moveable=false;
		}
	}
	
	public boolean isMoveable() {
		return moveable;
	}
	void setMoveable(boolean moveable) {
		this.moveable = moveable;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHpmax() {
		return hpmax;
	}
	public void setHpmax(int hpmax) {
		this.hpmax = hpmax;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getEXP() {
		return EXP;
	}
	public void setEXP(int eXP) {
		EXP = eXP;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getCamp() {
		return camp;
	}
	public void setCamp(int camp) {
		this.camp = camp;
	}
	public BufferedImage getFigureimg() {
		return figureimg;
	}
	public void setFigureimg(BufferedImage figureimg) {
		this.figureimg = figureimg;
	}
	
	
}
