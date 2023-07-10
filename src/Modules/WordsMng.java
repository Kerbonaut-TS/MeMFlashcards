package Modules;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordsMng {
	
	String [][] words;
	int rows;
	int columns;
	
	public WordsMng (String filename){
	
		this.readfile(filename);	
		
	}
	
	public void set_columns (int c) { columns = c; };
	
	public void set_rows (int r) { rows = r; };
	
	public void readfile(String filename){
		
		rows = this.countRecords(filename)-1;
		columns = 8	;	
		
		//records (columns: img,Article,Greek singular,Article2,Greek Plural,English,Type,Mastered)

		words = new String [rows][columns];
		
		String currentLine;
		String[] stringArray;
		int r=0;
	
		try{
			
			BufferedReader br= new BufferedReader(new FileReader(filename));

			while ((currentLine = br.readLine()) != null) {
				
				int itemsCount = this.countItems(currentLine);
				
				//get values
				stringArray= new String[itemsCount];
				stringArray=currentLine.split(",");
				
				if(r>0){
					//write values in the correct matrix  (skip header)
					for(int i =0; i<stringArray.length; i++) words[r-1][i]=stringArray[i];
				}//skip the header

				r++; // next record
			}//end of file
			
			br.close();
			
			
		}catch(IOException e) {e.printStackTrace();}

	}//end GetdataFile
		
	public int countRecords(String filename){
		//fast record count 
		
		int count=0;
		System.out.print("Reading file..");

		try{			
			BufferedReader br= new BufferedReader(new FileReader(filename));
			while ((br.readLine()) != null) count++;
			br.close();	
		}catch(IOException e) {e.printStackTrace();}
				
		System.out.println("Record count: " + count);
		return count;
		
	}//end countRecords
	
	
	public int countItems(String s){
			
			if(s.length()==0) return 0;
	
			int count=0;
			//count commas
			count =s.length()-s.replace(",","").length();
			return count+1;
	
		}// end countitems
	
	
	public String [][] getWords(){ return words;}
	
	public String get_eng(int w){  return words[w][5];}
	
	public String get_word(int w){ return words[w][1] + words[w][2];}
	
	public Integer get_ID(int w){ return Integer.parseInt(words[w][0]); }
	
	public Integer get_status(int w){ 
		
		if(words[w][7] != null) {
			return Integer.parseInt(words[w][7]); 

		}else {
			return 0;
		}
			

	}
	
		
}
