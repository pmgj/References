import Formatter from "./Formatter.js";

export default class IEEE extends Formatter {
    name = "ieeetr";
    constructor() {
        super();
    }
    printAuthors() {
        let ret = "";
        for (const author of this.authors) {
            ret += `${author.firstName[0]}. ${author.lastName}, `;
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
        return `${this.printAuthors()}, &ldquo;${obj.title},&rdquo; <em>${obj.journal}</em>, vol. ${obj.volume}, p. ${obj.pages}, ${obj.year}.`;
    }
    inproceedings(obj) {
        let series = obj.series ? ` of <em>${obj.series}</em>` : ``;
        let volume = obj.volume ? `, vol. ${obj.volume}${series}` : ``;
        let address = obj.address ? `, (${obj.address})` : ``;
        let organization = obj.organization ? `, ${obj.organization}` : ``;
        let date = obj.month ? `, ${obj.month} ${obj.year}` : `, ${obj.year}`;
        let publisher = obj.publisher ? `, ${obj.publisher}` : ``;
        return `${this.printAuthors()}, &ldquo;${obj.title},&rdquo; in <em>${obj.booktitle}</em>${volume}${address}, p. ${obj.pages}${organization}${publisher}${date}.`;
    }
}