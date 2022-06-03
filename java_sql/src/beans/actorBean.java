package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class actorBean {
	private int _id;
	private String _firstName;
	private String _lastName;
	

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_firstName() {
		return _firstName;
	}
	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}
	public String get_lastName() {
		return _lastName;
	}
	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}
	
	public String toString() {
		String pattern = "First name = %s, Last name = %s";
		String text = String.format(pattern, this._firstName, this._lastName);
		

		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("First Name", this._firstName));
		dataList.add(new keyvaluepair("Last Name", this._lastName));

		
		

		return jsonHelper.toJsonObject(dataList);
	}
	
	

}
