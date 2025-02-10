package model.styles;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.Key;

import model.Entry;
import model.authorFormatter.AuthorFormatter;
import model.citationFormatter.CitationFormatter;

public abstract class FormatterStrategy {
    protected AuthorFormatter authorFormatter;
    protected CitationFormatter citationFormatter;

    protected abstract String article(Entry reference);

    protected abstract String inproceedings(Entry reference);

    public String format(BibTeXDatabase database) {
        var formatted = new StringBuilder();
        for (Key key : database.getEntries().keySet()) {
            var bEntry = database.getEntries().get(key);
            var entry = new Entry(bEntry);
            var listOfAuthors = Entry.processAuthors(entry.getAuthor());
            var authors = authorFormatter.format(listOfAuthors);
            String reference = "";
            try {
                Method method = FormatterStrategy.class.getDeclaredMethod(bEntry.getType().toString(), Entry.class);
                reference = (String) method.invoke(this, new Entry(bEntry));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                System.out.println(ex.getMessage());
            }
            formatted.append(String.format("<p>[%s] %s%s</p>", citationFormatter.format(entry),
                    authors, reference));
        }
        return formatted.toString();
    }
}