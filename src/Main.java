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
    
    int state = 0;
    
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
       
       switch(state){
         case 0:
           if(t.get() < 500){
             left.set(0.5);
             right.set(-.5);
           }else{
             state = 1;
             t.restart();
           }
           break;
          case 1:
            if(t.get() < 100){
              left.set(0);
              right.set(-0.25);
            }else{
              state = 2;
              t.restart();
            }
            break;
          case 2:
            if(t.get() < 500){
             left.set(.5);
             right.set(-.5);
           }else{
             left.set(0);
             right.set(0);
             
             state = 3;
           }
           break;
          case 3: default:
           isEnabled = false;
           break;
       }
       
       
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
    
    System.out.println(Animator.getGlobalValuesScaled(robot.getSpeedValues()));
    
  }
}