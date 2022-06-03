package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class genreBean {
	private int _genreID;
	private String _genre;
	
	
	public int get_genreID() {
		return _genreID;
	}
	public void set_genreID(int _genreID) {
		this._genreID = _genreID;
	}
	public String get_genre() {
		return _genre;
	}
	public void set_genre(String _genre) {
		this._genre = _genre;
	}
	
	public String toString() {
		String pattern = "ID = %s, Genre = %s";
		String text = String.format(pattern, this._genreID, this._genre);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Genre: ", this._genre));

		
		

		return jsonHelper.toJsonObject(dataList);
	}


}
