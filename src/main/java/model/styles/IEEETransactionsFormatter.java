package model.styles;

import model.Entry;
import model.authorFormatter.FirstNameLastNameAbbrv;
import model.citationFormatter.NumberFormatter;

public class IEEETransactionsFormatter extends FormatterStrategy {

    public IEEETransactionsFormatter() {
        this.authorFormatter = new FirstNameLastNameAbbrv();
        this.citationFormatter = new NumberFormatter();    
    }

    @Override
    protected String format(Entry entry) {
        var volumeValue = entry.getVolume();
        var pagesValue = entry.getPages();
        var monthValue = entry.getMonth();
        var yearValue = entry.getYear();

        var volume = volumeValue.isEmpty() ? "" : ", vol. " + volumeValue;
        var pages = pagesValue.isEmpty() ? "" : ", p." + pagesValue;
        var date = monthValue.isEmpty() ? ", " + yearValue : ", " + monthValue + " " + yearValue;
        var temp = String.format(", &ldquo;%s,&rdquo; <em>%s</em>%s%s%s",
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume, pages, date);
        return temp;
    }

    @Override
    public String toString() {
        return "ieeetr";
    }
}