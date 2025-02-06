package model.authorFormatter;

import java.util.List;

import model.Author;

public abstract class AuthorFormatter {
    public abstract String format(List<Author> listOfAuthors);
}
