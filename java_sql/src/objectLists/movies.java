package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.movieBean;
import helpers.jsonHelper;

public class movies {

	private Connection _connection;
	private ArrayList<movieBean> _movies;

	private String selectAllMovies = "select * from movie ORDER BY movie_id ASC";
	private String updateMovieTitle = "UPDATE movie SET title = ? WHERE title = ?;";
	private String updateMovieLength = "UPDATE movie SET lenght = ? WHERE title = ?;";
	private String addMovie = "insert into movie (title, lenght) VALUES (?,?);";
	private String removeMovie = "DELETE FROM movie WHERE title = ? AND lenght = ?;";


	public movies(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<movieBean>();
		getMovies();
	}

	public ArrayList<movieBean> getMovies() {
		if (this._movies.size() > 0)
			return this._movies;

		this._movies = new ArrayList<movieBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectAllMovies)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}

		return this._movies;
	}

	public int updateMovieTitle(String newTitle, String Title) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateMovieTitle)) {
			myQry.setString(1, newTitle);
			myQry.setString(2, Title);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}
		return count;
	}

	
	public int updateMovieLength(String title, int newLength) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateMovieLength)) {
			myQry.setString(2, title);
			myQry.setDouble(1, newLength);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	public int addMovie(String title, int lenght) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(addMovie)) {
			myQry.setString(1, title);
			myQry.setDouble(2, lenght);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addMovie exception for statement");
			e.printStackTrace();
		}
		return count;
	}

	public int removeMovie(String title, int lenght) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(removeMovie)) {
			myQry.setString(1, title);
			myQry.setDouble(2, lenght);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("removeMovie exception for statement");
			e.printStackTrace();
		}
		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (movieBean ab : this._movies) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Movies ", beansContent);
	}

	private movieBean buildMovie(ResultSet rs) {
		movieBean ab = new movieBean();

		try {
			ab.set_movieID(rs.getInt("movie_id"));
			ab.set_movieTitle(rs.getString("title"));
			ab.set_movieLength(rs.getInt("lenght"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildMovies(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._movies.add(buildMovie(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

	}


}