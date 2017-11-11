package newb.c.backend.elasticsearch.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "wow", type = "esmovie")
public class MovieDTO {
	
	@Field(type = FieldType.String)
    private Integer id;
	
	@Field(type = FieldType.String)
    private String movieName;
	
	@Field(type = FieldType.String)
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
    
    
    public MovieDTO(String movieName, String movieLink) {
		super();
		this.movieName = movieName;
		this.movieLink = movieLink;
	}

	public MovieDTO() {
		super();
	}

	@Override
    public String toString() {
    	return "name "+movieName+";link "+movieLink;
    }
}