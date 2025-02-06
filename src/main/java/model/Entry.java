package model;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.Value;

public class Entry {
    private BibTeXEntry entry;

    public Entry(BibTeXEntry entry) {
        this.entry = entry;
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

    private String get(Key key) {
        Value value = this.entry.getField(key);
        return value != null ? value.toUserString() : "";
    }
}
