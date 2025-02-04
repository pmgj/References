package model;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public class ApalikeFormatter extends FormatterStrategy {

    @Override
    protected String format(BibTeXEntry entry) {
        StringBuilder formatted = new StringBuilder();
        System.out.println(entry.getKey());
        System.out.println(entry.getField(new Key("title")).toUserString());
        System.out.println(entry.getField(new Key("author")).toUserString());
        return formatted.toString();
    }

    private String authorFormatter(String authors) {
        StringBuilder formattedAuthors = new StringBuilder();

        String[] authorArray = authors.split(" and ");

        for (int i = 0; i < authorArray.length; i++) {
            String author = authorArray[i];

            String[] parts = author.split(" ");
            String lastName = parts[0];
            String firstName = parts[1];

            String abbreviatedFirstName = firstName.substring(0, 1) + ".";

            if (i > 0) {
                formattedAuthors.append(" & ");
            }

            formattedAuthors.append(lastName).append(", ").append(abbreviatedFirstName);
        }
        return formattedAuthors.toString();
    }
}