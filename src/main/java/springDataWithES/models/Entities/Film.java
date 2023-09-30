package springDataWithES.models.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.Objects;

@Document(indexName = "film")
public class Film {

    @Id
    private String id;
    private String title;
    private Genre genre;
    private Author director;
    private Date dateOfRelease;

    public Film(String id, String title, Genre genre, Author director, Date dateOfRelease) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.dateOfRelease = dateOfRelease;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getDirector() {
        return director;
    }

    public void setDirector(Author director) {
        this.director = director;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) && Objects.equals(title, film.title) && genre == film.genre && Objects.equals(director, film.director) && Objects.equals(dateOfRelease, film.dateOfRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, director, dateOfRelease);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", director=" + director +
                ", dateOfRelease=" + dateOfRelease +
                '}';
    }
}
