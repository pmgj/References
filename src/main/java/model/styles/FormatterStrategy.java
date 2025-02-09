package model.styles;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import model.Entry;

public abstract class FormatterStrategy {
    protected abstract String format(Entry reference);

    public String format(BibTeXDatabase database) {
        StringBuilder formatted = new StringBuilder();
        for (Key key : database.getEntries().keySet()) {
            BibTeXEntry entry = database.getEntries().get(key);
            formatted.append(String.format("<p>%s</p>", this.format(new Entry(entry))));
        }
        return formatted.toString();
    }
}