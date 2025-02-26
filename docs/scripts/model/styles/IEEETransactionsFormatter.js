import FormatterStrategy from "./FormatterStrategy.js";
import FirstNameLastNameAbbrv from "../authorFormatter/FirstNameLastNameAbbrv.js";
import NumberFormatter from "../citationFormatter/NumberFormatter.js";

export default class IEEE extends FormatterStrategy {
    constructor() {
        super();
        this.authorFormatter = new FirstNameLastNameAbbrv();
        this.citationFormatter = new NumberFormatter();
    }
    article(obj) {
        let volume = obj.volume ? `, vol. ${obj.volume}` : ``;
        let pages = obj.pages ? `, p. ${obj.pages}` : ``;
        let date = obj.month ? `, ${obj.month} ${obj.year}` : `, ${obj.year}`;
        return `, &ldquo;${obj.title},&rdquo; <em>${obj.journal}</em>${volume}${pages}${date}.`;
    }
    inproceedings(obj) {
        let series = obj.series ? ` of <em>${obj.series}</em>` : ``;
        let volume = obj.volume ? `, vol. ${obj.volume}${series}` : ``;
        let address = obj.address ? `, (${obj.address})` : ``;
        let organization = obj.organization ? `, ${obj.organization}` : ``;
        let date = obj.month ? `, ${obj.month} ${obj.year}` : `, ${obj.year}`;
        let publisher = obj.publisher ? `, ${obj.publisher}` : ``;
        return `, &ldquo;${obj.title},&rdquo; in <em>${obj.booktitle}</em>${volume}${address}, p. ${obj.pages}${organization}${publisher}${date}.`;
    }
    toString() {
        return "ieeetr";
    }
}