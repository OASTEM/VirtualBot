package org.frc4079.VirtualBot.Control;

public class Robot{
  private SpeedController left, right;
  private final double SPEED_MOD = 5.576;
  
  public Robot(SpeedController left, SpeedController right){
    this.left = left;
    this.right = right;
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
    double lMove = (left.speed * SPEED_MOD + (Math.random()*2) - 1);
    double rMove = (right.speed * SPEED_MOD + (Math.random()*2) - 1);
    left.distance += lMove;
    right.distance += rMove;
    
    System.out.println(m + ": " + lMove + ", " + (-rMove));
  }
}