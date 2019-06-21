package newb.c.a_spring.backend.sql.model.basemodel;

import javax.persistence.*;

@Table(name="movie")
@Entity
public class Movie {
    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Integer id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_link")
    private String movieLink;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return movie_name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @param movieName
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * @return movie_link
     */
    public String getMovieLink() {
        return movieLink;
    }

    /**
     * @param movieLink
     */
    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }
    
    
    public Movie(String movieName, String movieLink) {
		super();
		this.movieName = movieName;
		this.movieLink = movieLink;
	}

	public Movie() {
		super();
	}

	@Override
    public String toString() {
    	return "name "+movieName+";link "+movieLink;
    }
}