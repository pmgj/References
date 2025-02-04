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
import javax.swing.JTextArea;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;

import model.ApalikeFormatter;
import model.FormatterStrategy;

public class BibTeXApp {
    private JFrame frame;
    private JTextArea outputArea;
    private JComboBox<String> styleSelector;
    // private FormatterStrategy formatterStrategy;
    private BibTeXDatabase database;

    public BibTeXApp() {
        frame = new JFrame("BibTeX Formatter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextArea();
        outputArea.setEditable(false); // Evita edição manual
        styleSelector = new JComboBox<>(new String[] { "Apalike", "Ieeetr" });
        JButton loadButton = new JButton("Carregar Arquivo");

        // Listener para carregar arquivo
        loadButton.addActionListener(e -> loadFile());

        // Listener para mudança de estilo
        styleSelector.addActionListener(e -> {
            String selectedStyle = (String) styleSelector.getSelectedItem();
            // formatterStrategy = selectedStyle.equals("Apalike")
            // ? new ApalikeFormatter()
            // : new IeeetrFormatter();
            refreshDisplay(); // Atualiza a exibição ao mudar o estilo
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Estilo:"));
        topPanel.add(styleSelector);
        topPanel.add(loadButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        frame.setVisible(true);

        // formatterStrategy = new ApalikeFormatter(); // Estilo padrão inicial
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
        // String selectedStyle = (String) styleSelector.getSelectedItem();
        FormatterStrategy strategy = new ApalikeFormatter();
        String text = strategy.format(database);
        outputArea.setText(text);
    }

    public static void main(String[] args) {
        new BibTeXApp();
    }
}