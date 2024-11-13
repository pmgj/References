import APA from "./apalike.js";
import IEEE from "./ieeetr.js";

class GUI {
    styles = [APA, IEEE];
    select = document.querySelector("#style");
    convert() {
        let textarea = document.querySelector("textarea");
        let ul = document.querySelector("ul");
        ul.innerHTML = "";
        const bibJSON = BibtexParser.parseToJSON(textarea.value);
        let index = parseInt(this.select.value);
        for (const element of bibJSON) {
            let style = new this.styles[index]();
            style.processAuthors(element);
            let li = document.createElement("li");
            ul.appendChild(li);
            li.innerHTML = style[element.type](element);
        }
    }
    populateStyles() {
        for (let i = 0; i < this.styles.length; i++) {
            const s = this.styles[i];
            this.select.add(new Option(new s(), i));
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