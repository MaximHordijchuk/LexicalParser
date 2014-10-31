package LexemeAnalyzer;

/**
 * Created by max on 30.10.14.
 */
public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("int main(int argc, char **argv) {\n");
        lexer.analyzeSource();
    }
}
