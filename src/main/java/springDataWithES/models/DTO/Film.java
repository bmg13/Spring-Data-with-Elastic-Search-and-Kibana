package springDataWithES.models.DTO;

import springDataWithES.models.Entities.Genre;

import java.util.Date;

public record Film (String id, String title, Genre genre, Author director, Date dateOfRelease) {}
