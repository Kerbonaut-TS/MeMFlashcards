import Modules.FlashcardGen;
import Modules.WordsMng;

public class MemFC {

	public static void main(String[] args) {
		
		
		String[][] words;
		
		//text file with words
		WordsMng wm = new WordsMng("C:\\Users\\Riccardo\\Dropbox\\MemorableFC\\wordsdb.csv");	
		words = wm.getWords();

		
		for (int w = 0; w<=115; w++) {
			try {
				
				
				
					//where to find the images
			        String img_dir = "C:\\Users\\Riccardo\\Dropbox\\MemorableFC\\Flashcards\\pics";
			        
				
				    //Specify where to save the flashcards
					String out_dir = "C:\\Users\\Riccardo\\Dropbox\\MemorableFC\\Flashcards";
					
					
					if(wm.get_status(w) != 1) {
											
						//Create Flashcard
						String filename = wm.get_ID(w) + ".jpg";
					    System.out.println("create flashcard for " , wm.get_word(w)););
	
					    FlashcardGen fg = new FlashcardGen(img_dir);
					    //fg.debug_sections(wm.get_word(w), wm.get_ID(w),  out_dir +"\\"+ filename);
					    fg.create_flashcard(wm.get_word(w), wm.get_ID(w),  out_dir +"\\"+ filename);
					    fg.create_flashcard(wm.get_eng(w), wm.get_ID(w),  out_dir +"\\eng\\"+ filename);
					    
					}else {System.out.println("Skipping card... " +  wm.get_word(w));}
									 
			} catch(Exception e ) { e.printStackTrace();}
		}//end for
		
		
	}//end Main
}//end class
