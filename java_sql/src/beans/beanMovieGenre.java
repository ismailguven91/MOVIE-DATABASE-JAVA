package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class beanMovieGenre {
	private String _movieGenre;
	private String _movieTitle;
	

	
	
	public String getMovieGenre() {
		return _movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this._movieGenre = movieGenre;
	}

	public String getMovieTite() {
		return _movieTitle;
	}

	public void setMovieTite(String movieTitle) {
		this._movieTitle = movieTitle;
	}

	public String toString() {
		String pattern = "Title = %s, Genre = %s";
		String text = String.format(pattern, this._movieTitle, this._movieGenre);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Title", this._movieTitle));
		dataList.add(new keyvaluepair("Genre ", this._movieGenre));
		
		return jsonHelper.toJsonObject(dataList);
	}
	
}

