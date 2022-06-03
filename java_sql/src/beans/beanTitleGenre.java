package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class beanTitleGenre {
	private String _movieTitle;
	private String _movieGenres;
	
	
	public String get_movieTitle() {
		return _movieTitle;
	}
	public void set_movieTitle(String _movieTitle) {
		this._movieTitle = _movieTitle;
	}
	public String get_movieGenres() {
		return _movieGenres;
	}
	public void set_movieGenres(String _movieGenres) {
		this._movieGenres = _movieGenres;
	}
	
	
	public String toString() {
		String pattern = "Title = %s, Genre = %s";
		String text = String.format(pattern, this._movieTitle, this._movieGenres);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Title", this._movieTitle));
		dataList.add(new keyvaluepair("Genres", this._movieGenres));

		
		

		return jsonHelper.toJsonObject(dataList);
	}
	

}

