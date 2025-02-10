package model.citationFormatter;

import model.Entry;

public class NumberFormatter implements CitationFormatter {
    private int index = 1;

    @Override
    public String format(Entry entry) {
        return String.format("%d", index++);
    }
}
