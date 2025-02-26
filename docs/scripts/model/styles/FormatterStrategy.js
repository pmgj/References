import Entry from "../Entry.js";

export default class FormatterStrategy {
    static name = "";
    authorFormatter;
    citationFormatter;
    format(database) {
        let formatted = "";
        for (let entry of database) {
            var listOfAuthors = Entry.processAuthors(entry.author);
            var authors = this.authorFormatter.format(listOfAuthors);
            let reference = "";
            try {
                reference = this[entry.type](entry);
            } catch (ex) {
                console.log(ex.message);
            }
            formatted += `<p>[${this.citationFormatter.format(entry)}] ${authors}${reference}</p>`;
        }
        return formatted;
    }
}