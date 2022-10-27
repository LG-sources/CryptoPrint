import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.google.gson.JsonParseException;

public class App {
	
	
	public static void printHeader() throws IOException, InterruptedException, URISyntaxException {
		System.out.println("\t*=========================================================*");
		System.out.println("\t|  ____                  _        ____        _       _   |");
		System.out.println("\t| / ___|_ __ _   _ _ __ | |_ ___ |  _ \\\\ _ __(_)_ __ | |_ |");
		System.out.println("\t|| |   | '__| | | | '_ \\| __/ _ \\| |_) | '__| | '_  \\| __||");
		System.out.println("\t|| |___| |  | |_| | |_) | || (_) |  __/| |  | | | |  | |_ |");
		System.out.println("\t| \\____|_|   \\__, | .__/ \\__\\___/|_|   |_|  |_|_| |_|\\__| |");
		System.out.println("\t|            |___/|_|                                     |");
		System.out.println("\t*=========================================================*");
		System.out.println("\tAuthor : Guillaume Levené "+"©\n");
		
		System.out.println("      \t\tA simple example of using http requests and \n\t\t         JSON processing in Java.\n");
		
		System.out.println("You can find a list of cryptocurrency identifiers on :\nhttps://coinmarketcap.com/all/views/all/");
		System.out.println("========================================================================");
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException{
			
		

		printHeader();
	    
		Scanner input = new Scanner(System.in);
		System.out.print("Please provide the ID (Symbol) of the cryptocurrency you wish to track\nor quit to close the application :");
		String id = input.nextLine();
		System.out.println("========================================================================");
    	
    	
		while(!id.equals("quit")){
	    	try {
	    		Currency Curr = new Currency(id);
	    		System.out.println(Curr.toString());
	    	}
	    	catch(InterruptedException I) {
	    		System.out.println("The process was suddenly interrupted. Please try again.\n");
	    		I.printStackTrace();
	    		System.exit(-1);
	    	}
	    	catch(JsonParseException J) {
	    		System.out.println("The data retrieved by the HTTP request is not usable.\n");
	    		J.printStackTrace();
	    		System.exit(-1);
	    	}
	    	catch(IOException IO) {
	    	IO.printStackTrace();
	    	System.exit(-1);
	    	}
	    	System.out.println("========================================================================");
	    	System.out.print("Please provide the ID (Symbol) of the cryptocurrency you wish to track\nor quit to close the application :");
			id = input.nextLine();
			
		}
		input.close();
	    System.exit(0);	   
	}
	

}
