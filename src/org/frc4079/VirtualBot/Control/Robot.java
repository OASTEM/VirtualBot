package org.frc4079.VirtualBot.Control;

public class Robot {
    private SpeedController left, right;
    public final double MAX_SPEED = 15; //feet per second
    public final double WHEEL_CIR = 6 * Math.PI; // in inches
    public final double GEAR_RATIO = 7.3;
    public final double TP_REV = 80; //ticks per revolution
    public final double TIMER = 500;
    public final double SPEED_SCALE = 10;
    // units of SPEED_MOD is: encoder ticks per timer ms
    private final double SPEED_MOD = MAX_SPEED * 12 * GEAR_RATIO * TP_REV / WHEEL_CIR;
    //private final double SPEED_MOD = MAX_SPEED * 12 / 39.4;
    //private final double SPEED_MOD = 557.64.6;

    // constant for smoothing out data points
    private final double RAMP_RATE = 0.3;
    private double lastLeftVel = -1.0;
    private double lastRightVel = -1.0;

    private String speedValues = "";

    public Robot(SpeedController left, SpeedController right) {
        this.left = left;
        this.right = right;
    }

    public String getSpeedValues() {
        return this.speedValues;
    }

    public SpeedController getMotor(Direction d) {
        switch(d) {
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
        // scale each wheel to a proportion of the speed of the wheel.
        // limited to the maximum speed denoted by SPEED_MOD.
        double lMove = left.speed == 0 ? 0 : (left.speed * SPEED_MOD);
        double rMove = right.speed == 0 ? 0 : (right.speed * SPEED_MOD);

        // smooth out data points so that there's no sudden decel or accel
        if (lastLeftVel != -1.0) {
            lMove = RAMP_RATE * lastLeftVel + (1 - RAMP_RATE) * lMove;
            rMove = RAMP_RATE * lastRightVel + (1 - RAMP_RATE) * rMove;
        }
        
        lastLeftVel = lMove;
        lastRightVel = rMove;

        left.distance += lMove/1000 * TIMER;
        right.distance += rMove/1000 * TIMER;
        speedValues += (m + ", " + lMove + ", " + (-rMove) + "\n");
    }

	public double getTicksToMetersConvFactor() {
		// TODO Auto-generated method stub
		return 1 / TP_REV / GEAR_RATIO * WHEEL_CIR / 39.4;
	}
}
