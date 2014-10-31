package LexemeAnalyzer;

/**
 * Created by max on 31.10.14.
 */
public class LexemePosition {
    public final LexemeType type;
    public final int start;
    public final int end;

    public LexemePosition(LexemeType type, int start, int to) {
        this.type = type;
        this.start = start;
        this.end = to;
    }
}
