import APA from "./apa.js";

class GUI {
    convert() {
        let textarea = document.querySelector("textarea");
        let ul = document.querySelector("ul");
        const bibJSON = BibtexParser.parseToJSON(textarea.value);
        for (const element of bibJSON) {
            let apa = new APA(element);
            let li = document.createElement("li");
            ul.appendChild(li);
            li.innerHTML = apa[element.type](element);
        }
    }
    registerEvents() {
        let button = document.querySelector("input[type='button']");
        button.onclick = this.convert.bind(this);
    };
}
let gui = new GUI();
gui.registerEvents();