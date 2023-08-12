package numbers;

import java.util.List;

public class ValidationService {

    private ValidationService() {

    }

    public static void validateNaturalNumber(Long numberValue) throws Exception {
        if (numberValue < 1) {
            throw new Exception("The first parameter should be a natural number or zero.");
        }
    }

    public static Long getValidFirstNaturalNumber(String input) throws Exception {
        try {
            long longNumber = Long.parseLong(input);
            validateNaturalNumber(longNumber);
            return longNumber;
        } catch (NumberFormatException e) {
            throw new Exception("The first parameter should be a natural number or zero.");
        }


    }

    public static Long getValidSecondNaturalNumber(String secondNumberInput) throws Exception {
        try {
            long longNumber = Long.parseLong(secondNumberInput);
            if (longNumber < 1) {
                throw new Exception("The second parameter should be a natural number.");
            }
            return longNumber;
        } catch (NumberFormatException e) {
            throw new Exception("The second parameter should be a natural number.");
        }
    }

    public static void validateAllowedPropertiesSearch(List<String> searchList) throws Exception {
        List<String> notAllowedProperties = AllowedProperty.getNotAllowedPropertiesFromSearchList(searchList);
        if (notAllowedProperties.size() == 1){
            String notAllowedPropertiesAsString = getNotAllowedPropertiesAsString(notAllowedProperties);
            throw new Exception("The property [" + notAllowedPropertiesAsString + "] is wrong." +
                    "\nAvailable properties: [" + AllowedProperty.getAllowedPropertiesString() + "]");
        }
        if (notAllowedProperties.size() > 1) {
            String notAllowedPropertiesAsString = getNotAllowedPropertiesAsString(notAllowedProperties);
            throw new Exception("The properties [" + notAllowedPropertiesAsString + "] are wrong." +
                    "\nAvailable properties: [" + AllowedProperty.getAllowedPropertiesString() + "]");
        }
    }

    public static void validateMutuallyExclusiveProperties(List<String> searchList) throws Exception {
        String mutuallyExclusiveProperties = AllowedProperty.getMutuallyExclusivePropertiesStringIfExists(searchList);
        if (!mutuallyExclusiveProperties.isEmpty()) {
            throw new Exception("The request contains mutually exclusive properties: [" + mutuallyExclusiveProperties +
                    "]\nThere are no numbers with there properties.");
        }
    }

    private static String getNotAllowedPropertiesAsString(List<String> notAllowedProperties) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String notAllowedProperty : notAllowedProperties) {
            stringBuilder.append(notAllowedProperty).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString().toUpperCase();
    }

}
