package numbers;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static final String EXIT_REQUEST = "0";
    private static final String INSTRUCTIONS_REQUEST = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();

        while (true) {
            System.out.print("\nEnter a request: ");
            String input = scanner.nextLine().toLowerCase();
            String[] inputSplit = input.split(" ");
            if (input.equals(EXIT_REQUEST)) {
                break;
            }
            if (input.equals(INSTRUCTIONS_REQUEST)) {
                printInstructions();
                continue;
            }
            if (inputSplit.length == 1) {
                processSingleNumber(input);
            } else if (inputSplit.length == 2) {
                processNumbersInBetween(inputSplit[0],inputSplit[1]);
            } else {
                List<String> searchList = getSearchListWithoutAnyDuplicates(inputSplit);
                processNumbersInBetweenWithSpecificProperties(inputSplit[0], inputSplit[1], searchList);
            }
        }
        System.out.println("\nGoodbye!");
    }

    private static void printInstructions() {
        System.out.print("""
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    private static void processSingleNumber(String input) {
        try {
            Long naturalNumber = ValidationService.getValidFirstNaturalNumber(input);
            Number number = new Number(naturalNumber);
            ValidationService.validateNaturalNumber(number.getNumberValue());
            processNumberProperties(number);
            PrintService printService = new PrintService();
            printService.printNumber(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processNumbersInBetween(String firstNumberInput, String secondNumberInput) {
        List<Number> numbersInBetween = getNumbersInBetween(firstNumberInput, secondNumberInput);
        PrintService printService = new PrintService();
        printService.printNumbers(numbersInBetween);
    }

    private static List<Number> getNumbersInBetween(String firstNumberInput, String secondNumberInput) {
        ArrayList<Number> numbers = new ArrayList<>();
        try {
            Long fromNumber = ValidationService.getValidFirstNaturalNumber(firstNumberInput);
            Long counter = ValidationService.getValidSecondNaturalNumber(secondNumberInput);
            Long toNumber = fromNumber + counter;
            for (Long i = fromNumber; i < toNumber; i++) {
                Number number = new Number(i);
                processNumberProperties(number);
                numbers.add(number);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }

    private static void processNumbersInBetweenWithSpecificProperties(String firstNumberInput, String secondNumberInput,
                                                                      List<String> searchList) {
        List<Number> numbers = getNumbersInBetweenSpecificProperties(firstNumberInput, secondNumberInput, searchList);
        PrintService printService = new PrintService();
        printService.printNumbers(numbers);
    }


    private static List<String> getSearchListWithoutAnyDuplicates(String[] inputSplit) {
        ArrayList<String> searchList = new ArrayList<>();
        for (int i = 2; i < inputSplit.length; i++) {
            searchList.add(inputSplit[i]);
        }
        return searchList.stream().distinct().collect(Collectors.toList());
    }


    private static List<Number> getNumbersInBetweenSpecificProperties(String firstNumberInput, String secondNumberInput, List<String> searchList) {
        List<Number> numbers = new ArrayList<>();
        try {
            Long fromNumber = ValidationService.getValidFirstNaturalNumber(firstNumberInput);
            Long counter = ValidationService.getValidSecondNaturalNumber(secondNumberInput);
            ValidationService.validateAllowedPropertiesSearch(searchList);
            ValidationService.validateMutuallyExclusiveProperties(searchList);
            while (counter > 0) {
                Number number = new Number(fromNumber);
                processNumberProperties(number);
                if (isNumberRequired(number, searchList)) {
                    numbers.add(number);
                    counter--;
                }
                fromNumber++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }

    private static boolean isNumberRequired(Number number, List<String> searchList) {
        List<String> includesSearchList = createIncludesSearchList(searchList);
        List<String> exludesSearchList = createExludesSearchList(searchList);
        return number.getAllTrueProperties().containsAll(includesSearchList)
                && !number.getAllTrueProperties().stream().anyMatch(exludesSearchList::contains);

    }

    private static List<String> createExludesSearchList(List<String> searchList) {
        ArrayList<String> excludesSearchList = new ArrayList<>();
        for (String searchCriteria : searchList) {
            if (searchCriteria.startsWith("-")) {
                excludesSearchList.add(searchCriteria.replace("-", ""));
            }
        }
        return excludesSearchList;

    }

    private static List<String> createIncludesSearchList(List<String> searchList) {
        ArrayList<String> includesSearchList = new ArrayList<>();
        for (String searchCriteria : searchList) {
            if (!searchCriteria.startsWith("-")) {
                includesSearchList.add(searchCriteria);
            }
        }
        return includesSearchList;
    }

    private static void processNumberProperties(Number number) {
        NumberService numberService = new NumberService();
        numberService.processEvenAndOddProperty(number);
        numberService.processBuzzProperty(number);
        numberService.processDuckProperty(number);
        numberService.processPalindromicProperty(number);
        numberService.processGapfulProperty(number);
        numberService.processSpyProperty(number);
        numberService.processSquareProperty(number);
        numberService.processSunnyProperty(number);
        numberService.processJumpingProperty(number);
        numberService.processHappyAndSadProperty(number);
        numberService.createAndSetAllTrueProperties(number);
    }
}
