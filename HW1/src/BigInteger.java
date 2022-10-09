import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigInteger {
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

    public static final String NUMBER_PATTERN = "\\s*(\\+|\\-*)([0-9]+)\\s*";
    public static final String OP_PATTERN = "(\\+|\\-|\\*|/)";
    public static final Pattern EXPRESSION_PATTERN = Pattern.compile(NUMBER_PATTERN + OP_PATTERN + NUMBER_PATTERN);

    char[] value;
    boolean isPlus;

    public BigInteger(char[] s, boolean isPlus) {
        this.value = s;
        this.isPlus = isPlus;
    }

    public BigInteger toggleSign() {
        this.isPlus = !this.isPlus;

        return this;
    }

    private BigInteger addImpl(BigInteger big) {
        boolean isPlus = this.isPlus && big.isPlus;

        int[] arr = new int[Math.max(this.value.length, big.value.length) + 1];

        for (int i = 0; i < this.value.length; i++) {
            arr[i] = Character.getNumericValue(this.value[i]);
        }

        for (int i = 0; i < big.value.length; i++) {
            arr[i] = arr[i] + Character.getNumericValue(big.value[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 10) {
                int num = arr[i] % 10;
                int pass = arr[i] / 10;

                arr[i] = num;

                if (pass >= 1) {
                    arr[i + 1] = arr[i + 1] + pass;
                }
            }
        }

        char[] result = new char[arr[arr.length - 1] == 0 ? arr.length - 1 : arr.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (arr[result.length - 1 - i] + '0');
        }

        return new BigInteger(result, isPlus);
    }

    public BigInteger add(BigInteger big) {
        if (!this.isPlus && big.isPlus) {
            return big.subtract(this.toggleSign()); // +big - +this
        }

        if (this.isPlus && !big.isPlus) {
            return this.subtract(big.toggleSign()); // +this - +big
        }

        return this.addImpl(big); // +this + +big or -this + -big
    }

    private BigInteger subtractImpl(BigInteger big) {
        int[] arr = new int[Math.max(this.value.length, big.value.length)];

        for (int i = 0; i < this.value.length; i++) {
            arr[i] = Character.getNumericValue(this.value[i]);
        }

        for (int i = 0; i < big.value.length; i++) {
            arr[i] = arr[i] - Character.getNumericValue(big.value[i]);
        }

        boolean isPlus = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                isPlus = arr[i] > 0;
                break;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (isPlus && arr[i] < 0) {
                arr[i + 1]--;
                arr[i] = 10 + arr[i];
            } else if (!isPlus && arr[i] > 0) {
                arr[i + 1]++;
                arr[i] = -10 + arr[i];
            }
        }

        int length = arr.length;
        for (; length > 1; length--) {
            if (arr[length - 1] != 0) {
                break;
            }
        }

        char[] result = new char[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (Math.abs(arr[result.length - 1 - i]) + '0');
        }

        return new BigInteger(result, isPlus);
    }

    public BigInteger subtract(BigInteger big) {
        if ((this.isPlus && !big.isPlus) || (!this.isPlus && big.isPlus)) {
            return this.add(big.toggleSign()); // +this + +big or -this + -big
        }

        if (!this.isPlus && !big.isPlus) { // -this - (-big)
            return big.toggleSign().subtract(this.toggleSign()); // +big - +this
        }

        return this.subtractImpl(big); // +this - +big
    }

    public BigInteger multiply(BigInteger big) {
        boolean isPlus = this.isPlus && big.isPlus || !this.isPlus && !big.isPlus;

        int[] arr = new int[this.value.length + big.value.length];

        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < big.value.length; j++) {
                arr[i + j] = arr[i + j]
                        + Character.getNumericValue(this.value[i]) * Character.getNumericValue(big.value[j]);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 10) {
                int num = arr[i] % 10;
                int pass = arr[i] / 10;

                arr[i] = num;

                if (pass >= 1) {
                    arr[i + 1] = arr[i + 1] + pass;
                }
            }
        }

        int length = arr.length;
        for (; length > 1; length--) {
            if (arr[length - 1] != 0) {
                break;
            }
        }

        char[] result = new char[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (Math.abs(arr[result.length - 1 - i]) + '0');
        }

        return new BigInteger(result, isPlus);
    }

    @Override
    public String toString() {
        String str = this.isPlus ? "" : "-";

        for (int i = 0; i < this.value.length; i++) {
            str += this.value[i];
        }

        return str;
    }

    static BigInteger compute(BigInteger first, BigInteger second, Operator op) throws Exception {
        switch (op) {
            case ADD:
                return first.add(second);
            case SUBTRACT:
                return first.subtract(second);
            case MULTIPLY:
                return first.multiply(second);
        }

        throw new Exception();
    }

    static Operator getOperator(String token) throws Exception {
        switch (token) {
            case "+":
                return Operator.ADD;
            case "-":
                return Operator.SUBTRACT;
            case "*":
                return Operator.MULTIPLY;
        }

        throw new Exception();
    }

    static BigInteger evaluate(String input) throws Exception {
        String numberPattern = "\\s*(\\+|\\-*)([0-9]+)\\s*";
        String opPattern = "(\\+|\\-|\\*|/)";

        Pattern p = Pattern.compile(numberPattern + opPattern + numberPattern);
        Matcher m = p.matcher(input);
        boolean b = m.matches();

        if (b) {
            Operator op = getOperator(m.group(3));

            String firstSign = m.group(1) == "" ? "+" : m.group(1);
            String firstNumber = m.group(2);
            String secondSign = m.group(4) == "" ? "+" : m.group(4);
            String secondNumber = m.group(5);

            char[] firstArr = firstNumber.toCharArray();
            char[] secondArr = secondNumber.toCharArray();

            for (int i = 0; i < firstArr.length / 2; i++) {
                char temp = firstArr[i];
                firstArr[i] = firstArr[firstArr.length - i - 1];
                firstArr[firstArr.length - i - 1] = temp;
            }
            for (int i = 0; i < secondArr.length / 2; i++) {
                char temp = secondArr[i];
                secondArr[i] = secondArr[secondArr.length - i - 1];
                secondArr[secondArr.length - i - 1] = temp;
            }

            BigInteger first = new BigInteger(firstArr, !firstSign.equals("-"));
            BigInteger second = new BigInteger(secondArr, !secondSign.equals("-"));

            BigInteger result = compute(first, second, op);

            return result;
        }

        throw new Exception();
    }

    public static void main(String[] args) throws Exception {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                boolean done = false;
                while (!done) {
                    String input = reader.readLine();

                    try {
                        done = processInput(input);
                    } catch (Exception e) {
                        System.out.println(e);
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput(String input) throws Exception {
        boolean quit = isQuitCmd(input);

        if (quit) {
            return true;
        } else {
            BigInteger result = evaluate(input);
            System.out.println(result.toString());

            return false;
        }
    }

    static boolean isQuitCmd(String input) {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
