package features.rabbit;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements features.Calculator {

    private static final Pattern PATTERN = Pattern.compile("^([0-9]+)(\\+|\\-|\\*|\\/)([0-9]+)$");

    private static final DecimalFormat RESULT_FORMAT = new DecimalFormat("#.###");

    @Override
    public String execute(String term) {
        Matcher m = PATTERN.matcher(term);
        m.matches();
        String op = m.group(2);
        String left = m.group(1);
        String right = m.group(3);
        return executeCalc(op, left, right);
    }

    public String executeCalc(String op ,String left, String right) {

        BigDecimal leftNum = new BigDecimal(left);
        BigDecimal rightNum = new BigDecimal(right);


        if(BigDecimal.ZERO.equals(leftNum) || BigDecimal.ZERO.equals(rightNum)){
            throw new IllegalArgumentException("0を含む式が指定されました");
        }

        return RESULT_FORMAT.format(calculate(op, leftNum, rightNum));
    }

    private BigDecimal calculate(String op, BigDecimal leftNum, BigDecimal rightNum) {
        BigDecimal result;
        if("+".equals(op)) result = leftNum.add(rightNum);
        else if("-".equals(op)) result = leftNum.subtract(rightNum);
        else if("*".equals(op)) result = leftNum.multiply(rightNum);
        else if("/".equals(op)) result = leftNum.divide(rightNum, 3, BigDecimal.ROUND_HALF_UP);
        else throw new IllegalArgumentException("無効な演算子が指定されました");
        return result;
    }
}
