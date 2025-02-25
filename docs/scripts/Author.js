export default class Author {
    #firstName;
    #lastName;
    constructor(firstName, lastName) {
        this.#firstName = firstName;
        this.#lastName = lastName;
    }
    firstName() {
        return this.#firstName;
    }
    lastName() {
        return this.#lastName;
    }
}