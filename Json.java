import java.io.IOException;
import java.io.Writer;

public final class Json {
    private Json() {
        // No instantiation.
    }

    /**
     * Writes a string to {@link Writer}, with escaping it for JSON, only with minimum conversions.
     *
     * <p>Some JSON encoder implementations convert solidas (slash) {@code '/'} along with other special
     * characters, but it is not mandatory. They escape solidas just to be safe and secure against strings
     * including HTML end tags like {@code "</script>"}. For example, JSON-java, a reference implementation
     * of a JSON package in Java, converts solidas only when it is after the left angle bracket {@code '<'}.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259">RFC 8259 - The JavaScript Object Notation (JSON) Data Interchange Format</a>
     * @see <a href="https://github.com/stleary/JSON-java">JSON-java</a>
     * @see <a href="https://mondotondo.com/2010/12/29/the-solidus-issue/">The Solidus Issue</a>
     */
    public static void writeStringEscapedForJson(final String original, final Writer writer) throws IOException {
        if (original == null || original.isEmpty()) {
            return;
        }

        final int length = original.length();

        for (int i = 0; i < length; i++) {
            final char current = original.charAt(i);
            switch (current) {
                case '\\':
                    writer.append("\\\\");
                    break;
                case '"':
                    writer.append("\\\"");
                    break;
                case '\b':  // 0008
                    writer.append("\\b");
                    break;
                case '\f':  // 000c
                    writer.append("\\f");
                    break;
                case '\n':  // 000a
                    writer.append("\\n");
                    break;
                case '\r':  // 000d
                    writer.append("\\r");
                    break;
                case '\t':  // 0009
                    writer.append("\\t");
                    break;
                case '\0':
                case '\u0001':
                case '\u0002':
                case '\u0003':
                case '\u0004':
                case '\u0005':
                case '\u0006':
                case '\u0007':
                case '\u000b':
                case '\u000e':
                case '\u000f':
                case '\u0010':
                case '\u0011':
                case '\u0012':
                case '\u0013':
                case '\u0014':
                case '\u0015':
                case '\u0016':
                case '\u0017':
                case '\u0018':
                case '\u0019':
                case '\u001a':
                case '\u001b':
                case '\u001c':
                case '\u001d':
                case '\u001e':
                case '\u001f':
                    writer.append("\\u00");
                    final String hex = Integer.toHexString(current);
                    writer.append("00", 0, 2 - hex.length());
                    writer.append(hex);
                    break;
                default:
                    writer.append(current);
            }
        }
    }
}
