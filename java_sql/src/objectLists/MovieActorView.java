package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.beanTitleActor;
import helpers.jsonHelper;

public class MovieActorView {

	private Connection _connection;
	private ArrayList<beanTitleActor> _MovieActorView;

	private String selectMoviesActors = "select * from titleactor";

	public MovieActorView(Connection cn) {
		this._connection = cn;
		this._MovieActorView = new ArrayList<beanTitleActor>();
		getMoviesActors();
	}

	public ArrayList<beanTitleActor> getMoviesActors() {
		if (this._MovieActorView.size() > 0)
			return this._MovieActorView;

		this._MovieActorView = new ArrayList<beanTitleActor>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectMoviesActors)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovieActorView exception for statement");
			e.printStackTrace();
		}

		return this._MovieActorView;
	}

	public String toJson() {
		String beansContent = "";
		for (beanTitleActor ab : this._MovieActorView) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("MoviesActors", beansContent);
	}

	private beanTitleActor buildMovieActor(ResultSet rs) {
		beanTitleActor ab = new beanTitleActor();

		try {
			ab.set_movieTitle(rs.getString("title"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildMoviesActors(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._MovieActorView.add(buildMovieActor(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMoviesActors(rs);
		} catch (SQLException e) {
			System.out.println("getFilmsAndActors exception for result set");
			e.printStackTrace();
		}

	}

	
}