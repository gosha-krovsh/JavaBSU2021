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
        Pattern pattern = Pattern.compile("^\\+?\\-?(\\d+[\\.]?([\\d]{" + precision + "})?)$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
