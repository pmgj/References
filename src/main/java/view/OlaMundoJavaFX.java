// mvn package javafx:run
package view;

import java.io.File;

import javafx.application.Application;
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

public class OlaMundoJavaFX extends Application {

    private TextArea outputArea;
    private final FormatterStrategy[] STYLES = new FormatterStrategy[] { new ApalikeFormatter(),
            new IEEETransactionsFormatter() };

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Formatador de Citação BibTeX");

        HBox root = new HBox(10);
        root.setStyle("-fx-padding: 10;");

        Button loadButton = new Button("Carregar Arquivo");
        ComboBox<FormatterStrategy> styleSelector = new ComboBox<>();
        styleSelector.getItems().addAll(STYLES);
        styleSelector.setPromptText("Selecionar Estilo");
        Button formatButton = new Button("Formatar Referências");

        outputArea = new TextArea();
        outputArea.setEditable(false);

        Label label = new Label("Estilo:");

        VBox v = new VBox(10);
        root.getChildren().addAll(label, loadButton, styleSelector, formatButton);
        v.getChildren().addAll(root, outputArea);

        // Carregar botões de ação
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir arquivo BibTeX");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("arquivo BibTeX", "*.bib"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                outputArea.setText("Arquivo carregado com sucesso!");
            }
        });

        // Botão de ação
        formatButton.setOnAction(event -> {
            // if (references == null || references.isEmpty()) {
            // outputArea.setText("Arquivos não carregados.");
            // return;
            // }

            FormatterStrategy selectedStyle = styleSelector.getValue();
            if (selectedStyle == null) {
                outputArea.setText("Por favor, selecione um estilo de citação.");
                return;
            }

            // CitationStyle style;
            // if ("apalike".equals(selectedStyle)) {
            // style = new ApalikeStyle();
            // } else if ("ieeetr".equals(selectedStyle)) {
            // style = new IeeetrStyle();
            // } else {
            // outputArea.setText("Estilo selecionado não suportado.");
            // return;
            // }

            // CitationFormatter formatter = new CitationFormatter(style);
            // String formattedReferences = formatter.formatReferences(references);
            // outputArea.setText(formattedReferences);
        });

        primaryStage.setScene(new Scene(v, 600, 400));
        primaryStage.show();
    }
}