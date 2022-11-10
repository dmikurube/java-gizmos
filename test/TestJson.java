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
                arguments("foo\\nbar", "foo\nbar"),
                arguments("いろは", "いろは"),
                arguments("\\u0001", "\u0001"));
    }
}
