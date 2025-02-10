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
    protected String article(Entry entry) {
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
    protected String inproceedings(Entry entry) {
        var seriesValue = entry.getSeries();
        var volumeValue = entry.getVolume();
        var addressValue = entry.getAddress();
        var organizationValue = entry.getOrganization();
        var pagesValue = entry.getPages();
        var monthValue = entry.getMonth();
        var yearValue = entry.getYear();
        var publisherValue = entry.getPublisher();

        var series = seriesValue.isEmpty() ? "" : " of <em>" + seriesValue + "</em>";
        var volume = volumeValue.isEmpty() ? "" : ", vol. " + volumeValue + series;
        var address = addressValue.isEmpty() ? "" : ", (" + addressValue + ")";
        var organization = organizationValue.isEmpty() ? "" : ", " + organizationValue;
        var date = monthValue.isEmpty() ? ", " + yearValue : ", " + monthValue + " " + yearValue;
        var publisher = publisherValue.isEmpty() ? "" : ", " + publisherValue;
        var temp = String.format(", &ldquo;%s,&rdquo; in <em>%s</em>%s%s, p. %s%s%s%s",
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getBooktitle().replace("{", "").replace("}", ""),
                volume, address, pagesValue, organization, publisher, date);
        return temp;
    }

    @Override
    public String toString() {
        return "ieeetr";
    }
}