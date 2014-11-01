package LexemeAnalyzer;

/**
 * Created by max on 30.10.14.
 */

import java.util.regex.Pattern;

public enum LexemeType {
    //gold #FFD700#FF3030
    COMMENT("comment", "(//.*)|(/\\*(.|\\s)*\\*/)", ""),
    NUMBER("number", "(?<![a-zA-Z0-9_$])" +
            "((0|([1-9][0-9]*)|(0(x|X)[a-fA-F0-9]*)|(0[1-7][0-7]*))((?i)(ul|lu|u|l)(?-i))?" +
            "|([0-9]*\\.[0-9]+)([eE][-+]?[0-9]+)?)" +
            "(?![a-zA-Z0-9_$])", "#00CD00"),
    STRING_OR_CHARACTER("string or character",
            "(\"(\\\\['\"\\\\0abfnrtuUxv]|[^\\\\\"\n]*)\")" +
                    "|(\'((([^'\\\\\n]|\\\\['\"\\\\0abfnrtuUxv])|(\\\\x[0-9a-fA-F]{1,4})))?\')", ""),
    RESERVED_WORD("reserved word", "(?<![a-zA-Z0-9_$])(int|double|void|char|long|unsigned|short|break|continue" +
            "|return|switch|and|or|xor|class|struct|enum|private|public|protected|static" +
            "|friend|const|new|delete)(?![a-zA-Z0-9_$])", "#FF8C00"),
    SEPARATOR("separator", "[\\Q{}[]().,:;\\E]", "#EE6363"),
    OPERATOR("operator",
            "<?<=?|>?>=?|->|\\*=?|/=?|%=?|&=?|\\|=?|\\^=?|\\+\\+|--|&&?|\\|(\\|)?|<<|>>|==?|!=?|\\+=?|-=?", ""),
    PREPROCESSOR_DIRECTIVES("preprocessor directives", "#.*", ""),
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
