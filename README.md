## **JavaCC parser project**

### Project Description

This project is a simple JavaCC-based parser that demonstrates the basic structure of a compiler front-end. It uses a generated parser with JavaCC configuration options and defines a minimal grammar that accepts a single keyword (`"hello"`). The project serves as a starting point for learning parser generation, token handling, and grammar definition in JavaCC.

### Build and Run

The correct order is:
1. Generate the parser sources with JavaCC.
2. Compile the generated `.java` files.
3. Run the compiled parser class.

For the parser in `src/Parser.jj`:

```bash
javacc src/Parser.jj
javac -d out gen/*.java
java -cp out Parser
```

For the packaged binary parser in `src/BinaryParser.jj`:

```bash
javacc src/BinaryParser.jj
javac -d out genParser/*.java
java -cp out genParser.BinaryParser
```

For the expression parser in `src/ExpressionParser.jj` and its test in `test/ExpressionParserTest.java`:

```bash
javacc src/ExpressionParser.jj
javac -cp "lib/junit-4.13.2.jar" -d out genParser/*.java test/ExpressionParserTest.java
java -cp "out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ExpressionParserTest
```

If you compile directly inside the generated folder instead of using `-d out`, then the matching run command should use that folder on the classpath. For example, `javac *.java` in the generated folder would pair with `java -cp . genParser.BinaryParser`, not with `-cp out`.
