package model.styles;

import java.util.List;

import model.Author;
import model.Entry;
import model.authorFormatter.AuthorFormatter;
import model.authorFormatter.LastNameFirstNameAbbrv;
import model.citationFormatter.AuthorYearFormatter;
import model.citationFormatter.CitationFormatter;

public class ApalikeFormatter extends FormatterStrategy {

    private AuthorFormatter authorFormatter = new LastNameFirstNameAbbrv();
    private CitationFormatter citationFormatter = new AuthorYearFormatter();

    @Override
    protected String format(Entry entry) {
        List<Author> listOfAuthors = Entry.processAuthors(entry.getAuthor());
        var authors = authorFormatter.format(listOfAuthors);

        var numberValue = entry.getNumber();
        var pagesValue = entry.getPages();
        var volumeValue = entry.getVolume();

        var number = numberValue.isEmpty() ? "" : "(" + numberValue + ")";
        var pages = pagesValue.isEmpty() ? "" : ":" + pagesValue;
        var volume = volumeValue.isEmpty() ? "" : ", " + volumeValue + number + pages;
        var temp = String.format("%s %s (%s). %s. <i>%s</i>%s", citationFormatter.format(entry), authors, entry.getYear(),
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume);
        return temp;
    }

    @Override
    public String toString() {
        return "apalike";
    }
}