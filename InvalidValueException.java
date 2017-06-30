package org.frc4079.VirtualBot.Control;

import java.io.*;

 public class InvalidValueException extends Exception 
 {
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
