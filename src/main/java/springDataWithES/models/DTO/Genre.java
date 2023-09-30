package springDataWithES.models.DTO;

public enum Genre {

    DRAMA("DRAMA"),
    COMEDY("COMEDY"),
    THRILLER("THRILLER"),
    BIOGRAPHY("BIOGRAPHY"),
    OTHER("OTHER");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }
}
