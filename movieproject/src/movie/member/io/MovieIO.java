package movie.member.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import movie.memeber.model.vo.Movie;

public class MovieIO {

		private File movieFile = new File("MovieFile.ser");
		
		public List<Movie> loadmovieList(){

			try (ObjectInputStream ois = new ObjectInputStream (new BufferedInputStream(new FileInputStream(movieFile)));
					){
				return (List<Movie>)ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null; 
		}
		
		public void saveMovieList(List<Movie> movieList) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(movieFile)))) {
				oos.writeObject(movieList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


