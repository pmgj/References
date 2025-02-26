import Entry from "../Entry.js";

export default class AuthorYearFormatter {
    format(entry) {
        let listOfAuthors = Entry.processAuthors(entry.author);
        let citation;
        if (listOfAuthors.length === 1) {
            citation = `${listOfAuthors[0].lastName()}, ${entry.year}`;
        } else if (listOfAuthors.length === 2) {
            citation = `${listOfAuthors[0].lastName()} and ${listOfAuthors[1].lastName()}, ${entry.year}`;
        } else {
            citation = `${listOfAuthors[0].lastName()} et al., ${entry.year}`;
        }
        return `${citation}`;
    }
}
