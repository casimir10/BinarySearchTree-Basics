package cisc3150hw3;


public class Movie {
	
	  private String title;
	  private int releaseYear;
	  public Movie left,right;

	  public Movie(String s, int r) {
		  title = s;
		  releaseYear = r;
		  left = right = null;
	  }
	  
	  public String getTitle() {
		  return title;
	  }
	  
	  public void setTitle(String i) {
		  title = i;
	  }
	  
	  public int getYear() {
		  return releaseYear;
	  }
	  
	 
}
