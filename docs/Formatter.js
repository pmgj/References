export default class Formatter {
    static name = "";
    constructor(bibliography) {
        this.bibliography = bibliography;
        this.processAuthors();
    }
    getItem(id) {
        return this.bibliography.find(item => item.id === id);
    }
    processAuthors() {
        for (const item of this.bibliography) {
            item.authors = [];
            let as = item.author;
            let names = as.split("and");
            for (const element of names) {
                let name = element.trim();
                let parts = name.split(",");
                if (parts.length > 1) {
                    let obj = { firstName: parts[1].trim(), lastName: parts[0].trim() };
                    item.authors.push(obj);
                }
            }
        }
    }
}