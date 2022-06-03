package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class beanMovieActor {
	
	private String _movieID;
	private String _actorID;
	
	
	public String get_movieID() {
		return _movieID;
	}
	public void set_movieID(String _movieID) {
		this._movieID = _movieID;
	}
	public String get_actorID() {
		return _actorID;
	}
	public void set_actorID(String _actorID) {
		this._actorID = _actorID;
	}
	
	
	public String toString() {
		String pattern = "Movie ID = %s, Actor ID = %s";
		String text = String.format(pattern, this._movieID, this._actorID);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Movide ID", this._movieID));
		dataList.add(new keyvaluepair("Actor ID", this._actorID));

		
		

		return jsonHelper.toJsonObject(dataList);
	}
	

}