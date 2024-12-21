// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.REVDigitBoardController.RobotColorEnum;
import frc.lib.AftershockXboxController;

public class RobotContainer {

  //private final REVDigitBoard revDigitBoard = new REVDigitBoard();
  private final REVDigitBoardController revDigitBoardController = new REVDigitBoardController();
  private final AftershockXboxController mControllerPrimary = new AftershockXboxController(0);
  
  public boolean test = false;

  public RobotContainer() {
    configureBindings();
    System.out.println("RobotContainer constructor");
  }

  public void initialize() {
    revDigitBoardController.clear();
    revDigitBoardController.display("DEAD");
  }

  private void configureBindings() {

    System.out.println("RobotContainer.configureBindings()");

    // Bindings to not trigger unless RoboRIO is enabled on Driver Station.

    // Multiply manual drive by a Large Number (blame Enzo)
    Trigger turboButton = new Trigger(()-> mControllerPrimary.getRawButton(1)); // XBoxController A button
    turboButton.onTrue(new InstantCommand(()->{
      test = true;
      System.out.println("XboxController 1 Pressed");
    })).onFalse(new InstantCommand(()->{
      test = false;
      System.out.println("XboxController 1 Released");
    }));


  }

  public void processRevDigitBoardController() {
    revDigitBoardController.processRevDigitBoardController();
  }


  //   Trigger TriggerA = new Trigger(() -> revDigitBoardController.getButtonA());
  //   TriggerA.onTrue(new InstantCommand(()->{
  //     revDigitBoardController.setRobotColor(RobotColorEnum.eRed);
  //     revDigitBoardController.m_RobotColor = RobotColorEnum.eRed;
  //     System.out.println("A Pressed");
  //   }))
  //   .onFalse(new InstantCommand(()->{
  //     System.out.println("A NOT PRESSED");
  //   }))
  //   ;

  //   Trigger TriggerB = new Trigger(() -> revDigitBoardController.getButtonB());
  //   TriggerB.onTrue(new InstantCommand(()->{
  //     revDigitBoardController.setRobotColor(RobotColorEnum.eBlue);
  //     revDigitBoardController.m_RobotColor = RobotColorEnum.eBlue;
  //     System.out.println("B Pressed");
  //   }))
  //   .onFalse(new InstantCommand(()->{
  //     System.out.println("B NOT PRESSED");
  //   }))
  //   ;
  // }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
