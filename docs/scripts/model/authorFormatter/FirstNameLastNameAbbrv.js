export default class FirstNameLastNameAbbrv {
    format(listOfAuthors) {
        let ret = "";
        for (let author of listOfAuthors) {
            let abbrv = author.firstName().split(" ");
            for (let n of abbrv) {
                ret += n.charAt(0) + ". ";
            }
            ret += author.lastName() + ", ";
        }
        return ret.substring(0, ret.length() - 2);
    }
}
