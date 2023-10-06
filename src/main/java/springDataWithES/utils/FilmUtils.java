package springDataWithES.utils;

import springDataWithES.models.Entities.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmUtils {

    public static List<Film> toList(Iterable<Film> iterable) {
        List<Film> films = new ArrayList<>();
        iterable.iterator().forEachRemaining(films::add);
        return films;
    }
}
