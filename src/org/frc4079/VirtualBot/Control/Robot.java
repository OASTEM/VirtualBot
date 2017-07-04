package org.frc4079.VirtualBot.Control;

public class Robot{
  private SpeedController left, right;
  public final double MAX_SPEED = 15; //feet per second
  public final double WHEEL_CIR = 6 * Math.PI; // in inches
  public final double GEAR_RATIO = 7.3;
  public final double TP_REV = 80; //ticks per revolution
  public final double TIMER = 50;
  public final double SPEED_SCALE = 10;
  
  // units of SPEED_MOD is: encoder ticks per timer milliseconds
  private final double SPEED_MOD = MAX_SPEED / 1000 * 12 * GEAR_RATIO * TP_REV * TIMER / WHEEL_CIR;
  //private final double SPEED_MOD = 557.6;
  
  private String speedValues = "";
  
  public Robot(SpeedController left, SpeedController right){
    this.left = left;
    this.right = right;
  }
  
  public String getSpeedValues(){
	  return this.speedValues;
  }
  
  public SpeedController getMotor(Direction d){
    switch(d){
      case LEFT:
        return this.left;
      case RIGHT:
        return this.right;
      default:
        break;
    }
    return null;
  }
  
  public void updateVirtualMotors(double m){
    //double lMove = left.speed == 0 ? 0 : (left.speed * SPEED_MOD + (Math.random()*2) - 1);
    //double rMove = right.speed == 0 ? 0 : (right.speed * SPEED_MOD + (Math.random()*2) - 1);
	// scale each wheel to a proportion of the speed of the wheel.
	// limited to the maximum speed denoted by SPEED_MOD.
	double lMove = left.speed == 0 ? 0 : (left.speed * SPEED_MOD);
	double rMove = right.speed == 0 ? 0 : (right.speed * SPEED_MOD);
    left.distance += lMove;
    right.distance += rMove;
    speedValues += (m + ", " + lMove + ", " + (-rMove) + "\n");
  }
}
