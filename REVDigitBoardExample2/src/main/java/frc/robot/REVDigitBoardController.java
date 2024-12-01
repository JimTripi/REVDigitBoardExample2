package frc.robot;

public class REVDigitBoardController extends REVDigitBoard {

    // public RobotColorEnum m_RobotColor = RobotColorEnum.eRed;
    // public RobotColorEnum toggleRobotColor() { 
    //     return (m_RobotColor == RobotColorEnum.eRed) ? RobotColorEnum.eBlue : RobotColorEnum.eRed; 
    // }

    // public enum RobotStartPositionEnum { eOne, eTwo, eThree };
    // public RobotStartPositionEnum m_RobotStartPosition = RobotStartPositionEnum.eOne;
    

    public enum RobotColorEnum { eRed, eBlue };
    public RobotColorEnum m_RobotColor = RobotColorEnum.eBlue;   //  restrict to R or B (red or blue)
    public void setRobotColor(RobotColorEnum InColor) {
        m_RobotColor = (InColor == RobotColorEnum.eRed) ? RobotColorEnum.eBlue : RobotColorEnum.eRed;
    }
    public RobotColorEnum getRobotColor() { return (m_RobotColor == RobotColorEnum.eRed) ? RobotColorEnum.eBlue : RobotColorEnum.eRed; }
    public String getRobotColorString() { return ((m_RobotColor == RobotColorEnum.eRed) ? "R" : "B"); }

    public String m_StartPositionNumber = "1";  // Restrict to characters 1,2,3
    public String m_ScenarioNumberForPosition = "1";  // Restrict to characters 1,2,3,... for each Start Position.

    public void logRevDigitBoardControllerState() {

        System.out.println("RevDigitBoardController>" +  
            "  Color: " + getRobotColorString() + 
            "  StartPosition: " + m_StartPositionNumber + 
            "  Scenario: " + m_ScenarioNumberForPosition +
            "  ButtonA: " + getButtonA() + 
            "  ButtonB: " + getButtonB() + 
            "  Pot: " + getAdjustPotentiometerVoltage() );

        String FourAlphaNumberics = getRobotColorString() + m_StartPositionNumber + "0" + m_ScenarioNumberForPosition;
        display(FourAlphaNumberics);

    }
  
}
