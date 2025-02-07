package model;

import java.util.ArrayList;
import java.util.List;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

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

    protected List<Author> processAuthors(String authors) {
        List<Author> temp = new ArrayList<>();
        String[] names = authors.split("and");
        for (var element : names) {
            var name = element.trim();
            var parts = name.split(",");
            if (parts.length > 1) {
                var obj = new Author(parts[1].trim(), parts[0].trim());
                temp.add(obj);
            }
        }
        return temp;
    }
}