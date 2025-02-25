export default class NumberFormatter {
    #index = 1;

    format(entry) {
        return `${this.#index++}`;
    }
}
