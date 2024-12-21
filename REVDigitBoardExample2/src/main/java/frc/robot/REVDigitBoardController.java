package frc.robot;

import javax.lang.model.util.ElementScanner14;

public class REVDigitBoardController extends REVDigitBoard {

    // Alliance Color
    public enum RobotColorEnum { eRed, eBlue };
    public RobotColorEnum m_RobotColor = RobotColorEnum.eBlue;   //  restrict to R or B (red or blue)
    public RobotColorEnum getRobotColor() { return (m_RobotColor == RobotColorEnum.eRed) ? RobotColorEnum.eBlue : RobotColorEnum.eRed; }
    public String getRobotColorString() { return ((m_RobotColor == RobotColorEnum.eRed) ? "R" : "B"); }
    public void setRobotColor(RobotColorEnum InColor) {
        m_RobotColor = InColor;
    }
    public void toggleRobotColor() {
        m_RobotColor = (m_RobotColor == RobotColorEnum.eRed) ? RobotColorEnum.eBlue : RobotColorEnum.eRed;
    }

    // Robot starting position
    public int m_StartPositionNumber = 1;  // Restrict to characters 1,2,3
    public int getRobotStartPositionNumber() { return m_StartPositionNumber; }
    public String getRobotStartPositionNumberString() { return String.valueOf(m_StartPositionNumber); }
    public void setRobotStartPositionNumber(int startPositionNumber) {
        m_StartPositionNumber = startPositionNumber;
    }
    public void incrementRobotStartPosition() { m_StartPositionNumber += 1; }  // don't know for each position the cap on sequence numbers.
    public void decrementRobotStartPosition() { 
        m_StartPositionNumber -= 1;  
        if (m_StartPositionNumber < 1) m_StartPositionNumber = 1; 
    }

    // The autonomous scenario to be executed for the selected starting position of the selected alliance color.
    public int m_ScenarioNumberForPosition = 1;  // Restrict to characters 1,2,3,... for each Start Position.
    public int getScenarioNumberForPosition() { return m_StartPositionNumber; }
    public String getScenarioNumberForPositionString() { return String.valueOf(m_ScenarioNumberForPosition); }
    public void setScenarioNumberForPosition(int startScenarioNumber) {
        m_ScenarioNumberForPosition = startScenarioNumber;
    }
    public void incrementScenarioNumberForPosition() { m_ScenarioNumberForPosition += 1; }  // don't know for each position the cap on sequence numbers.
    public void decrementScenarioNumberForPosition() { 
        m_ScenarioNumberForPosition -= 1;  
        if (m_ScenarioNumberForPosition < 1) m_ScenarioNumberForPosition = 1; 
    }

    // Update the digit board's display
    public void updateDigitBoardLEDs() {
        String FourAlphaNumberics = "DEAD";
        switch (mEditState) {
            case eNothing:
                if (m_ScenarioNumberForPosition < 10)
                    FourAlphaNumberics = getRobotColorString() + m_StartPositionNumber + "0" + m_ScenarioNumberForPosition;
                else 
                    FourAlphaNumberics = getRobotColorString() + m_StartPositionNumber + m_ScenarioNumberForPosition;
                break;
            case eColor:
                FourAlphaNumberics = getRobotColorString() +"   ";
                break;
            case ePosition:
                FourAlphaNumberics = " " + m_StartPositionNumber + "  ";
                break;
            case eScenario:
                if (m_ScenarioNumberForPosition < 10)
                    FourAlphaNumberics = "   " + m_ScenarioNumberForPosition;
                else 
                    FourAlphaNumberics = "  " + m_ScenarioNumberForPosition;
                break;
        }
        display(FourAlphaNumberics);
    }

    enum EditStateEnum { eNothing, eColor, ePosition, eScenario }
    EditStateEnum mEditState = EditStateEnum.eNothing; 
  
    public void processRevDigitBoardController() {
        if (getAdjustPotentiometerVoltage() > 3.75) {
            System.out.println("Edit Scenario");
            // Edit Scenario
            mEditState = EditStateEnum.eScenario; 
            setBlink2Hz();
            if (getButtonA()) {
                incrementScenarioNumberForPosition();
            } else if (getButtonB()) {
                decrementScenarioNumberForPosition();
            }
        } else if (getAdjustPotentiometerVoltage() > 2.5) {
            System.out.println("Edit Start Position");
            // Edit Start Position
            mEditState = EditStateEnum.ePosition; 
            setBlink2Hz();
            if (getButtonA()) {
                incrementRobotStartPosition();
            } else if (getButtonB()) {
                decrementRobotStartPosition();
            }
        } else if (getAdjustPotentiometerVoltage() > 1.25) {
            System.out.println("Edit Color");
            // Edit Color
            mEditState = EditStateEnum.eColor; 
            setBlink2Hz();
            if (getButtonA()) {
                toggleRobotColor();
            } else if (getButtonB()) {
                toggleRobotColor();
            }
        } else {
            System.out.println("Edit Nothing");
            // Nominal Mode (no editing)
            mEditState = EditStateEnum.eNothing; 
            setBlinkOff();
        }
  
        updateDigitBoardLEDs();
    
        logRevDigitBoardControllerState();
    }
  
  
    // Telemetry
    public void logRevDigitBoardControllerState() {
        System.out.println("RevDigitBoardController>" +  
            "  Color: " + getRobotColorString() + 
            "  StartPosition: " + m_StartPositionNumber + 
            "  Scenario: " + m_ScenarioNumberForPosition +
            "  ButtonA: " + getButtonA() + 
            "  ButtonB: " + getButtonB() + 
            "  Pot: " + getAdjustPotentiometerVoltage() );

    }
  
}
