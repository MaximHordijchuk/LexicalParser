package LexicalParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

/**
 * Created by max on 30.10.14.
 */
public class Lexer {

    BufferedReader reader;

    public Lexer(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void analyzeCode() throws IOException {
        String line;
        int textLengthBefore = 0;
        while ((line = reader.readLine()) != null) {
            processLine(line, textLengthBefore);
            textLengthBefore += line.length() + 1;
        }

    }

    private void processLine(String line, int textLengthBefore) {
        addLineToForm(line);
        changeTextColor(LexemeType.IDENTIFIER.getPattern().matcher(line), textLengthBefore,
                LexemeType.IDENTIFIER.getColor());
        changeTextColor(LexemeType.NUMBER.getPattern().matcher(line), textLengthBefore,
                LexemeType.NUMBER.getColor());
        changeTextColor(LexemeType.SEPARATOR.getPattern().matcher(line), textLengthBefore,
                LexemeType.SEPARATOR.getColor());
        changeTextColor(LexemeType.RESERVED_WORD.getPattern().matcher(line), textLengthBefore,
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
