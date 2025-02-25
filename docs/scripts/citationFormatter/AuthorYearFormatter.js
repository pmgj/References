import Entry from "../Entry.js";

export default class AuthorYearFormatter {
    format(entry) {
        let listOfAuthors = Entry.processAuthors(entry.getAuthor());
        let citation;
        if (listOfAuthors.size() === 1) {
            citation = `${listOfAuthors.get(0).lastName()}, ${entry.getYear()}`;
        } else if (listOfAuthors.size() === 2) {
            citation = `${listOfAuthors.get(0).lastName()} and ${listOfAuthors.get(1).lastName()}, ${entry.getYear()}`;
        } else {
            citation = `${listOfAuthors.get(0).lastName()} et al., ${entry.getYear()}`;
        }
        return `${citation}`;
    }
}
