// mvn package javafx:run
package view;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.styles.ApalikeFormatter;
import model.styles.FormatterStrategy;
import model.styles.IEEETransactionsFormatter;

public class BibTeXAppJavaFX extends Application {
    private ComboBox<FormatterStrategy> styleSelector;
    private TextArea outputArea;
    private BibTeXDatabase database;
    private Map<String, String> cache;
    private final FormatterStrategy[] STYLES = new FormatterStrategy[] { new ApalikeFormatter(),
            new IEEETransactionsFormatter() };

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BibTeX Formatter");

        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 10;");

        Label label = new Label("Estilo:");

        styleSelector = new ComboBox<>();
        styleSelector.getItems().addAll(STYLES);
        styleSelector.setValue(STYLES[0]);
        // styleSelector.setPromptText("Selecionar Estilo");

        Button loadButton = new Button("Carregar Arquivo");

        VBox v = new VBox(10);
        root.getChildren().addAll(label, styleSelector, loadButton);

        outputArea = new TextArea();
        outputArea.setEditable(false);

        v.getChildren().addAll(root, outputArea);

        cache = new HashMap<>();
        loadButton.setOnAction(event -> loadFile(primaryStage));
        styleSelector.setOnAction(event -> refreshDisplay());

        primaryStage.setScene(new Scene(v, 600, 400));
        primaryStage.show();
    }

    private void loadFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo BibTeX");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("arquivo BibTeX", "*.bib"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        try {
            Reader reader = new FileReader(selectedFile);
            var bibtexParser = new BibTeXParser();
            this.database = bibtexParser.parse(reader);
            this.generateFormattedReferences();
            this.refreshDisplay();
        } catch (IOException | ParseException e) {
            System.out.println("Erro ao ler o arquivo.");
        }
    }

    private void generateFormattedReferences() {
        cache.clear();
        for (var style : STYLES) {
            var reference = style.format(this.database);
            cache.put(style.toString(), reference);
        }
    }

    private void refreshDisplay() {
        if (this.database == null) {
            outputArea.setText("Nenhuma referência carregada.");
            return;
        }
        outputArea.setText(""); // Limpa a área de exibição
        var selectedStyle = styleSelector.getValue();
        var text = cache.get(selectedStyle.toString());
        outputArea.setText(String.format("<html><body>%s</body></html>", text));
    }
}