package dataContainer;

public class searchResponse {
	private String path;
	private double tfidf;
	public searchResponse(String p,double i){
		this.path=p; 
		this.tfidf=i;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public double getTfidf() {
		return tfidf;
	}
	public void setTfidf(double tfidf) {
		this.tfidf = tfidf;
	}
	
}

