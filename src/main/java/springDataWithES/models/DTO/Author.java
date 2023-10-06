package springDataWithES.models.DTO;

import java.util.List;

public class Author extends User {

    public Author() {super();}

    public Author(String name, List<Film> favouriteFilms) {
        super(name, favouriteFilms);
    }

    public Author(String name) {
        super(name);
    }
}
