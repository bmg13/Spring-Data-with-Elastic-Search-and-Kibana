package springDataWithES.utils;

import springDataWithES.models.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {

    public static List<User> toList(Iterable<User> iterable) {
        List<User> users = new ArrayList<>();
        iterable.iterator().forEachRemaining(users::add);
        return users;
    }

    public static List<User> toList(List<User> list) {
        return new ArrayList<>(list);
    }
}
