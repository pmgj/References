import Formatter from "./Formatter.js";

export default class APA extends Formatter {
    authors = [];
    name = "apalike";
    constructor() {
        super();
    }
    printAuthors() {
        let ret = "";
        for (const author of this.authors) {
            ret += `${author.lastName}, ${author.firstName[0]}., `;
        }
        return ret.substring(0, ret.length - 2);
    }
    citep(obj) {
        let ret = "(";
        if (this.authors.length === 1) {
            ret += `${this.authors[0].lastName}, ${obj.year}`;
        } else if (this.authors.length === 2) {
            ret += `${this.authors[0].lastName} and ${this.authors[1].lastName}, ${obj.year}`;
        } else {
            ret += `${this.authors[0].lastName} et al., ${obj.year}`;
        }
        ret += ")";
        return ret;
    }
    article(obj) {
        return `${this.printAuthors()} (${obj.year}). ${obj.title}. <em>${obj.journal}<em>, ${obj.volume}(${obj.number}):${obj.pages}.`;
    }
    inproceedings(obj) {
        return `${this.printAuthors()} (${obj.year}). ${obj.title}. In <em>${obj.booktitle}</em>, pages ${obj.pages}. ${obj.publisher}.`;
    }
}