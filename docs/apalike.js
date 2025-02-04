import Formatter from "./Formatter.js";

export default class APA extends Formatter {
    static name = "apalike";
    constructor(bibliography) {
        super(bibliography);
    }
    printAuthors(authors) {
        let ret = "";
        for (const author of authors) {
            ret += `${author.lastName}, ${author.firstName[0]}., `;
        }
        return ret.substring(0, ret.length - 2);
    }
    citep(id) {
        let obj = this.getItem(id);
        let ret = "[";
        if (obj.authors.length === 1) {
            ret += `${obj.authors[0].lastName}, ${obj.year}`;
        } else if (obj.authors.length === 2) {
            ret += `${obj.authors[0].lastName} and ${obj.authors[1].lastName}, ${obj.year}`;
        } else {
            ret += `${obj.authors[0].lastName} et al., ${obj.year}`;
        }
        ret += "]";
        return ret;
    }
    article(id) {
        let obj = this.getItem(id);
        let number = obj.number ? `(${obj.number})` : ``;
        let pages = obj.pages ? `:${obj.pages}` : ``;
        let volume = obj.volume ? `, ${obj.volume}${number}${pages}` : ``;
        return `${this.citep(id)} ${this.printAuthors(obj.authors)} (${obj.year}). ${obj.title}. <em>${obj.journal}<em>${volume}.`;
    }
    inproceedings(id) {
        let obj = this.getItem(id);
        let editor = obj.editor ? ` ${obj.editor}, editor,` : ``;
        let series = obj.series ? ` of ${obj.series}, ` : ``;
        let volume = obj.volume ? ` volume ${obj.volume}${series}` : ``;
        let pages = obj.pages ? `, pages ${obj.pages}` : ``;
        let address = obj.address ? `, ${obj.address}` : ``;
        let publisher = obj.publisher ? `${obj.publisher}` : ``;
        let organization = obj.organization ? `${obj.organization}` : ``;
        let orgpub = organization && publisher ? `${organization}, ${publisher}` : organization ? `${organization}` : publisher ? `${publisher}` : ``;
        return `${this.citep(id)} ${this.printAuthors(obj.authors)} (${obj.year}). ${obj.title}. In${editor} <em>${obj.booktitle}</em>${volume}${pages}${address}. ${orgpub}.`;
    }
}