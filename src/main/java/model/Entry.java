package model;

import java.util.ArrayList;
import java.util.List;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.Value;

public class Entry {
    private BibTeXEntry entry;

    public Entry(BibTeXEntry entry) {
        this.entry = entry;
    }

    public String getAddress() {
        return this.get(BibTeXEntry.KEY_ADDRESS);
    }

    public String getPublisher() {
        return this.get(BibTeXEntry.KEY_PUBLISHER);
    }

    public String getOrganization() {
        return this.get(BibTeXEntry.KEY_ORGANIZATION);
    }

    public String getBooktitle() {
        return this.get(BibTeXEntry.KEY_BOOKTITLE);
    }

    public String getTitle() {
        return this.get(BibTeXEntry.KEY_TITLE);
    }

    public String getYear() {
        return this.get(BibTeXEntry.KEY_YEAR);
    }

    public String getNumber() {
        return this.get(BibTeXEntry.KEY_NUMBER);
    }

    public String getPages() {
        return this.get(BibTeXEntry.KEY_PAGES);
    }

    public String getVolume() {
        return this.get(BibTeXEntry.KEY_VOLUME);
    }

    public String getAuthor() {
        return this.get(BibTeXEntry.KEY_AUTHOR);
    }

    public String getJournal() {
        return this.get(BibTeXEntry.KEY_JOURNAL);
    }

    public String getMonth() {
        return this.get(BibTeXEntry.KEY_MONTH);
    }

    public String getEditor() {
        return this.get(BibTeXEntry.KEY_EDITOR);
    }

    public String getSeries() {
        return this.get(BibTeXEntry.KEY_SERIES);
    }

    private String get(Key key) {
        Value value = this.entry.getField(key);
        return value != null ? value.toUserString() : "";
    }

    public static List<Author> processAuthors(String authors) {
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
