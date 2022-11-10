import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestJson {
    @ParameterizedTest
    @MethodSource("stringsToEscape")
    public void testEscape(final String expected, final String original) {
        final StringBuilder builder = new StringBuilder();
        Json.appendStringEscapedForJson(original, builder);
        assertEquals(expected, builder.toString());
    }

    private static Stream<Arguments> stringsToEscape() {
        return Stream.of(
                arguments("foo\\bbar", "foo\bbar"),
                arguments("foo\\fbar", "foo\fbar"),
                arguments("foo\\nbar", "foo\nbar"),
                arguments("foo\\rbar", "foo\rbar"),
                arguments("foo\\tbar", "foo\tbar"),
                arguments("foo\\u0000bar", "foo\u0000bar"),
                arguments("foo\\u0001bar", "foo\u0001bar"),
                arguments("foo\\u0002bar", "foo\u0002bar"),
                arguments("foo\\u0003bar", "foo\u0003bar"),
                arguments("foo\\u0004bar", "foo\u0004bar"),
                arguments("foo\\u0005bar", "foo\u0005bar"),
                arguments("foo\\u0006bar", "foo\u0006bar"),
                arguments("foo\\u0007bar", "foo\u0007bar"),
                arguments("foo\\bbar", "foo\u0008bar"),
                arguments("foo\\tbar", "foo\u0009bar"),
                // 000a cannot be literalized.
                // @see https://docs.oracle.com/javase/specs/jls/se14/html/jls-3.html#jls-3.10.5
                arguments("foo\\u000bbar", "foo\u000bbar"),
                arguments("foo\\fbar", "foo\u000cbar"),
                // 000d cannot be literalized.
                // @see https://docs.oracle.com/javase/specs/jls/se14/html/jls-3.html#jls-3.10.5
                arguments("foo\\u000ebar", "foo\u000ebar"),
                arguments("foo\\u000fbar", "foo\u000fbar"),
                arguments("foo\\u0010bar", "foo\u0010bar"),
                arguments("foo\\u0011bar", "foo\u0011bar"),
                arguments("foo\\u0012bar", "foo\u0012bar"),
                arguments("foo\\u0013bar", "foo\u0013bar"),
                arguments("foo\\u0014bar", "foo\u0014bar"),
                arguments("foo\\u0015bar", "foo\u0015bar"),
                arguments("foo\\u0016bar", "foo\u0016bar"),
                arguments("foo\\u0017bar", "foo\u0017bar"),
                arguments("foo\\u0018bar", "foo\u0018bar"),
                arguments("foo\\u0019bar", "foo\u0019bar"),
                arguments("foo\\u001abar", "foo\u001abar"),
                arguments("foo\\u001bbar", "foo\u001bbar"),
                arguments("foo\\u001cbar", "foo\u001cbar"),
                arguments("foo\\u001dbar", "foo\u001dbar"),
                arguments("foo\\u001ebar", "foo\u001ebar"),
                arguments("foo\\u001fbar", "foo\u001fbar"));
                arguments("いろは", "いろは"),
    }
}
