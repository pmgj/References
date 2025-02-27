import APA from "../model/styles/ApalikeFormatter.js";
import IEEE from "../model/styles/IEEETransactionsFormatter.js";

class GUI {
    STYLES = [new APA(), new IEEE()];
    database;
    cache = new Map();
    select = document.querySelector("#style");
    outputArea = document.querySelector("output");
    file = document.querySelector("input[type='file']");
    convert() {
        const reader = new FileReader();
        reader.onload = () => {
            this.database = BibtexParser.parseToJSON(reader.result);
            this.generateFormattedReferences();
            this.refreshDisplay();
        };
        reader.onerror = () => {
            showMessage("Error reading the file. Please try again.", "error");
        };
        reader.readAsText(this.file.files[0]);
        this.outputArea.innerHTML = "";
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
            this.outputArea.innerHTML = "Nenhuma referência carregada.";
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
        this.select.onchange = this.refreshDisplay.bind(this);
        this.file.onchange = this.convert.bind(this);
        this.populateStyles();
    };
}
let gui = new GUI();
gui.registerEvents();