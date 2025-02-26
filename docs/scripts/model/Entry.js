import Author from "./Author.js";

export default class Entry {
    static processAuthors(authors) {
        let temp = [];
        let names = authors.split("and");
        for (let element of names) {
            var name = element.trim();
            var parts = name.split(",");
            if (parts.length > 1) {
                var obj = new Author(parts[1].trim(), parts[0].trim());
                temp.push(obj);
            }
        }
        return temp;
    }
}