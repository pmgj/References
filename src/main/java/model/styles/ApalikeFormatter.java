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
    protected String article(Entry entry) {
        var numberValue = entry.getNumber();
        var pagesValue = entry.getPages();
        var volumeValue = entry.getVolume();

        var number = numberValue.isEmpty() ? "" : "(" + numberValue + ")";
        var pages = pagesValue.isEmpty() ? "" : ":" + pagesValue;
        var volume = volumeValue.isEmpty() ? "" : ", " + volumeValue + number + pages;
        var temp = String.format(" (%s). %s. <i>%s</i>%s", entry.getYear(),
                entry.getTitle().replace("{", "").replace("}", ""),
                entry.getJournal(), volume);
        return temp;
    }

    @Override
    protected String inproceedings(Entry entry) {
        var editorValue = entry.getEditor();
        var seriesValue = entry.getSeries();
        var volumeValue = entry.getVolume();
        var pagesValue = entry.getPages();
        var addressValue = entry.getAddress();
        var publisherValue = entry.getPublisher();
        var organizationValue = entry.getOrganization();

        var editor = editorValue.isEmpty() ? "" : " " + editorValue + ", editor,";
        var series = seriesValue.isEmpty() ? "" : " of " + seriesValue + ", ";
        var volume = volumeValue.isEmpty() ? "" : " volume " + volumeValue + series;
        var pages = pagesValue.isEmpty() ? "" : ", pages " + pagesValue;
        var address = addressValue.isEmpty() ? "" : ", " + addressValue;
        var publisher = publisherValue.isEmpty() ? "" : publisherValue;
        var organization = organizationValue.isEmpty() ? "" : organizationValue;
        var orgpub = !organization.isEmpty() && !publisher.isEmpty() ? organization + ", " + publisher : !organization.isEmpty() ? organization : !publisher.isEmpty() ? publisher : "";
        var temp = String.format(" (%s). %s. In%s <em>%s</em>%s%s%s. %s", entry.getYear(),
                entry.getTitle().replace("{", "").replace("}", ""),
                editor, entry.getBooktitle().replace("{", "").replace("}", ""), volume, pages, address, orgpub);
        return temp;
    }

    @Override
    public String toString() {
        return "apalike";
    }
}