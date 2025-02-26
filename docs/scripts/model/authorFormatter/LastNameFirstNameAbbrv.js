export default class LastNameFirstNameAbbrv {
    format(listOfAuthors) {
        let ret = "";
        for (let author of listOfAuthors) {
            ret += author.lastName() + ", ";
            let abbrv = author.firstName().split(" ");
            for (let n of abbrv) {
                ret += n.charAt(0) + ". ";
            }
            ret = ret.substring(0, ret.length - 1) + ", ";
        }
        return ret.substring(0, ret.length - 2);
    }
}
