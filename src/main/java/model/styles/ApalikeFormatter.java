package model.styles;

import model.Entry;
import model.authorFormatter.LastNameFirstNameAbbrv;
import model.citationFormatter.AuthorYearFormatter;

public class ApalikeFormatter extends FormatterStrategy {

    public ApalikeFormatter() {
        this.authorFormatter = new LastNameFirstNameAbbrv();
        this.citationFormatter = new AuthorYearFormatter();    
    }

    @Override
    protected String format(Entry entry) {
        var numberValue = entry.getNumber();
        var pagesValue = entry.getPages();
        var volumeValue = entry.getVolume();

        var number = numberValue.isEmpty() ? "" : "(" + numberValue + ")";
        var pages = pagesValue.isEmpty() ? "" : ":" + pagesValue;
        var volume = volumeValue.isEmpty() ? "" : ", " + volumeValue + number + pages;
        var temp = String.format("(%s). %s. <i>%s</i>%s", entry.getYear(),
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume);
        return temp;
    }

    @Override
    public String toString() {
        return "apalike";
    }
}