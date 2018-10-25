package sample;

import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class BonusLevel {
    private Map<Button, Integer> buttonMap;
    private int valueToCheck;


    public Map<Button, Integer> getButtonMap() {
        return buttonMap;
    }

    public BonusLevel() {
        this.buttonMap = new HashMap<>();
    }

    public void setButtonMap(Map<Button, Integer> buttonMap) {
        this.buttonMap = buttonMap;
    }

    public int getValueToCheck() {
        return valueToCheck;
    }

    public void setValueToCheck(int valueToCheck) {
        this.valueToCheck = valueToCheck;
    }
}
