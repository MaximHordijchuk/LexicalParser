package LexemeAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Created by max on 30.10.14.
 */
public class Lexer {

    StringBuilder source;

    public Lexer() {
        source = new StringBuilder();
    }

    public Lexer(String source) {
        setSource(source);
    }

    public Lexer(Reader source) throws IOException {
        setSource(source);
    }

    public void setSource(Reader source) throws IOException {
        this.source = new StringBuilder();
        BufferedReader reader = new BufferedReader(source);
        int c;
        while ((c = reader.read()) != -1) {
            this.source.append((char) c);
        }
    }

    public void setSource(String source) {
        this.source = new StringBuilder();
        this.source.append(source);
    }

    public void replaceSourceString(int from, int to, String str) {
        source.replace(from, to, str);
    }

    public void analyzeSource() {
                                  
    }

    private void processString(String string) {
        addLineToForm(string);
        changeTextColor(LexemeType.IDENTIFIER.getPattern().matcher(string), textLengthBefore,
                LexemeType.IDENTIFIER.getColor());
        changeTextColor(LexemeType.NUMBER.getPattern().matcher(string), textLengthBefore,
                LexemeType.NUMBER.getColor());
        changeTextColor(LexemeType.SEPARATOR.getPattern().matcher(string), textLengthBefore,
                LexemeType.SEPARATOR.getColor());
        changeTextColor(LexemeType.RESERVED_WORD.getPattern().matcher(string), textLengthBefore,
                LexemeType.RESERVED_WORD.getColor());
    }

    private void changeTextColor(Matcher matcher, int textLengthBefore, String color) {
        while (matcher.find()) {
            changeTextColorToForm(matcher.start(1) + textLengthBefore, matcher.end(1) + textLengthBefore, color);
        }
    }

    private void addLineToForm(String line) {
        codeAnalyzerGUI.addLine(line);
    }

    private void changeTextColorToForm(int start, int end, String color) {
        codeAnalyzerGUI.changeTestColor(start, end, color);
    }

}
