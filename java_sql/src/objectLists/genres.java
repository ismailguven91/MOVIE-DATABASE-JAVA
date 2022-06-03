package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.genreBean;
import helpers.jsonHelper;

public class genres {

	private Connection _connection;
	private ArrayList<genreBean> _genres;

	private String selectAllGenres = "select * from genre ORDER BY genre_id ASC";
	private String updateGenre = "UPDATE genre SET genre = ? WHERE genre = ?;";
	private String removeGenre = "DELETE FROM genre WHERE genre = ?;";
	private String addGenre = "insert into genre (genre) VALUES (?);";

	public genres(Connection cn) {
		this._connection = cn;
		this._genres = new ArrayList<genreBean>();
		getGenres();
	}

	public ArrayList<genreBean> getGenres() {
		if (this._genres.size() > 0)
			return this._genres;

		this._genres = new ArrayList<genreBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectAllGenres)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}

		return this._genres;
	}

	public int updateGenre(String newGenre, String Genre) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateGenre)) {
			myQry.setString(1, newGenre);
			myQry.setString(2, Genre);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateGenres exception for statement");
			e.printStackTrace();
		}
		return count;
	}

	public int removeGenre(String Genre) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(removeGenre)) {
			myQry.setString(1, Genre);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("removeGenre exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	public int addGenre(String Genre) {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(addGenre)) {
			myQry.setString(1, Genre);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addGenres exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	
	
	public String toJson() {
		String beansContent = "";
		for (genreBean ab : this._genres) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Genres", beansContent);
	}

	private genreBean buildGenre(ResultSet rs) {
		genreBean ab = new genreBean();

		try {
			ab.set_genreID(rs.getInt("genre_id"));
			ab.set_genre(rs.getString("genre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildGenres(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._genres.add(buildGenre(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildGenres(rs);
		} catch (SQLException e) {
			System.out.println("getGenres exception for result set");
			e.printStackTrace();
		}

	}
	
}