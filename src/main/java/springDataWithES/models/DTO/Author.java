package springDataWithES.models.DTO;

import java.util.List;

public class Author extends User {

    public Author(String userId, String name, List<Film> favouriteFilms) {
        super(name, favouriteFilms);
    }

}
