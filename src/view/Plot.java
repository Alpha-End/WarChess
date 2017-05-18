package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import dataControl.StateControl;
import entity.Dialogue;
import entity.Jump;
import interFace.PaintInterface;
import util.DOMParser;
import util.ElementR;
import util.R;

import java.io.File; 
import java.io.IOException; 
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException; 

public class Plot implements PaintInterface,Runnable,interFace.StateControl{
	ArrayList<Dialogue> dialist;
	Dialogue dialogue;
	int dialogueindex;
	int plotH,plotW,plotX,plotY;
	BufferedImage background;
	Jump jump;
	
	Font speakerfont;
	Font dialogfont;
	
	int dialogcounteveryline;
	//Label speakerlable,dialoguelable;
	//BufferedImage dialogbox;
	
	public Plot() {
		// TODO Auto-generated constructor stub
		setFormat();
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		/*if(!XMLData.next_xml_action_id.equals(ViewData.now_dialogues_id)){//若内存中台词过期则进入LOAD界面，并读取
			StateControl.end(this);//关闭PLOT界面
			StateControl.start(ViewData.load);//进入LOAD界面
			dialist=new ArrayList<>();
			dialogueindex=-1;
			Thread t=new Thread(this);//开启新线程，读取
			t.start();
		}
		else{
			paintDialogue(e);
		}*/
		paintDialogue(e);
	}
	
	public void nextDialogue(){
		if(dialogueindex<dialist.size()-1){//本段剧情台词没念完
			dialogueindex++;
			dialogue=dialist.get(dialogueindex);
			
			//speakerlable.setText(dialogue.getSpeaker());
			//dialoguelable.setText(dialogue.getDialogue());
			
			//System.out.println(dialogue.getDialogue());
		}
		else{
			jump.jumpThisAction();
			StateControl.next_action();
		}
	}
	
	@Override
	public void run() {//载入剧情
		// TODO Auto-generated method stub
		loadDialogue(XMLData.next_action_path,XMLData.next_action_id);//加载台词
		loadBackground(XMLData.next_action_path,XMLData.next_action_id);//加载背景
		loadNextAction(XMLData.next_action_path,XMLData.next_action_id);//加载跳转内容
		nextDialogue();//读取第一条台词
		ViewData.state=ViewData.PLOT;
	}
	
	private void loadBackground(String path,String id) {//加载背景
		// TODO Auto-generated method stub
		DOMParser parser = new DOMParser(); 
        Document document = parser.parse(path); 
        //get root element 
        Element rootElement = document.getDocumentElement(); 



        NodeList nodeList = rootElement.getElementsByTagName("dialogues"); 
        if(nodeList != null) 
        { 
        	Element firstelement=ElementR.getElementById(nodeList, id);
        	Element element = (Element)firstelement.getElementsByTagName("background").item(0); 
        	background=ElementR.loadImageByElement(element);
        } 
        background=R.hyalineProcess(background,plotW,plotH,plotX,plotY,ViewData.DIALOGBOX_ALPHA);//绘制透明对话框
       
	}
	
	
	private void loadNextAction(String path,String id) {//加载背景
		// TODO Auto-generated method stub
		DOMParser parser = new DOMParser(); 
        Document document = parser.parse(path); 
        //get root element 
        Element rootElement = document.getDocumentElement(); 



        NodeList nodeList = rootElement.getElementsByTagName("dialogues"); 
        if(nodeList != null) 
        { 
        	Element firstelement=ElementR.getElementById(nodeList, id);
        	Element element = (Element)firstelement.getElementsByTagName("jump").item(0); 
        	
        	try{
        		jump=ElementR.getNextActionByElement(element);
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        	
        } 
        
       
	}
	
	private void loadDialogue(String path,String id){//加载台词
		DOMParser parser = new DOMParser(); 
        Document document = parser.parse(path); 
        //get root element 
        Element rootElement = document.getDocumentElement(); 



        NodeList nodeList = rootElement.getElementsByTagName("dialogues"); 
        if(nodeList != null) 
        { 
        	Element firstelement=ElementR.getElementById(nodeList, id);
        	nodeList = firstelement.getElementsByTagName("dialogue"); 
        	for (int i = 0 ; i < nodeList.getLength(); i++) 
            { 
               Element element = (Element)nodeList.item(i); 
               dialist.add(ElementR.loadDialogueByElement(element));
               
               //System.out.println(dialist.get(dialist.size()-1));
               
            }
        } 
        
	}
	private void paintDialogue(Graphics e) {
		// TODO Auto-generated method stub
		e.drawImage(background, 0, 0, null);//绘制背景

		if(dialogue!=null){
			
			if(dialogue.getSpeaker()!=null){
				e.setFont(speakerfont);
				e.drawString(dialogue.getSpeaker(), plotX+plotW/10, plotY+speakerfont.getSize()+5);
			}
			e.setFont(dialogfont);
			
			int i=0;
			for(;(i+1)*dialogcounteveryline<dialogue.getDialogue().length();i++){
				String s=dialogue.getDialogue().substring(i*dialogcounteveryline, (i+1)*dialogcounteveryline);
				e.drawString(s, plotX+plotW/20, plotY+plotH/5+30+i*(dialogfont.getSize()+5));
			}
			String s=dialogue.getDialogue().substring(i*dialogcounteveryline, dialogue.getDialogue().length());
			e.drawString(s, plotX+plotW/20, plotY+plotH/5+30+i*(dialogfont.getSize()+5));
		}
		
		/*
		e.drawLine(plotX, plotY, plotX+plotW, plotY);//画框
		e.drawLine(plotX, plotY, plotX, plotY+plotH);
		e.drawLine(plotX, plotY+plotH, plotX+plotW, plotY+plotH);
		e.drawLine(plotX+plotW, plotY, plotX+plotW, plotY+plotH);
		*/
		//e.drawImage(dialogbox, plotX, plotY, null);
	}
	
	private void setFormat() {
		// TODO Auto-generated method stub
		plotH=ViewData.FRAMEH/3;
		plotW=ViewData.FRAMEW*9/10;
		plotX=ViewData.FRAMEW/20;
		plotY=ViewData.FRAMEH*2/3-ViewData.FRAMEH/15;
		//System.out.println(plotH);System.out.println(plotW);
		
		
		speakerfont=ViewData.SPEAKERFONT;
		dialogfont=ViewData.DIALOGUEFONT;
		
		
		dialogcounteveryline=plotW/dialogfont.getSize()*9/10;
		//speakerlable=new Label();
		//dialoguelable=new Label();
		
		//speakerlable.setFont(ViewData.SPEAKERFONT);
		//speakerlable.setLocation(plotX, plotY);
		//dialoguelable.setFont(ViewData.SPEAKERFONT);
		//dialoguelable.setLocation(plotX, plotY+speakerlable.getFont().getSize()+plotH/10);
		
		
	}
	
	public void start(){
		if(!XMLData.next_action_id.equals(ViewData.now_action_id)){//若内存中台词过期则进入LOAD界面，并读取
			ViewData.now_action_id=new String(XMLData.next_action_id);//更新剧情ID
			StateControl.start(ViewData.load);//进入LOAD界面
			dialist=new ArrayList<>();
			
			Thread t=new Thread(this);//开启新线程，读取
			dialogueindex=-1;
			t.start();
			
			
		}
		else{
			ViewData.state=ViewData.PLOT;//进入剧情界面
		}
	}
	public void end(){
		/*
		speakerlable.setVisible(false);
		dialoguelable.setVisible(false);
		*/
	}
}
