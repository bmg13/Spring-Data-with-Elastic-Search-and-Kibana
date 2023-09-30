package springDataWithES.models.DTO;

import java.util.List;
import java.util.Objects;

public class User {

    private String name;
    private List<Film> favouriteFilms;

    public User(String name, List<Film> favouriteFilms) {
        this.name = name;
        this.favouriteFilms = favouriteFilms;
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
        return Objects.equals(name, user.name) && Objects.equals(favouriteFilms, user.favouriteFilms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, favouriteFilms);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", favouriteFilms=" + favouriteFilms +
                '}';
    }
}
