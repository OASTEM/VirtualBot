import org.frc4079.VirtualBot.Animation.Animator;
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
      
    Timer t = new Timer(robot.SPEED_SCALE);
    t.start();
    
    Timer c = new Timer(robot.SPEED_SCALE);
    c.start();

    Timer m = new Timer(robot.SPEED_SCALE);
    m.start();
    
    while (isEnabled){
      
    	/**
    	 * Your code starts here
    	 */
    	
    	
    	//REPLACE ME
    	
       /**
        * Your code ends here
        */
        
        
        //Please don't touch below
       if(m.get() >= (robot.TIMER) ){
         robot.updateVirtualMotors(c.get());
         //System.out.println(c.get() + ", " + left.getEncPos() + ", " + right.getEncPos());
         m.restart();
       }
    }
    
    System.out.println(Animator.getJSON(robot.getSpeedValues(), robot.getTicksToMetersConvFactor()));
    //System.out.println(Animator.getGlobalValuesScaled(robot.getSpeedValues()));
    
  }
}