package LexemeAnalyzer;

/**
 * Created by max on 30.10.14.
 */

import java.util.regex.Pattern;

public enum LexemeType {
    //gold #FFD700#FF3030
    COMMENT("comment", "(//) | ()", ""),
    NUMBER("number", "[^a-zA-Z](0|([1-9]+)|(0(x|X)[a-fA-F0-9])|(0[1-7][0-7]))[^a-zA-Z]", "#00CD00"),
    RESERVED_WORD("reserved word", "[^a-zA-Z](int|double|void|char|long|unsigned|short|break|continue" +
            "|return|switch|and|or|xor|class|struct|enum|private|public|protected|static" +
            "|friend|const|new|delete)[^a-zA-Z]", "#FF8C00"), //dark orange
    SEPARATOR("separator", "([.:,'\" ])", "#EE6363"),
    IDENTIFIER("identifier", "([a-zA-Z_$][a-zA-Z0-9_$]*)", "#B9D3EE");

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
