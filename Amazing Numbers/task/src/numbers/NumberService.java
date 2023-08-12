package numbers;


import java.util.*;

public class NumberService {

    public void processBuzzProperty(Number number) {
        NumberProperty buzzProperty = createProperty("buzz", isBuzz(number));
        number.getNumberProperties().add(buzzProperty);
    }

    public void processDuckProperty(Number number) {
        NumberProperty duckProperty = createProperty("duck", isDuck(number));
        number.getNumberProperties().add(duckProperty);
    }

    public void processPalindromicProperty(Number number) {
        NumberProperty palindromicProperty = createProperty("palindromic", isPalindromic(number));
        number.getNumberProperties().add(palindromicProperty);
    }

    public void processGapfulProperty(Number number) {
        NumberProperty gapfulProperty = createProperty("gapful", isGapful(number));
        number.getNumberProperties().add(gapfulProperty);
    }

    public void processSpyProperty(Number number) {
        NumberProperty spyProperty = createProperty("spy", isSpy(number));
        number.getNumberProperties().add(spyProperty);
    }

    public void processSquareProperty(Number number) {
        long numberValue = number.getNumberValue();
        NumberProperty squareProperty = createProperty("square", isSquare(numberValue));
        number.getNumberProperties().add(squareProperty);
    }

    public void processSunnyProperty(Number number) {
        long numberValue = number.getNumberValue();
        NumberProperty sunnyProperty = createProperty("sunny", isSunny(numberValue));
        number.getNumberProperties().add(sunnyProperty);
    }


    public void processJumpingProperty(Number number) {
        NumberProperty jumpingProperty = createProperty("jumping", isJumping(number));
        number.getNumberProperties().add(jumpingProperty);
    }

    private boolean isJumping(Number number) {
        String stringValue = number.getStringValue();
        int length = stringValue.length();
        if (length == 1) {
            return true;
        }

        for (int i = 0; i < length - 1; i++) {
            int currentDigit = Character.getNumericValue(stringValue.charAt(i));
            int nextDigit = Character.getNumericValue(stringValue.charAt(i + 1));

            if (Math.abs(currentDigit - nextDigit) != 1) {
                return false;
            }
        }
        return true;
    }

    public void processHappyAndSadProperty(Number number) {
        NumberProperty happyProperty = createProperty("happy", isHappy(number));
        NumberProperty sadProperty = createProperty("sad", !isHappy(number));
        Collections.addAll(number.getNumberProperties(), happyProperty, sadProperty);
    }

    private boolean isHappy(Number number) {
        long numberValue = number.getNumberValue();
        HashSet<Long> visitedNumbers = new HashSet<>();

        while (numberValue != 1) {
            Long currentNumber = numberValue;
            Long sum = 0L;

            while (currentNumber != 0) {
                Long digit = currentNumber % 10;
                sum += digit * digit;
                currentNumber /= 10;
            }

            if (visitedNumbers.contains(sum)) {
                return false;
            }
            visitedNumbers.add(sum);
            numberValue = sum;

        }
        return true;
    }

    public void processEvenAndOddProperty(Number number) {
        boolean isEven = number.getNumberValue() % 2 == 0;
        NumberProperty evenProperty = createProperty("even", isEven);
        number.getNumberProperties().add(evenProperty);
        NumberProperty oddProperty = createProperty("odd", !isEven);
        number.getNumberProperties().add(oddProperty);
    }

    public void createAndSetAllTrueProperties(Number number) {
        List<String> allTrueProperties = new ArrayList<>();
        List<NumberProperty> numberProperties = number.getNumberProperties();
        for (NumberProperty numberProperty : numberProperties) {
            if (numberProperty.isBooleanValue()) {
                allTrueProperties.add(numberProperty.getName());
            }
        }
        number.setAllTrueProperties(allTrueProperties);
    }

    private boolean isBuzz(Number number) {
        long numberValue = number.getNumberValue();
        boolean isDivisibleBySeven = numberValue % 7 == 0;
        boolean endsWithSeven = numberValue % 10 == 7;
        return isDivisibleBySeven || endsWithSeven;
    }

    private boolean isDuck(Number number) {
        String stringValue = number.getStringValue();
        String substring = stringValue.substring(1);
        return substring.contains("0");
    }

    private boolean isPalindromic(Number number) {
        String stringValue = number.getStringValue();
        String reverse = new StringBuilder(stringValue).reverse().toString();
        return stringValue.equals(reverse);
    }

    private boolean isGapful(Number number) {
        long numberValue = number.getNumberValue();
        String stringValue = number.getStringValue();
        int length = stringValue.length();
        String firstNumber = stringValue.substring(0, 1);
        String lastNumber = stringValue.substring(length - 1);
        long divider = Long.parseLong(firstNumber + lastNumber);

        return length >= 3 && numberValue % divider == 0;
    }

    private boolean isSpy(Number number) {
        String stringValue = number.getStringValue();
        Long sum = 0L;
        Long product = 1L;
        String[] numberArray = stringValue.split("");
        for (String nr: numberArray) {
            long l = Long.parseLong(nr);
            sum += l;
            product *= l;
        }
        return sum.equals(product);
    }

    private boolean isSquare(Long numberValue) {
        if (numberValue < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(numberValue);
        return Math.pow(sqrt, 2) == numberValue;
    }

    private boolean isSunny(Long numberValue) {
        return isSquare(numberValue + 1);
    }

    private NumberProperty createProperty(String name, boolean booleanValue) {
        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setName(name);
        numberProperty.setBooleanValue(booleanValue);
        numberProperty.setResult(getResult(name, booleanValue));
        return numberProperty;
    }

    private String getResult(String name, boolean booleanValue) {
        return name + ": " + booleanValue;
    }
}
