/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorMoveCommand extends Command {
  public ElevatorMoveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.ELEVATOR);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.ELEVATOR.stopMotor();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.ELEVATOR.setMotorSpeed(-Robot.oi.OPERATOR.getRawAxis(5));
    Robot.ELEVATOR.setMotorSpeed(Robot.oi.OPERATOR.getRawAxis(RobotMap.OPERATOR_ELEVATOR_AXIS));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ELEVATOR.stopMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
