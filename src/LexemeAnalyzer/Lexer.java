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

    public void replaceSourceString(int from, int to, String str) {
        source.replace(from, to, str);
    }

    public LinkedList<LexemePosition> analyzeSource() {
        return processString(0, source.length() - 1);
    }

    public LinkedList<LexemePosition> analyzeSource(int start, int end) {
        return processString(start, end);
    }

    private LinkedList<LexemePosition> processString(int start, int end) {
        StringBuilder stringBuilder = new StringBuilder(source.substring(start, end));
        LinkedList<LexemePosition> lexemePositions = new LinkedList<LexemePosition>();
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.RESERVED_WORD));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.IDENTIFIER));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.NUMBER));
        lexemePositions.addAll(getLexemePosition(stringBuilder, LexemeType.SEPARATOR));
        return lexemePositions;
    }

    private List<LexemePosition> getLexemePosition(StringBuilder string, LexemeType lexemeType) {
        Matcher matcher = lexemeType.getPattern().matcher(string.toString());
        LinkedList<LexemePosition> lexemePositions = new LinkedList<LexemePosition>();
        while (matcher.find()) {
            lexemePositions.add(new LexemePosition(lexemeType, matcher.start(1), matcher.end(1)));
        }
        for (ListIterator<LexemePosition> position = lexemePositions.listIterator(lexemePositions.size());
             position.hasPrevious(); ) {
            LexemePosition currentPosition = position.previous();
            string.delete(currentPosition.start, currentPosition.end);
        }
        return lexemePositions;
    }
}
