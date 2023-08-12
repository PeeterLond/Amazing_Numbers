package numbers;

import java.util.List;
import java.util.Locale;

public class PrintService {

    public void printNumber(Number number) {
        List<NumberProperty> numberProperties = number.getNumberProperties();
        String formattedNr = String.format(Locale.US, "%,d", number.getNumberValue());
        System.out.println("\nProperties of " + formattedNr);
        for (NumberProperty numberProperty : numberProperties) {
            System.out.println(numberProperty.getResult());
        }
    }

    public void printNumbers(List<Number> numbersInBetween) {
        System.out.println();
        for (Number number : numbersInBetween) {
            String formattedNr = String.format(Locale.US, "%,d", number.getNumberValue());
            System.out.println(formattedNr + " is "+ getPropertiesString(number));
        }
    }

    private String getPropertiesString(Number number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String propertyName : number.getAllTrueProperties()) {
            stringBuilder.append(propertyName).append(", ");
        }
        String propertiesString = stringBuilder.toString();
        if (propertiesString.isEmpty()) {
            return "";
        }
        return propertiesString.substring(0, propertiesString.length() - 2);
    }


}
