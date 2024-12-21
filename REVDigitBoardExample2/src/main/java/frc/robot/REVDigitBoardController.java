package frc.robot;

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
    public void incrementScenarioNumberForPosition() { m_StartPositionNumber += 1; }  // don't know for each position the cap on sequence numbers.
    public void decrementScenarioNumberForPosition() { 
        m_ScenarioNumberForPosition -= 1;  
        if (m_ScenarioNumberForPosition < 1) m_ScenarioNumberForPosition = 1; 
    }

    // Update the digit board's display
    public void updateDigitBoardLEDs() {
        String FourAlphaNumberics = getRobotColorString() + m_StartPositionNumber + "0" + m_ScenarioNumberForPosition;
        display(FourAlphaNumberics);
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
