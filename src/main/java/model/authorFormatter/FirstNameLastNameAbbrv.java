package model.authorFormatter;

import java.util.List;

import model.Author;

public class FirstNameLastNameAbbrv implements AuthorFormatter {
    public String format(List<Author> listOfAuthors) {
        var ret = "";
        for (var author : listOfAuthors) {
            var abbrv = author.firstName().split(" ");
            for (String n : abbrv) {
                ret += n.charAt(0) + ". ";
            }
            ret += author.lastName() + ", ";
            // ret = ret.substring(0, ret.length() - 1) + ", ";
        }
        return ret.substring(0, ret.length() - 2);
    }
}
