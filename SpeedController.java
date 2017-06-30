package org.frc4079.VirtualBot.Control;

public class SpeedController{
  protected double speed, distance;
  boolean isReversed;
  
  public SpeedController(int port){
    this.distance = 0;
    this.speed = 0;
    this.isReversed = false;
  }
  
  public SpeedController(int port, boolean isReversed){
    this.distance = 0;
    this.speed = 0;
    this.isReversed = isReversed;
  }
  
  public void set(double speed){
    try{
      if(speed > 1 || speed < -1) throw new InvalidValueException(speed);
      else{
        this.speed = speed;
      }
    }catch (InvalidValueException e){
      e.printStackTrace();
    }
  }
  
  public double getEncPos(){
    return distance * (isReversed ? -1 : 1);
  }
}