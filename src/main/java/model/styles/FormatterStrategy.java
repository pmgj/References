package model.styles;

import java.util.List;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import model.Author;
import model.Entry;
import model.authorFormatter.AuthorFormatter;
import model.citationFormatter.CitationFormatter;

public abstract class FormatterStrategy {
    protected AuthorFormatter authorFormatter;
    protected CitationFormatter citationFormatter;

    protected abstract String format(Entry reference);

    public String format(BibTeXDatabase database) {
        StringBuilder formatted = new StringBuilder();
        for (Key key : database.getEntries().keySet()) {
            BibTeXEntry bEntry = database.getEntries().get(key);
            var entry = new Entry(bEntry);
            List<Author> listOfAuthors = Entry.processAuthors(entry.getAuthor());
            var authors = authorFormatter.format(listOfAuthors);
            formatted.append(String.format("<p>%s %s %s</p>", citationFormatter.format(entry),
                    authors, this.format(new Entry(bEntry))));
        }
        return formatted.toString();
    }
}