package Modules;

import java.awt.Color;
import java.io.IOException;

import IconoclasticLayer.IconoclasticLayer;
import IconoclasticLayer.RGBHolder;



public class FlashcardGen {
	
	//images
	String input_dir;
	IconoclasticLayer ic;
	RGBHolder img;
	
	//best coordinates for text
	int R;
	int C;
	


	public FlashcardGen(String input_path) {
		
		this.input_dir = input_path;

	}
	
	public void create_flashcard(String  word,Integer id, String output_path) throws Exception {
		
		
		
		// === get Image
		img = this.import_img(input_dir+"\\"+id+".jpg");
		
		// === create sections
	    ic = new IconoclasticLayer(img);
	    ic.setResolution(3);								    
	    double[][] rankedSections = ic.rank_sections();
	    
	    // === update best coordinates
	     R =  (int) rankedSections[0][1];
	     C =  (int) rankedSections[0][2];		     
	    
	    
	    // === write Text
	    int left_margin =  0; //left margin already considered in string writing method
	    int top_margin = 72;
	    img.add_text(word, 72, this.get_color(),  ic.sections[R][C].get_center_x()+left_margin,ic.sections[R][C].get_center_y()+top_margin);
	    img.saveImage(output_path);
		
	}
	
	public void debug_sections(String  word,Integer id, String output_path) throws Exception {
		
		
		
		// === get Image
		img = this.import_img(input_dir+"\\"+id+".jpg");
		
		// === create sections
	    ic = new IconoclasticLayer(img);
	    ic.setResolution(3);								    
	    double[][] rankedSections = ic.rank_sections();
		     
	    
	    
	    // === write Text
	    int left_margin =  0; //left margin already considered in string writing method
	    int top_margin = 0;
	    
	    int rank = 0;
	    
	    for (int r =0; r<=8; r++) {
		        
			    // === update best coordinates
			     R =  (int) rankedSections[r][1];
			     C =  (int) rankedSections[r][2];	
			     
			     int X = ic.sections[R][C].get_center_x()+left_margin;
			     int Y = ic.sections[R][C].get_center_y()+top_margin;
		    	
			    img.add_text(String.valueOf(r), 72, this.get_color(), X , Y);
			    rank++;
		  
		 }
	    

	    
	    img.saveImage(output_path);
	    
		
	}
	
	public Color get_color() {
		
		
	    //determine text colour
	    double[] avgValues = ic.sections[R][C].getAVGvalue();
	    double  brightness = (avgValues[0]+avgValues[1]+avgValues[2])/3; //get avg brightness in RGB channels
	    
		Color text_color = ((brightness<180) ) ?  Color.WHITE :  Color.BLACK ;
		
		return text_color;
		
	}
			
	public RGBHolder import_img(String path) {
		
		RGBHolder original = new RGBHolder();
		RGBHolder img = new RGBHolder();
		
		try {
			
			original.setImageFromFile(path);
			original = original.resize(480,600);
			img.clone(original);
			
			
		}catch(IOException e){ System.out.println("Not Found: " + path);e.printStackTrace(); }
	
		
		return img;
		
	}

}
