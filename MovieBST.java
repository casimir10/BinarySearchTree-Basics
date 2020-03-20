package cisc3150hw3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieBST {
	
	 private Movie first;
	 
	 //This method is extremely confusing for a number of Reasons
	 //1)I Don't understand how to traverse between two specific points in a binary tree
	 //2)Since the two Parameters are Strings, How do I get their positions within the tree?
	 //3)What exactly is meant by "between start and end"? Should I find the path between the nodes containing the strings or the levels between them?
	 
	  public void subSet(String start, String end){
	    // Selects movie titles that fall alphabetically between start and end.
		
		if(search(start,first) != null) {
			order(search(start,first),end);
		}
		else {
			System.out.print("null");
		}
		
	  }
	  
	  //Inserts all Movie nodes into a Binary Tree
	  public void insert(String d,int a) {
		  if(first == null) {
			  first = new Movie(d,a);
		  }  
	  Movie loc = first;
	  while(true) {
		 if(d.compareTo(loc.getTitle()) < 0){
			 if(loc.left != null) loc = loc.left;
			 else {loc.left = new Movie(d,a); break;}
		 }
		 else if(d.compareTo(loc.getTitle()) > 0) {
			 if(loc.right != null) loc = loc.right;
			 else {loc.right = new Movie(d,a); break;}
		 }
		 else break; 
		  	  
	  }	  
	 }//End Insert
	  
	  public void traverse() {order(first,"Persuasion (1995)");}
	  
	  //Prints the Values from the Binary Tree recursively
	  public void order(Movie first, String stop) {
		  if (first != null && !(first.getTitle().equals(stop))) {
			  order(first.left,stop);
			  System.out.println(first.getTitle() + " ");
			  order(first.right,stop);
		  }
	  }//end order
	  
	  //Allows for searching of a movie node within the Binary Tree
	  public Movie search(String name, Movie node){
		    if(node != null){
		        if(node.getTitle().equals(name)){
		           return node;
		        } else {
		            Movie foundNode = search(name, node.left);
		            if(foundNode == null) {
		                foundNode = search(name, node.right);
		            }
		            
		            return foundNode;
		         }
		    } else {
		        return null;
		    }
		}
	  
	  
	
	  
	public static void main(String []args) throws IOException {
			
		    File file = 
		    	      new File("C:\\Users\\Christian Casimir\\Desktop\\movies.csv"); 
		    Scanner sc = new Scanner(file); 
		    
		    File outputfile = new File("output.txt");
		    Scanner out = new Scanner(outputfile);
		        
		   //Read from file both Name and Year Together 
		   LinkedList<String> movies = new LinkedList<String>();
		   LinkedList<Integer> years = new LinkedList<Integer>();
		   
		   //Splits the title and release year
		   String line;
			while(sc.hasNext()) {
				line = sc.nextLine();
				String[] tokens = line.split(",");
				
				if(line.contains("\"")) {
					movies.add(tokens[1] + tokens[2]);
				}
				else {
					movies.add(tokens[1]);
				}	
			}
			//Splits the movie year from the title
			String line2;
			for(int i = 0;i< movies.size();i++) {
				line2 = movies.get(i);
				
				String x = line2;
				 Matcher m = Pattern.compile("\\((.*?)\\)").matcher(x);
				while(m.find()) {
					if(Character.isDigit(m.group(1).charAt(1))) {
						years.add(Integer.parseInt(m.group(1)));
					}
					else {
						 years.add(0000);	
					}
				}
				
				//years.add(tokens[0]);
				//System.out.println(answer);
			}	
		    //TESTER 	    
			MovieBST movietree = new MovieBST();
			for(int i = 0; i < movies.size();i++) 
				movietree.insert(movies.get(i),years.get(i));
			
				
			movietree.subSet("Bio-Dome (1996)","Mortal Kombat (1995)");
			System.out.println("");
			movietree.subSet("Richard III (1995)","Heidi Fleiss: Hollywood Madam (1995)");
			System.out.println("");
			movietree.subSet("Jade (1995)","Crumb (1994)");
		
		    sc.close();
		    out.close();
	}//end main
}