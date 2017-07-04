package org.frc4079.VirtualBot.Control;

public class Timer{
  long start;
  boolean hasStarted;
  double speed_scale = 1;
  
  public Timer(){
    
  }
  
  public Timer(double speed_scale){
	  this.speed_scale = speed_scale;
  }
  
  public long start(){
    if(!hasStarted){
      start = System.currentTimeMillis();
      hasStarted = true;
    }
    return start;
  }
  
  public void reset(){
    start = 0;
    hasStarted = false;
  }
  
  public void restart(){
    reset();
    start();
  }
  
  public double get(){
    return (System.currentTimeMillis() - start) * speed_scale;
  }
}