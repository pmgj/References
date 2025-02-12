package view;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;

import model.styles.ApalikeFormatter;
import model.styles.FormatterStrategy;
import model.styles.IEEETransactionsFormatter;

public class BibTeXAppSwing {
    private JTextPane outputArea;
    private JComboBox<FormatterStrategy> styleSelector;
    private BibTeXDatabase database;
    private Map<String, String> cache;
    private final FormatterStrategy[] STYLES = new FormatterStrategy[] { new ApalikeFormatter(),
            new IEEETransactionsFormatter() };

    public BibTeXAppSwing() {
        JFrame frame = new JFrame("BibTeX Formatter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextPane();
        outputArea.setContentType("text/html");
        outputArea.setEditable(false);
        styleSelector = new JComboBox<>(STYLES);
        var loadButton = new JButton("Carregar Arquivo");

        cache = new HashMap<>();
        loadButton.addActionListener(e -> loadFile(frame));
        styleSelector.addActionListener(e -> refreshDisplay());

        var topPanel = new JPanel();
        topPanel.add(new JLabel("Estilo:"));
        topPanel.add(styleSelector);
        topPanel.add(loadButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void loadFile(JFrame frame) {
        var fileChooser = new JFileChooser();
        var result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            var filePath = fileChooser.getSelectedFile().getPath();
            try {
                Reader reader = new FileReader(filePath);
                var bibtexParser = new BibTeXParser();
                this.database = bibtexParser.parse(reader);
                this.generateFormattedReferences();
                this.refreshDisplay();
            } catch (IOException | ParseException e) {
                JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        var selectedStyle = (FormatterStrategy) styleSelector.getSelectedItem();
        var text = cache.get(selectedStyle.toString());
        outputArea.setText(String.format("<html><body>%s</body></html>", text));
    }

    public static void main(String[] args) {
        new BibTeXAppSwing();
    }
}