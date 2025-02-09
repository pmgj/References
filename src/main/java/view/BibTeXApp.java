package view;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

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

public class BibTeXApp {
    private JFrame frame;
    private JTextPane outputArea;
    private JComboBox<FormatterStrategy> styleSelector;
    private BibTeXDatabase database;

    public BibTeXApp() {
        frame = new JFrame("BibTeX Formatter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextPane();
        outputArea.setContentType("text/html");
        outputArea.setEditable(false); // Evita edição manual
        styleSelector = new JComboBox<>(new FormatterStrategy[] { new ApalikeFormatter(), new IEEETransactionsFormatter() });
        JButton loadButton = new JButton("Carregar Arquivo");

        // Listener para carregar arquivo
        loadButton.addActionListener(e -> loadFile());

        // Listener para mudança de estilo
        styleSelector.addActionListener(e -> refreshDisplay());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Estilo:"));
        topPanel.add(styleSelector);
        topPanel.add(loadButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            try {
                Reader reader = new FileReader(filePath);
                BibTeXParser bibtexParser = new BibTeXParser();
                this.database = bibtexParser.parse(reader);
                this.refreshDisplay(); // Atualiza a exibição
            } catch (IOException | ParseException e) {
                JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshDisplay() {
        if (this.database == null) {
            outputArea.setText("Nenhuma referência carregada.");
            return;
        }
        outputArea.setText(""); // Limpa a área de exibição
        FormatterStrategy selectedStyle = (FormatterStrategy) styleSelector.getSelectedItem();
        String text = selectedStyle.format(database);
        outputArea.setText(String.format("<html><body>%s</body></html>", text));
    }

    public static void main(String[] args) {
        new BibTeXApp();
    }
}