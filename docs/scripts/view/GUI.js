import APA from "../model/styles/ApalikeFormatter.js";
import IEEE from "../model/styles/IEEETransactionsFormatter.js";

class GUI {
    STYLES = [new APA(), new IEEE()];
    select = document.querySelector("#style");
    database;
    cache = new Map();
    outputArea = document.querySelector("output");
    convert() {
        let textarea = document.querySelector("textarea");
        this.outputArea.innerHTML = "";
        this.database = BibtexParser.parseToJSON(textarea.value);
        this.generateFormattedReferences();
        this.refreshDisplay();
    }
    generateFormattedReferences() {
        this.cache.clear();
        for (let style of this.STYLES) {
            let reference = style.format(this.database);
            this.cache.set(style.toString(), reference);
        }
    }
    refreshDisplay() {
        if (this.database == null) {
            outputArea.innerHTML = "Nenhuma referência carregada.";
            return;
        }
        this.outputArea.textContent = "";
        let index = parseInt(this.select.value);
        let selectedStyle = this.STYLES[index];
        let text = this.cache.get(selectedStyle.toString());
        this.outputArea.innerHTML = `${text}`;
    }
    populateStyles() {
        for (let i = 0; i < this.STYLES.length; i++) {
            const s = this.STYLES[i];
            this.select.add(new Option(s.toString(), i));
        }
    }
    registerEvents() {
        let button = document.querySelector("input[type='button']");
        button.onclick = this.convert.bind(this);
        this.select.onchange = this.refreshDisplay.bind(this);
        this.populateStyles();
    };
}
let gui = new GUI();
gui.registerEvents();