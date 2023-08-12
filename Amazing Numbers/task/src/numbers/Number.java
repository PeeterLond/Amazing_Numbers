package numbers;

import java.util.ArrayList;
import java.util.List;

public class Number {
    private String stringValue;
    private long numberValue;
    private List<NumberProperty> numberProperties = new ArrayList<>();
    private List<String> allTrueProperties = new ArrayList<>();

    public Number(Long input) {
        this.stringValue = input.toString();
        this.numberValue = input;
    }

    public long getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(long numberValue) {
        this.numberValue = numberValue;
    }

    public List<NumberProperty> getNumberProperties() {
        return numberProperties;
    }

    public void setNumberProperties(List<NumberProperty> numberProperties) {
        this.numberProperties = numberProperties;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List<String> getAllTrueProperties() {
        return allTrueProperties;
    }

    public void setAllTrueProperties(List<String> allTrueProperties) {
        this.allTrueProperties = allTrueProperties;
    }
}
