package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieBean {
	private int _movieID;
	private String _movieTitle;
	private int _movieLength;
	
	
	public int get_movieID() {
		return _movieID;
	}
	public void set_movieID(int _movieID) {
		this._movieID = _movieID;
	}
	public String get_movieTitle() {
		return _movieTitle;
	}
	public void set_movieTitle(String _movieTitle) {
		this._movieTitle = _movieTitle;
	}
	public int get_movieLength() {
		return _movieLength;
	}
	public void set_movieLength(int _movieLength) {
		this._movieLength = _movieLength;
	}
	
	
	public String toString() {
		String pattern = "Title = %s, Length = %s"+" min";
		String text = String.format(pattern, this._movieTitle, this._movieLength);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Title", this._movieTitle));
		dataList.add(new keyvaluepair("Length", String.valueOf(this._movieLength)));
		
		return jsonHelper.toJsonObject(dataList);
	}
	
}
