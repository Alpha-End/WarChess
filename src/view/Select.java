package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.ViewData;
import data.XMLData;
import dataControl.StateControl;
import entity.Option;
import interFace.PaintInterface;
import util.DOMParser;
import util.ElementR;
import util.R;

public class Select implements PaintInterface,Runnable,interFace.StateControl{
	ArrayList<Option> optionlist;
	BufferedImage background,optionbox_selected,optionbox_unselected;
	int selectW,selectH,index,optionboxinterval;
	Font optionfont;
	
	public Select() {
		// TODO Auto-generated constructor stub
		selectW=ViewData.FRAMEW;
		selectH=ViewData.FRAMEH;
		optionboxinterval=20;//两选项纵向间隔20
		optionfont=ViewData.OPTIONFONT;
	}
	public void UP_KEY(){
		if(index>0){
			index--;
		}
	}
	public void DOWN_KEY(){
		if(index<optionlist.size()-1){
			index++;
		}
	}
	public void selectOption(){
		optionlist.get(index).getJump().jumpThisAction();
		StateControl.next_action();
	}
	private void loadDialogue(String path,String id){//加载台词
		DOMParser parser = new DOMParser(); 
        Document document = parser.parse(path); 
        //get root element 
        Element rootElement = document.getDocumentElement(); 
        optionlist=new ArrayList<>();


        NodeList nodeList = rootElement.getElementsByTagName("select"); 
        if(nodeList != null) 
        { 
        	Element firstelement=ElementR.getElementById(nodeList, id);
        	nodeList = firstelement.getElementsByTagName("option"); 
        	for (int i = 0 ; i < nodeList.getLength(); i++) 
            { 
               Element element = (Element)nodeList.item(i); 
               optionlist.add(ElementR.getOptionByElement(element));
               
            }
        } 
        
	}
	private void loadBackground(String path,String id) {//加载背景
		// TODO Auto-generated method stub
		DOMParser parser = new DOMParser(); 
        Document document = parser.parse(path); 
        //get root element 
        Element rootElement = document.getDocumentElement(); 



        NodeList nodeList = rootElement.getElementsByTagName("select"); 
        if(nodeList != null) 
        { 
        	Element firstelement=ElementR.getElementById(nodeList, id);
        	Element element = (Element)firstelement.getElementsByTagName("background").item(0); 
        	background=ElementR.loadImageByElement(element);
        } 
        background=R.hyalineProcess(background,selectW,selectH,0,0,ViewData.SELECT_BACKGROUND_ALPHA);//绘制透明对话框
       
	}
	private void loadOptionBox() {
		// TODO Auto-generated method stub
		optionbox_selected=R.load(XMLData.OPTION_BOX_SELECTED_IMG_PATH);
		optionbox_unselected=R.load(XMLData.OPTION_BOX_UNSELECTED_IMG_PATH);
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		e.drawImage(background, 0, 0, null);
		int totaloptionboxH=optionbox_selected.getHeight()*optionlist.size()+optionboxinterval*(optionlist.size()-1);
		int y=(selectH-totaloptionboxH)/2;
		int x=(selectW-optionbox_selected.getWidth())/2;
		for(int i=0;i<optionlist.size();i++){
			if(index==i){
				paintOptionSelect(e,x,y,optionlist.get(i),optionbox_selected);//绘制每个选项
			}
			else{
				paintOptionSelect(e, x, y,optionlist.get(i),optionbox_unselected);
			}
			y+=optionbox_selected.getHeight()+optionboxinterval;
		}
	}
	
	private void paintOptionSelect(Graphics e,int x,int y,Option op,BufferedImage optionbox) {
		// TODO Auto-generated method stub
		e.drawImage(optionbox, x, y, null);
		e.setFont(optionfont);
		e.drawString(op.getText(), x+(optionbox.getWidth()-optionfont.getSize()*op.getText().length())/2, y+(optionbox.getHeight()+optionfont.getSize())/2);
	}
	@Override
	public void run() {//资源加载线程
		// TODO Auto-generated method stub
		StateControl.start(ViewData.load);
		loadDialogue(XMLData.next_action_path,XMLData.next_action_id);
		loadBackground(XMLData.next_action_path,XMLData.next_action_id);
		loadOptionBox();
		ViewData.state=ViewData.SELECT;
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		if(!XMLData.next_action_id.equals(ViewData.now_action_id)){//若内存中选项过期则进入LOAD界面，并读取
			
			ViewData.now_action_id=new String(XMLData.next_action_id);//更新选项ID
			StateControl.start(ViewData.load);//进入LOAD界面
			Thread t=new Thread(this);//开启新线程，读取
			index=0;
			t.start();
		}
		else{
			ViewData.state=ViewData.SELECT;//进入剧情界面
		}
	}
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
}
