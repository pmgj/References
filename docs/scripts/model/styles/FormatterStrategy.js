import Entry from "../Entry.js";

export default class FormatterStrategy {
    static name = "";
    authorFormatter;
    citationFormatter;
    format(database) {
        let formatted = "";
        for (let key of database.getEntries().keySet()) {
            let bEntry = database.getEntries().get(key);
            let entry = new Entry(bEntry);
            var listOfAuthors = Entry.processAuthors(entry.getAuthor());
            var authors = authorFormatter.format(listOfAuthors);
            let reference = "";
            try {
                reference = this[bEntry.getType().toString()](new Entry(bEntry));
            } catch (ex) {
                System.out.println(ex.getMessage());
            }
            formatted += `<p>[${citationFormatter.format(entry)}] ${authors}${reference}</p>`;
        }
        return formatted;
    }
}