package model.citationFormatter;

import java.util.List;

import model.Author;
import model.Entry;

public class AuthorYearFormatter implements CitationFormatter {
    @Override
    public String format(Entry entry) {
                List<Author> listOfAuthors = Entry.processAuthors(entry.getAuthor());

                String citation;
        if (listOfAuthors.size() == 1) {
            citation = String.format("%s, %s", listOfAuthors.get(0).lastName(), entry.getYear());
        } else if (listOfAuthors.size() == 2) {
            citation = String.format("%s and %s, %s", listOfAuthors.get(0).lastName(), listOfAuthors.get(1).lastName(), entry.getYear());
        } else {
            citation =  String.format("%s et al., %s", listOfAuthors.get(0).lastName(), entry.getYear());
        }
        return String.format("%s", citation);
    }
}
