package springDataWithES.models.DTO;

import java.util.List;

public class Author extends User {

    public Author() {super();}

    public Author(String id, String name, List<Film> favouriteFilms) {
        super(id, name, favouriteFilms);
    }

    public Author(String name) {
        super(name);
    }
}
