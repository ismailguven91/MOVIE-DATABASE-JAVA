package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import beans.actorBean;
import helpers.jsonHelper;

public class actors {
	private Connection _connection;
	private ArrayList<actorBean> _actors;
	
	private String selectAllActors = "select * from actor ORDER BY actor_id ASC";
	private String updateActorFirstName = "UPDATE actor SET actor_first_name = ? WHERE actor_first_name = ?;";
	private String updateActorLastName = "UPDATE actor SET actor_last_name = ? WHERE actor_last_name = ?;";
	private String removeActor = "DELETE FROM actor WHERE actor_first_name = ? AND actor_last_name = ?;";
	private String addActor = "insert into actor (actor_first_name, actor_last_name) VALUES (?,?);";

	public actors(Connection cn) {
		this._connection = cn;
		this._actors = new ArrayList<actorBean>();
		getActors();
	}
	
	public ArrayList<actorBean> getActors() {
		if (this._actors.size() > 0) 
			return this._actors;
			
		this._actors = new ArrayList<actorBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectAllActors)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this._actors;
	}
	
	public int updateActorFirstName(String actorNewFirstName, String actorFirstName) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateActorFirstName)) {
			myQry.setString(1, actorNewFirstName);
			myQry.setString(2, actorFirstName);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateActorLastName(String actorNewLastName, String actorLastName) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateActorLastName)) {
			myQry.setString(1, actorNewLastName);
			myQry.setString(2, actorLastName);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}
		return count;
	}

	public int removeActor(String firstName, String lastName) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(removeActor)) {
			myQry.setString(1,firstName);
			myQry.setString(2,lastName);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("removeActor exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	public int addActor(String firstName, String lastName) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(addActor)) {
			myQry.setString(1,firstName);
			myQry.setString(2,lastName);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertActors exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	public String toJson() {
		String beansContent = "";
		for (actorBean ab : this._actors) {
			beansContent += ab.toJson() + ",";
		}
		
		return jsonHelper
			.toJsonArray("Actors", beansContent);
	}

 	private actorBean buildActor(ResultSet rs) {
		actorBean ab = new actorBean();

		try {
			ab.set_id(rs.getInt("actor_id"));
			ab.set_firstName(rs.getString("actor_first_name"));
			ab.set_lastName(rs.getString("actor_last_name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}
	
 	private void buildActors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._actors.add(buildActor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

 	}

 	

}