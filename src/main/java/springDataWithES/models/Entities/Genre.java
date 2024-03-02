package springDataWithES.models.Entities;

public enum Genre {

    DRAMA("DRAMA"),
    COMEDY("COMEDY"),
    THRILLER("THRILLER"),
    BIOGRAPHY("BIOGRAPHY"),
    FANTASY("FANTASY"),
    ACTION("ACTION"),
    SCI_FI("SCI_FI"),
    OTHER("OTHER");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }
}
