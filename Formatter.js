export default class Formatter {
    constructor() {
        this.name = "";
        this.authors = [];
    }
    processAuthors(obj) {
        let as = obj.author;
        let names = as.split("and");
        for (const element of names) {
            let name = element.trim();
            let parts = name.split(",");
            if (parts.length > 1) {
                let obj = { firstName: parts[1].trim(), lastName: parts[0].trim() };
                this.authors.push(obj);
            }
        }
    }
    toString() {
        return this.name;
    }    
}