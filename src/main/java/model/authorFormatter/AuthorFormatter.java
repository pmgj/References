package model.authorFormatter;

import java.util.List;

import model.Author;

public interface AuthorFormatter {
    public abstract String format(List<Author> listOfAuthors);
}
