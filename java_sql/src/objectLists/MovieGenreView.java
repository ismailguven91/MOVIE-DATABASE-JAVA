package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.beanMovieGenre;
import helpers.jsonHelper;

public class MovieGenreView {

	private Connection _connection;
	private ArrayList<beanMovieGenre> _MovieGenreView;

	private String selectAllMoviesAndGenres = "select * from titlegenre";

	public MovieGenreView(Connection cn) {
		this._connection = cn;
		this._MovieGenreView = new ArrayList<beanMovieGenre>();
		getMoviesAndGenres();
	}

	public ArrayList<beanMovieGenre> getMoviesAndGenres() {
		if (this._MovieGenreView.size() > 0)
			return this._MovieGenreView;

		this._MovieGenreView = new ArrayList<beanMovieGenre>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectAllMoviesAndGenres)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovieGenreView exception for statement");
			e.printStackTrace();
		}

		return this._MovieGenreView;
	}

	public String toJson() {
		String beansContent = "";
		for (beanMovieGenre ab : this._MovieGenreView) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("MoviesAndGenres", beansContent);
	}

	private beanMovieGenre buildMovieGenre(ResultSet rs) {
		beanMovieGenre ab = new beanMovieGenre();

		try {
			ab.setMovieTite(rs.getString("title"));
			ab.setMovieGenre(rs.getString("genre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildMovieGenres(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._MovieGenreView.add(buildMovieGenre(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovieGenres(rs);
		} catch (SQLException e) {
			System.out.println("getMoviesGenres exception for result set");
			e.printStackTrace();
		}

	}

	
}