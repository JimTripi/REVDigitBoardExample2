// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.REVDigitBoardController.RobotColorEnum;

public class RobotContainer {

  //private final REVDigitBoard revDigitBoard = new REVDigitBoard();
  private final REVDigitBoardController revDigitBoardController = new REVDigitBoardController();

  public RobotContainer() {
    configureBindings();
  }

  public void initialize() {
    revDigitBoardController.clear();
    revDigitBoardController.display("DEAD");
  }

  private void configureBindings() {

    Trigger TriggerA = new Trigger(() -> revDigitBoardController.getButtonA());
    TriggerA.onTrue(new InstantCommand(()->{
      revDigitBoardController.setRobotColor(RobotColorEnum.eRed);
      System.out.println("A Pressed");
    })).onFalse(new InstantCommand(()->{
      revDigitBoardController.setRobotColor(RobotColorEnum.eBlue);
      System.out.println("A NOT PRESSED");
    }));

    Trigger TriggerB = new Trigger(() -> revDigitBoardController.getButtonB());

  }

  public void printRevDigitBoardControllerState() {
    revDigitBoardController.logRevDigitBoardControllerState();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
