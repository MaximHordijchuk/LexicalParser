package LexemeAnalyzer;

/**
 * Created by max on 30.10.14.
 */

import java.util.regex.Pattern;

public enum LexemeType {
    COMMENT("comment", "(//.*)|(/\\*(.|\\s)*?((\\*/)|($)))", "#828282"),
    NUMBER("number", "(?<![a-zA-Z0-9_$])" +
            "((0|([1-9][0-9]*)|(0(x|X)[a-fA-F0-9]*)|(0[1-7][0-7]*))((?i)(ul|lu|u|l)(?-i))?" +
            "|([0-9]*\\.[0-9]+)([eE][-+]?[0-9]+)?)" +
            "(?![a-zA-Z0-9_$])", "#1C86EE"),
    STRING_OR_CHARACTER("string or character",
            "((\"(\\\\['\"\\\\0abfnrtuUxv]|[^\\\\\"\n]*)\")" +
                    "|(\'((([^'\\\\\n]|\\\\['\"\\\\0abfnrtuUxv])|(\\\\x[0-9a-fA-F]{1,4})))?\'))(?![a-zA-Z0-9_$])",
            "#6A8751"),
    RESERVED_WORD("reserved word", "(?<![a-zA-Z0-9_$])(int|double|void|char|long|unsigned|short|break|continue" +
            "|return|switch|and|or|xor|class|struct|enum|private|public|protected|static|template|for|if" +
            "|friend|const|new|delete)(?![a-zA-Z0-9_$])", "#FF8C00"),
    SEPARATOR("separator", "[\\Q{}[]().,:;\\E]", "#DBCA57"),//#EE6363
    OPERATOR("operator",
            "<?<=?|>?>=?|->|\\*=?|/=?|%=?|&=?|\\|=?|\\^=?|\\+\\+|--|&&?|\\|(\\|)?|<<|>>|==?|!=?|\\+=?|-=?", "#008066"),
    PREPROCESSOR_DIRECTIVES("preprocessor directives", "#.*", "#66B008"),
    IDENTIFIER("identifier", "(?<![a-zA-Z0-9_$])[a-zA-Z_$][a-zA-Z0-9_$]*(?![a-zA-Z0-9_$])", "#B9D3EE");

    private final String name;
    private final String regex;
    private final String color;
    private final Pattern pattern;

    LexemeType(String name, String regex, String color) {
        this.name = name;
        this.regex = regex;
        this.color = color;
        this.pattern = Pattern.compile(regex);
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public String getColor() {
        return color;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
