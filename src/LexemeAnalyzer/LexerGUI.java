package LexemeAnalyzer;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by max on 01.11.14.
 */
public class LexerGUI extends JFrame {

    public static final Color DEFAULT_FOREGROUND_COLOR = Color.decode("#8B0000");
    public static final Color BACKGROUND_COLOR = Color.decode("#4F4F4F");
    private JPanel rootPanel;
    private JTextPane text;
    private JScrollPane scrollPane;


    public LexerGUI() throws HeadlessException {
        super("Code analyzer");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollBar jScrollBar = scrollPane.getVerticalScrollBar();
        jScrollBar.setUnitIncrement(20);

        text.setBackground(BACKGROUND_COLOR);
        setDefaultForegroundColor();

        setVisible(true);
    }

    public void addTextChangedListener(DocumentListener listenForTextChanged) {
        text.getDocument().addDocumentListener(listenForTextChanged);
    }

    public void changeTextColor(List<LexemePosition> lexemePositions) {
        setDefaultForegroundColor();
        for (final LexemePosition position : lexemePositions) {
            Thread highlightThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StyledDocument document = text.getStyledDocument();
                    SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                    StyleConstants.setForeground(attributeSet, Color.decode(position.type.getColor()));
                    document.setCharacterAttributes(position.start, position.end - position.start,
                            attributeSet, true);
                    text.setDocument(document);
                }
            });
            SwingUtilities.invokeLater(highlightThread);
        }
    }

    public void setDefaultForegroundColor() {
        Thread highlightThread = new Thread(new Runnable() {
            @Override
            public void run() {
                StyledDocument document = text.getStyledDocument();
                SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                StyleConstants.setForeground(attributeSet, DEFAULT_FOREGROUND_COLOR);
                document.setCharacterAttributes(0, document.getLength(), attributeSet, true);
                text.setDocument(document);
            }
        });
        SwingUtilities.invokeLater(highlightThread);
    }

    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
