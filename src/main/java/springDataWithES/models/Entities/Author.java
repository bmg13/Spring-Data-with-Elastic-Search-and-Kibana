package springDataWithES.models.Entities;

import java.util.List;

public class Author extends User {

    public Author(String userId, String name, List<Film> favouriteFilms) {
        super(userId, name, favouriteFilms);
    }

}