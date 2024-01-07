import java.io.IOException;

import Modules.FlashcardGen;
import Modules.DataFrame;
import filters.StatFilter;

public class MemFC {

	public static void main(String[] args) {
		
		String[][] words;
		
		//text file with words
		DataFrame wm = new DataFrame(".\\files\\wordsdb.csv");
		
		for (int w = 0; w<=259; w++) {
			try {
				
				//where to find the images
				String img_dir = ".\\files\\pics";

				//Specify where to save the flashcards
				String out_dir = "C:\\Users\\Riccardo\\Dropbox\\MemorableFC\\";

				//Create Flashcard
				String imgname = wm.iloc(w,0);
				String word_ell = wm.iloc(w,2);
				String word_eng = wm.iloc(w,5);
				FlashcardGen fg = new FlashcardGen();

				System.out.println(imgname+") creating flashcard for " +  word_ell );

				//create cards
				fg.create_flashcard(word_ell, img_dir+"\\"+imgname,  out_dir +"\\"+ imgname+".jpg");
				fg.create_flashcard(word_eng, img_dir+"\\"+imgname,  out_dir +"\\eng\\"+ imgname+".jpg");


			} catch(Exception e ) { e.printStackTrace();}
		}//end for
		
		
	}//end Main
}//end class
