import APA from "../model/styles/ApalikeFormatter.js";
import IEEE from "../model/styles/IEEETransactionsFormatter.js";

class GUI {
    STYLES = [APA, IEEE];
    select = document.querySelector("#style");
    database;
    cache = new Map();
    outputArea = document.querySelector("output");
    convert() {
        let textarea = document.querySelector("textarea");
        this.outputArea.innerHTML = "";
        this.database = BibtexParser.parseToJSON(textarea.value);
        let index = parseInt(this.select.value);
        let style = new this.STYLES[index](this.database);
        this.generateFormattedReferences();
        this.refreshDisplay();
        for (const element of this.database) {
            let li = document.createElement("li");
            ul.appendChild(li);
            li.innerHTML = style[element.type](element.id);
        }
    }
    generateFormattedReferences() {
        this.cache.clear();
        for (let style of this.STYLES) {
            let reference = style.format(this.database);
            this.cache.put(style.toString(), reference);
        }
    }
    refreshDisplay() {
        if (this.database == null) {
            outputArea.setText("Nenhuma referência carregada.");
            return;
        }
        this.outputArea.textContent = "";
        let index = parseInt(this.select.value);
        let selectedStyle = new this.STYLES[index](this.database);
        let text = cache.get(selectedStyle.toString());
        outputArea.setText(`${text}`);
    }
    populateStyles() {
        for (let i = 0; i < this.STYLES.length; i++) {
            const s = this.STYLES[i];
            this.select.add(new Option(s.name, i));
        }
    }
    registerEvents() {
        let button = document.querySelector("input[type='button']");
        button.onclick = this.convert.bind(this);
        this.populateStyles();
    };
}
let gui = new GUI();
gui.registerEvents();