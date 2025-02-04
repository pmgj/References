package model;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public abstract class FormatterStrategy {
    protected abstract String format(BibTeXEntry reference);

    public String format(BibTeXDatabase database) {
        StringBuilder formatted = new StringBuilder();
        for (Key key : database.getEntries().keySet()) {
            BibTeXEntry entry = database.getEntries().get(key);
            formatted.append(this.format(entry));
            formatted.append("\n");
        }
        return formatted.toString();
    }
}