package LexemeAnalyzer;

import java.io.*;
import java.util.*;
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

    public void insertString(int offset, String string) {
        source.insert(offset, string);

    }

    public void removeString(int offset, int length) {
        source.delete(offset, offset + length);
    }

    public LinkedList<LexemePosition> analyzeSource() {
        StringBuilder stringBuilder = new StringBuilder(source);
        LinkedList<LexemePosition> lexemePositions = new LinkedList<LexemePosition>();
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.COMMENT));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.PREPROCESSOR_DIRECTIVES));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.STRING_OR_CHARACTER));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.RESERVED_WORD));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.IDENTIFIER));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.NUMBER));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.OPERATOR));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.SEPARATOR));
        return lexemePositions;
    }

    private List<LexemePosition> getLexemePosition(StringBuilder string, LexemeType lexemeType) {
        Matcher matcher = lexemeType.getPattern().matcher(string.toString());
        LinkedList<LexemePosition> lexemePositions = new LinkedList<LexemePosition>();
        while (matcher.find()) {
            lexemePositions.add(new LexemePosition(lexemeType, matcher.start(), matcher.end()));
        }
        for (ListIterator<LexemePosition> position = lexemePositions.listIterator(lexemePositions.size());
             position.hasPrevious(); ) {
            LexemePosition currentPosition = position.previous();
            for (int i = currentPosition.start; i < currentPosition.end; i++) {
                string.setCharAt(i, ' ');
            }
        }
        return lexemePositions;
    }
}
