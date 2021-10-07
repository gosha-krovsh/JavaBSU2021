package by.gosha_krovsh.quizer.tasks.math_tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface DoubleStringConverter {
    static String doubleToString(int precision, double value) {
        return BigDecimal.valueOf(value)
                .setScale(precision, RoundingMode.HALF_UP)
                .toString();
    }

    static boolean isDoubleStringCorrect(int precision,
                                         String string) {
        Pattern pattern = Pattern.compile("^\\+?\\-?(\\d+[\\.]?([\\d]+)?)$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    static String correctDoubleStringPrecision(int precision,
                                               String string) {
        int dot_index = string.indexOf('.');
        if (dot_index == -1 && precision == 0) {
            return string;
        } else if (dot_index == -1) {
            string += ".";
            dot_index = string.length() - 1;
        }

        String substring = string.substring(dot_index + 1);
        if (substring.length() > precision) {
            string = string.substring(0, dot_index + precision + 1);
            return string;
        } else {
            String addition = new String(new char[precision - substring.length()])
                    .replace('\0', '0');
            return string + addition;
        }
    }
}
