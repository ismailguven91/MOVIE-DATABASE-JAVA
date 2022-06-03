package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class beanTitleActor {
	private String _movieTitle;
	private String _movieActors;
	
	
	public String get_movieTitle() {
		return _movieTitle;
	}
	public void set_movieTitle(String _movieTitle) {
		this._movieTitle = _movieTitle;
	}
	public String get_movieActors() {
		return _movieActors;
	}
	public void set_movieActors(String _movieActors) {
		this._movieActors = _movieActors;
	}
	
	
	public String toString() {
		String pattern = "Title = %s, Actors = %s";
		String text = String.format(pattern, this._movieTitle, this._movieActors);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Movie", this._movieTitle));
		dataList.add(new keyvaluepair("Actor ", this._movieActors));

		
		

		return jsonHelper.toJsonObject(dataList);
	}
	

}