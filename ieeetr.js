import Formatter from "./Formatter.js";

export default class IEEE extends Formatter {
    static name = "ieeetr";
    constructor(bibliography) {
        super(bibliography);
    }
    printAuthors(authors) {
        let ret = "";
        for (const author of authors) {
            ret += `${author.firstName[0]}. ${author.lastName}, `;
        }
        return ret.substring(0, ret.length - 2);
    }
    citep(id) {
        return `[${this.bibliography.findIndex(item => item.id === id) + 1}]`;
    }
    article(id) {
        let obj = this.getItem(id);
        let volume = obj.volume ? `, vol. ${obj.volume}` : ``;
        let pages = obj.pages ? `, p. ${obj.pages}` : ``;
        let date = obj.month ? `, ${obj.month} ${obj.year}` : `, ${obj.year}`;
        return `${this.citep(id)} ${this.printAuthors(obj.authors)}, &ldquo;${obj.title},&rdquo; <em>${obj.journal}</em>${volume}${pages}${date}.`;
    }
    inproceedings(id) {
        let obj = this.getItem(id);
        let series = obj.series ? ` of <em>${obj.series}</em>` : ``;
        let volume = obj.volume ? `, vol. ${obj.volume}${series}` : ``;
        let address = obj.address ? `, (${obj.address})` : ``;
        let organization = obj.organization ? `, ${obj.organization}` : ``;
        let date = obj.month ? `, ${obj.month} ${obj.year}` : `, ${obj.year}`;
        let publisher = obj.publisher ? `, ${obj.publisher}` : ``;
        return `${this.citep(id)} ${this.printAuthors(obj.authors)}, &ldquo;${obj.title},&rdquo; in <em>${obj.booktitle}</em>${volume}${address}, p. ${obj.pages}${organization}${publisher}${date}.`;
    }
}