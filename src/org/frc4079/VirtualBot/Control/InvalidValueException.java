package org.frc4079.VirtualBot.Control;

 public class InvalidValueException extends Exception 
 {
   /**
	 * 
	 */
private static final long serialVersionUID = 2170077574331935799L;
private double value;
   public InvalidValueException(double value)
   {
      this.value = value;
   } 
   public double getValue()
   {
      return value;
   }
 }
