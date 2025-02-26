import LastNameFirstNameAbbrv from "../authorFormatter/LastNameFirstNameAbbrv.js";
import AuthorYearFormatter from "../citationFormatter/AuthorYearFormatter.js";
import FormatterStrategy from "./FormatterStrategy.js";

export default class APA extends FormatterStrategy {
    static name = "apalike";
    constructor() {
        super();
        this.authorFormatter = new LastNameFirstNameAbbrv();
        this.citationFormatter = new AuthorYearFormatter();
    }
    article(obj) {
        let number = obj.number ? `(${obj.number})` : ``;
        let pages = obj.pages ? `:${obj.pages}` : ``;
        let volume = obj.volume ? `, ${obj.volume}${number}${pages}` : ``;
        return ` (${obj.year}). ${obj.title}. <em>${obj.journal}</em>${volume}.`;
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
        return ` (${obj.year}). ${obj.title}. In${editor} <em>${obj.booktitle}</em>${volume}${pages}${address}. ${orgpub}.`;
    }
}