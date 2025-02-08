package model.styles;

import java.util.List;

import model.Author;
import model.Entry;
import model.authorFormatter.AuthorFormatter;
import model.authorFormatter.LastNameFirstNameAbbrv;

public class ApalikeFormatter extends FormatterStrategy {

    private AuthorFormatter authorFormatter = new LastNameFirstNameAbbrv();

    @Override
    protected String format(Entry entry) {
        List<Author> listOfAuthors = this.processAuthors(entry.getAuthor());
        var authors = authorFormatter.format(listOfAuthors);

        var numberValue = entry.getNumber();
        var pagesValue = entry.getPages();
        var volumeValue = entry.getVolume();

        var number = numberValue.isEmpty() ? "" : "(" + numberValue + ")";
        var pages = pagesValue.isEmpty() ? "" : ":" + pagesValue;
        var volume = volumeValue.isEmpty() ? "" : ", " + volumeValue + number + pages;
        var temp = String.format("%s (%s). %s. <i>%s</i>%s", authors, entry.getYear(),
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume);
        return temp;
    }
}