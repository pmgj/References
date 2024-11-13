import Formatter from "./Formatter.js";

export default class APA extends Formatter {
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
        let number = obj.number ? `(${obj.number})` : ``;
        let pages = obj.pages ? `:${obj.pages}` : ``;
        let volume = obj.volume ? `, ${obj.volume}${number}${pages}` : ``;
        return `${this.printAuthors()} (${obj.year}). ${obj.title}. <em>${obj.journal}<em>${volume}.`;
    }
    inproceedings(obj) {
        let editor = obj.editor ? ` ${obj.editor}, editor,` : ``;
        let series = obj.series ? ` of ${obj.series}, ` : ``;
        let volume = obj.volume ? ` volume ${obj.volume}${series}` : ``;
        let pages = obj.pages ? `, pages ${obj.pages}` : ``;
        let address = obj.address ? `, ${obj.address}` : ``;
        let publisher = obj.publisher ? `${obj.publisher}` : ``;
        let organization = obj.organization ? `${obj.organization}` : ``;
        let orgpub = organization && publisher ? `${organization}, ${publisher}` : organization ? `${organization}` : publisher ? `${publisher}` : ``;
        return `${this.printAuthors()} (${obj.year}). ${obj.title}. In${editor} <em>${obj.booktitle}</em>${volume}${pages}${address}. ${orgpub}.`;
    }
}