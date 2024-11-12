export default class APA {
    authors = [];
    constructor(obj) {
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
    processAuthors() {
        let ret = "";
        for (const author of this.authors) {
            ret += `${author.lastName}, ${author.firstName[0]}., `;
        }
        return ret.substring(0, ret.length - 2);
    }
    processReference(obj) {
        let ret = "[";
        if (this.authors.length === 1) {
            ret += `${this.authors[0].lastName}, ${obj.year}`;
        } else if (this.authors.length === 2) {
            ret += `${this.authors[0].lastName} and ${this.authors[1].lastName}, ${obj.year}`;
        } else {
            ret += `${this.authors[0].lastName} et al., ${obj.year}`;
        }
        ret += "]";
        return ret;
    }
    article(obj) {
        return `${this.processReference(obj)} ${this.processAuthors()} (${obj.year}). ${obj.title}. <em>${obj.journal}<em>, ${obj.volume}(${obj.number}):${obj.pages}.`;
    }
    inproceedings(obj) {
        return `${this.processReference(obj)} ${this.processAuthors()} (${obj.year}). ${obj.title}. In <em>${obj.booktitle}</em>, pages ${obj.pages}. ${obj.publisher}.`;
    }
}