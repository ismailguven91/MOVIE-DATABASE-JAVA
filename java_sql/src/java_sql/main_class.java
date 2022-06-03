package java_sql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.databaseHelper;
import helpers.jsonHelper;
import objectLists.MovieActorView;
import objectLists.MovieGenreView;
import objectLists.actors;
import objectLists.genres;
import objectLists.movies;

public class main_class {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");
		
		ShowAllTables(conn);

		// ***********************************************************

		
		
		// **** ACTORS ***
		
		
		//BELOW WE RENAME AN ACTOR BY FIRSTNAME BY INSERTING THE OLD FIRSTNAME IN FIRST PARAMETER AND THE NEW FIRSTNAME IN SECOND PARAMETER
		//renameActorFirstName(conn, "","");
		
		//BELOW INSERT ACTORS LASTNAME IN FIRST PARAMETER AND NEW LASTNAME IN SECOND PARAMETER
		//renameActorLastName(conn, "","");
		
		//BELOW WE REMOVE ACTOR = ISMAIL GUVEN
		//removeActor(conn, "Ismail", "Guven");
		
		//BELOW WE ADD ACTOR = FIRSTNAME TEST LASTNAME TEST
		//addActor(conn, "ActorFirstName TEST","ActorLastName TEST");
		
		
		
		// ***********************************************************

		
		
		
		// **** MOVIES ***
		
		
		//BELOW WE RENAME A MOVIE BY INSERTING THE OLD TITLE IN FIRST PARAMETER AND THE NEW TITLE IN SECOND PARAMETER
		//renameMovie(conn, "Avatar","Avatar 2");
		
		//BELOW WE CHANGE A MOVIE LENGTH BY INSERTING THE TITLE IN FIRST PARAMETER AND NEW LENGTH IN SECOND PARAMETER
		//updateMovieLength(conn, "Avatar 2",100);
		
		//BELOW WE REMOVE A MOVIE BY INSERTING THE TITLE IN FIRST PARAMTER AND LENGTH IN SECOND PARAMETER
		//removeMovie(conn, "",0);
		
		//BELOW WE ADD A MOVIE BY INSERTING ANY TITLE IN FIRST PARAMETER AND ANY LENGTH IN SECOND PARAMETER
		//addMovie(conn, "",0);
		
		
		
		// ***********************************************************
		
		
		
		// **** GENRES ***
		
		//BELOW WE RENAME A GENRE BY INSERTING THE OLD GENRE IN FIRST PARAMETER AND THE NEW GENRE IN SECOND PARAMETER
		//renameGenre(conn, "","");
		
		//BELOW WE ADD GENRE BY INSERTING ANY GENRE IN THE PARAMETER
		//addGenre(conn, "");
		
		//BELOW WE REMOVE GENRE BY INSERTING A GENRE FROM THE GENRE DATABASE
		//removeGenre(conn, "");
		
		
		ShowAllTables(conn);
		conn.close();
	}
	
	private static void ShowAllTables(Connection conn) {
		actors myActors = new actors(conn);
		movies myMovies = new movies(conn);
		genres myGenres = new genres(conn);
		MovieGenreView myMovieGenreView = new MovieGenreView(conn);
		MovieActorView myMovieActorView = new MovieActorView(conn);
		
		ArrayList<String> document = new ArrayList<String>();
		document.add(myActors.toJson());
		document.add(myMovies.toJson());
		document.add(myGenres.toJson());
		document.add(myMovieGenreView.toJson());
		document.add(myMovieActorView.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);

		System.out.println(jsonDoc);

	}
	
	// FUNCTIONS FOR ACTORS (CHANGE FIRSTNAME, CHANGE LASTNAME, ADD ACTOR, REMOVE ACTOR)
	private static void renameActorFirstName(Connection conn, String firstName, String newFirstName ) {
		actors myActors = new actors(conn);

		int antal = 0;
		
		antal += myActors.updateActorFirstName(firstName, newFirstName);
		System.out.println("Renamed an actor by first name: " + antal);	
		
	}
	
	private static void renameActorLastName(Connection conn, String lastName, String newlasttName ) {
		actors myActors = new actors(conn);

		int antal = 0;
		
		antal += myActors.updateActorFirstName(lastName, newlasttName);
		System.out.println("Renamed an actor by last name: " + antal);	
		
	}
	
	private static void removeActor(Connection conn, String firstName, String lastName) {
		actors myActors = new actors(conn);

		int antal = 0;

		antal += myActors.removeActor(firstName, lastName);
		System.out.println("Actor removed: " + antal);
	}
	
	private static void addActor(Connection conn, String firstName, String lastName) {
		actors myActors = new actors(conn);

		int antal = 0;

		antal += myActors.addActor(firstName, lastName);
		System.out.println("Actor added: " + antal);
	}
	
	
	
	// FUNCTIONS FOR MOVIES (CHANGE TITLE, CHANGE LENGTH, ADD MOVIE, REMOVE MOVIE)
	private static void renameMovie(Connection conn, String title, String newTitle) {
		movies myMovies = new movies(conn);

		int antal = 0;
		
		antal += myMovies.updateMovieTitle(title, newTitle);
		
		System.out.println("Movie uppdaterad: " + antal);		
	}
	
	private static void updateMovieLength(Connection conn, String title, int newLength) {
		movies myMovies = new movies(conn);

		int antal = 0;

		antal += myMovies.updateMovieLength(title, newLength);
		System.out.println("Movie length updated: " + antal);
	}
	
	private static void removeMovie(Connection conn, String title, int lenght) {
		movies myMovies = new movies(conn);

		int antal = 0;

		antal += myMovies.removeMovie(title, lenght);
		System.out.println("Movies removed: " + antal);
	}
	
	private static void addMovie(Connection conn, String title, int lenght) {
		movies myMovies = new movies(conn);

		int antal = 0;

		antal += myMovies.addMovie(title, lenght);
		System.out.println("Movies added: " + antal);
	}
	
	
	
	// FUNCTIONS FOR GENRES (CHANGE NAME OF GENRE, ADD GENRE, REMOVE GENRE)
	private static void renameGenre(Connection conn, String genre, String newGenre) {
		genres myGenres = new genres(conn);

		int antal = 0;
		
		antal += myGenres.updateGenre(genre, newGenre);
		
		System.out.println("Genre uppdaterad: " + antal);		
	}

	private static void removeGenre(Connection conn, String genre) {
		genres myCategories = new genres(conn);

		int antal = 0;

		antal += myCategories.removeGenre(genre);
		System.out.println("A genre has been removed: " + antal);
	}
	
	private static void addGenre(Connection conn, String genre) {
		genres myCategories = new genres(conn);

		int antal = 0;

		antal += myCategories.addGenre(genre);

		System.out.println("A genre has been added: " + antal);
	}

}