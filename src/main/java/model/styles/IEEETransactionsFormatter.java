package model.styles;

import java.util.List;

import model.Author;
import model.Entry;
import model.authorFormatter.AuthorFormatter;
import model.authorFormatter.FirstNameLastNameAbbrv;

public class IEEETransactionsFormatter extends FormatterStrategy {

    private AuthorFormatter authorFormatter = new FirstNameLastNameAbbrv();

    @Override
    protected String format(Entry entry) {
        List<Author> listOfAuthors = this.processAuthors(entry.getAuthor());
        var authors = authorFormatter.format(listOfAuthors);

        var volumeValue = entry.getVolume();
        var pagesValue = entry.getPages();
        var monthValue = entry.getMonth();
        var yearValue = entry.getYear();

        var volume = volumeValue.isEmpty() ? "" : ", vol. " + volumeValue;
        var pages = pagesValue.isEmpty() ? "" : ", p." + pagesValue;
        var date = monthValue.isEmpty() ? ", " + yearValue : ", " + monthValue + " " + yearValue;
        var temp = String.format("%s, &ldquo;%s,&rdquo; <em>%s</em>%s%s%s", authors,
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume, pages, date);
        return temp;
    }

    @Override
    public String toString() {
        return "ieeetr";
    }
}