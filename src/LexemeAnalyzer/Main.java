package LexemeAnalyzer;

import java.io.*;

/**
 * Created by max on 30.10.14.
 */
public class Main {
    public static void main(String[] args) {
        /*try {
            Reader reader = new InputStreamReader(new FileInputStream((new File("input.txt"))), "UTF-8");
            Lexer lexer = new Lexer(reader);
            lexer.analyzeSource();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Lexer lexer = new Lexer();
        LexerGUI lexerGUI = new LexerGUI();
        LexerController lexerController = new LexerController(lexerGUI, lexer);
    }
}
