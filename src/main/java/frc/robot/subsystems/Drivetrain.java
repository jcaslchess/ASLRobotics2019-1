/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

import frc.robot.commands.DrivetrainCommand;
/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX backLeft;
  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX backRight;

  private SpeedControllerGroup left;
  private SpeedControllerGroup right;
  private DifferentialDrive driveTrain;

  private DigitalInput l, c, r;


  public Drivetrain(){
    frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_DRIVE_MOTOR);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
    driveTrain = new DifferentialDrive(left, right);

    l = new DigitalInput(RobotMap.PHOTO_SWITCH_LEFT);
    c = new DigitalInput(RobotMap.PHOTO_SWITCH_CENTER);
    r = new DigitalInput(RobotMap.PHOTO_SWITCH_RIGHT);

  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    driveTrain.tankDrive(leftSpeed, rightSpeed);
  }
  public void arcadeDrive(double x, double z){
    driveTrain.arcadeDrive(Math.pow(x, 2), Math.pow(z, 2));
  }

  public String getPESensors(){
    String ret = "";
    ret += l.get()? "1" : "0";
    ret += c.get()? "1" : "0";
    ret += r.get()? "1" : "0";
    return ret;
  }

  public boolean getLeftSensor(){ return l.get(); }
  public boolean getCenterSensor(){ return c.get(); }
  public boolean getRightSensor(){ return r.get(); }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DrivetrainCommand());
  }
}
