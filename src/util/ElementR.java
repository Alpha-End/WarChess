package util;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.ViewData;
import data.XMLData;
import entity.Dialogue;

public class ElementR {
	public static void getNextActionByElement(Element element) throws Exception{
		
		Element e;
		e=(Element)element.getElementsByTagName("action").item(0);
		if(e!=null){
			switch(e.getTextContent()){
			case "SELECT":{XMLData.next_action=ViewData.SELECT;break;}
			case "PLOT":{XMLData.next_action=ViewData.PLOT;break;}
			}
		}
		else{
			throw new Exception();
		}
		e=(Element)element.getElementsByTagName("source_path").item(0);
		if(e!=null){
			XMLData.dialogue_path=e.getTextContent();
		}
		else{
			throw new Exception();
		}
		e=(Element)element.getElementsByTagName("action_id").item(0);
		if(e!=null){
			XMLData.next_xml_action_id=e.getTextContent();
		}
		else{
			throw new Exception();
		}
	}
	public static Dialogue loadDialogueByElement(Element element) {
		// TODO Auto-generated method stub
		Element e;
		String speaker=null,dialogue=null,picturepath=null;
		e=(Element)element.getElementsByTagName("speaker").item(0);
		if(e!=null){
			speaker=e.getTextContent();
		}
		e=(Element)element.getElementsByTagName("content").item(0);
		if(e!=null){
			dialogue=e.getTextContent();
		}
		e=(Element)element.getElementsByTagName("picture").item(0);
		if(e!=null){
			picturepath=e.getTextContent();
		}
		return new Dialogue(speaker, dialogue, picturepath);
		
	}
	
	public static BufferedImage loadImageByElement(Element element) {
		// TODO Auto-generated method stub
		Element e;
		String img_path=null;
		e=(Element)element.getElementsByTagName("img_path").item(0);
		if(e!=null){
			img_path=e.getTextContent();
		}
		//System.out.println(img_path);
		return R.load(img_path);
		
	}
	public static Element getElementById(NodeList nodelist,String id){
		if(nodelist != null) 
        { 
        	for(int i = 0 ; i < nodelist.getLength(); i++){
        		Element element = (Element)nodelist.item(i); 
                String diaid = element.getAttribute("id"); 
                if(diaid.equals(id)){
                	return element;
                }
        	}
           
        } 
		return (Element)nodelist.item(0); 
	}
}