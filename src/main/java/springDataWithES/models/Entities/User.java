package springDataWithES.models.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Objects;

@Document(indexName = "user")
public class User {

    @Id
    private String id;
    private String name;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Film> favouriteFilms;

    public User() {}

    public User(String id, String name, List<Film> favouriteFilms) {
        this.id = id;
        this.name = name;
        this.favouriteFilms = favouriteFilms;
    }

    public User(String name, List<Film> favouriteFilms) {
        this.name = name;
        this.favouriteFilms = favouriteFilms;
    }

    public User(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFavouriteFilms() {
        return favouriteFilms;
    }

    public void setFavouriteFilms(List<Film> favouriteFilms) {
        this.favouriteFilms = favouriteFilms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(favouriteFilms, user.favouriteFilms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, favouriteFilms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", favouriteFilms=" + favouriteFilms +
                '}';
    }
}
