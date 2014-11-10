package LexemeAnalyzer;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Created by max on 05.11.14.
 */
public class LexerController {

    LexerGUI lexerGUI;
    Lexer lexer;

    public LexerController(LexerGUI lexerGUI, Lexer lexer) {
        this.lexerGUI = lexerGUI;
        this.lexer = lexer;

        this.lexerGUI.addTextChangedListener(new TextChangedListener());
    }

    class TextChangedListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                lexer.insertString(e.getOffset(), e.getDocument().getText(e.getOffset(), e.getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            lexerGUI.changeTextColor(lexer.analyzeSource());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            lexer.removeString(e.getOffset(), e.getLength());
            lexerGUI.changeTextColor(lexer.analyzeSource());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
