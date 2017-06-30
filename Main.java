import org.frc4079.VirtualBot.Control.Robot;
import org.frc4079.VirtualBot.Control.SpeedController;
import org.frc4079.VirtualBot.Control.Timer;

class Main{

  static boolean isEnabled = false;

  private static Robot robot;
  public static SpeedController left, right;
  
  public static void main(String[] args){
    robotInit();
    operatorControl();
  }
  
  public static void robotInit(){
    left = new SpeedController(0);
    right = new SpeedController(1, true);

    robot = new Robot(left, right);
    
    isEnabled = true;
  }
  
  public static void operatorControl(){
    Timer t = new Timer();
    t.start();
    
    Timer m = new Timer();
    m.start();
    
    while (isEnabled){
      /**
       * Your code starts here
       */
       
       if(t.get() < 1000){
         left.set(1);
         right.set(-1);
       }else{
         left.set(0);
         right.set(0);
         isEnabled = false;
       }
       
       /**
        * Your code ends here
        */
        
        
        //Please don't touch below
       if(m.get() >= 1){
         robot.updateVirtualMotors(t.get());
         //System.out.println(t.get() + ": " + left.getEncPos() + ", " + right.getEncPos());
         m.restart();
       }
    }
  }
}