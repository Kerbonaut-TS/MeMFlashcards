package Modules;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataFrame {
	
	String [][] df;
	int rows;
	int columns;

	public DataFrame(String filepath){

		this.df = this.read_csv(filepath);

	}


	public String [][] read_csv(String filepath){
		
		rows = this.countRecords(filepath)-1;
		columns = 8;

		df = new String [rows][columns];
		String currentLine;
		String[] stringArray;
		int r=0;
	
		try{
			
			BufferedReader br= new BufferedReader(new FileReader(filepath));

			while ((currentLine = br.readLine()) != null) {
				
				int itemsCount = this.countItems(currentLine);
				//get values
				stringArray=currentLine.split(",");
				
				if(r>0){
					//write values in the correct matrix  (skip header)
					for(int i =0; i<stringArray.length; i++){
						df[r-1][i]=stringArray[i];
					}
				}//skip the header

				r++; // next record
			}//end of file
			
			br.close();

			
		}catch(IOException e) {e.printStackTrace();}

		return df;

	}//end Get data
	
	
	private int countRecords(String filename){
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
	
	
	
	
	private int countItems(String s){
			
			if(s.length()==0) return 0;
	
			int count=0;
			//count commas
			count =s.length()-s.replace(",","").length();
			return count+1;
	
		}// end countitems
	
	
	
	public String [][] get_df(){ return df;}
	
	public String iloc(int row, int col){  return df[row][col];}

	public int shape(int i){

		return (i==0) ? this.rows : this.columns;

	}

		
}
