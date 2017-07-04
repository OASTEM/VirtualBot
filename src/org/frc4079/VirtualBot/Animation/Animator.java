package org.frc4079.VirtualBot.Animation;

import java.util.ArrayList;

public class Animator {
	
	private static final double PADDING = 0.0;
	private static final double CANVAS_X = 100.0, CANVAS_Y = 100.0;
	
	public static String getGlobalValuesScaled(String v){
		System.out.println("Speed Values:");
		System.out.println(v);
		
		String[] sets = v.split("\n");
		double d2r_90 = Math.PI/2;
		
		double x_0 = 0, y_0 = 0, theta_0 = d2r_90, s = 1000;
		
		ArrayList<ArrayList<Double>> arr = new ArrayList<ArrayList<Double>>();
		
		ArrayList<Double> set1 = new ArrayList<Double>();
		set1.add(0.0D);
		set1.add(x_0);
		set1.add(y_0);
		set1.add(theta_0 * 180/Math.PI);
		
		arr.add(set1);
		for(String i : sets){
			String[] points = i.split(", ");
			double R = Double.parseDouble(points[1]);
			double L = Double.parseDouble(points[2]);

			double r, x_1, y_1, theta_1;
			
			if (R == L){
				r = 0;
				 x_1 = x_0 + Math.cos(theta_0) * R;
				 y_1 = y_0 - Math.sin(theta_0) * R;
				theta_1 = theta_0;
			}else{
				r = (R+L)*s/(2*(L-R));
			
				//double d_theta = -(R/(r-s/2));
				double d_theta = -((L-R)/s);
				theta_1 = theta_0 + d_theta;
				
				double d_x = r * Math.cos(theta_1 + d2r_90) - Math.cos(theta_0 + d2r_90);
				double d_y = r * Math.sin(theta_1 + d2r_90) - Math.sin(theta_0 + d2r_90);
				
				 x_1 = x_0 + d_x;
				 y_1 = y_0 - d_y;
			}
			
			ArrayList<Double> curr = new ArrayList<Double>();
			
			curr.add(Double.parseDouble(points[0]));
			curr.add(x_1);
			curr.add(y_1);
			curr.add(theta_1 * 180/Math.PI);
			
			x_0 = x_1;
			y_0 = y_1;
			theta_0 = theta_1;
			
			arr.add(curr);
		}
		
		String res = "[";
		
		double max_x = Double.MIN_VALUE, min_x = Double.MAX_VALUE;
		double max_y = Double.MIN_VALUE, min_y = Double.MAX_VALUE;
		
		for(int i = 0; i < arr.size(); i++){
			ArrayList<Double> curr = arr.get(i);
			if(curr.get(1) > max_x) max_x = curr.get(1);
			if(curr.get(1) < min_x) min_x = curr.get(1);
			if(curr.get(2) > max_y) max_y = curr.get(2);
			if(curr.get(2) < min_y) min_y = curr.get(2);
		}
		
		double scale_factor, range_x = max_x - min_x, range_y = max_y - min_y;
		if(range_x > range_y){
			scale_factor = ((range_x + PADDING * 2)*2) / CANVAS_X;
		}else{
			scale_factor = ((range_y + PADDING * 2)*2) / CANVAS_Y;
		}
		
		//scale_factor = 5;
		
		System.out.println("X-Range (ticks): " + range_x);
		System.out.println("Y-Range (ticks): " + range_y);
		System.out.println("Scale Factor: " + scale_factor);
		System.out.println("Robot Size (px): " + s / scale_factor);
		System.out.println("X-Canvas (px): " + CANVAS_X);
		System.out.println("Y-Canvas (px): " + CANVAS_Y);
		
		for(int i = 0; i < arr.size(); i++){
			ArrayList<Double> curr = arr.get(i);
			res += "{\"x\":" + (curr.get(1)/scale_factor) + ", \"y\":" + (curr.get(2)/scale_factor) + ", \"rot\":" + (curr.get(3)-90) + "},\n";
		}
		
		res += "]";
		
		return res;
	}
	
	/**public static String getGlobalValuesWrapped(String v){
		String[] sets = v.split("\n");
		double d2r_90 = Math.PI/2;
		
		double x_0 = 0, y_0 = 0, theta_0 = d2r_90, s = 300;
		
		ArrayList<ArrayList<Double>> arr = new ArrayList<ArrayList<Double>>();
		
		ArrayList<Double> set1 = new ArrayList<Double>();
		set1.add(0.0D);
		set1.add(x_0);
		set1.add(y_0);
		set1.add(theta_0);
		
		arr.add(set1);
		for(String i : sets){
			String[] points = i.split(", ");
			double R = Double.parseDouble(points[1]);
			double L = Double.parseDouble(points[2]);

			double r, x_1, y_1, theta_1;
			
			if (R == L){
				r = 0;
				 x_1 = x_0 + Math.cos(theta_0) * R;
				 y_1 = y_0 + Math.sin(theta_0) * R;
				theta_1 = theta_0;
			}else{
				r = (R+L)*s/(2*(L-R));
			
				//double d_theta = -(R/(r-s/2));
				double d_theta = -((L-R)/s);
				theta_1 = theta_0 + d_theta;
				
				double d_x = r * Math.cos(theta_1 + d2r_90) - Math.cos(theta_0 + d2r_90);
				double d_y = r * Math.sin(theta_1 + d2r_90) - Math.sin(theta_0 + d2r_90);
				
				 x_1 = x_0 + d_x;
				 y_1 = y_0 + d_y;
			}
			
			ArrayList<Double> curr = new ArrayList<Double>();
			
			curr.add(Double.parseDouble(points[0]));
			curr.add(x_1);
			curr.add(y_1);
			curr.add(theta_1 * 180/Math.PI);
			
			x_0 = x_1;
			y_0 = y_1;
			theta_0 = theta_1;
			
			arr.add(curr);
		}
		
		String res = "";
		
		double max_x = Double.MIN_VALUE, min_x = Double.MAX_VALUE;
		double max_y = Double.MIN_VALUE, min_y = Double.MAX_VALUE;
		
		for(int i = 0; i < arr.size(); i++){
			ArrayList<Double> curr = arr.get(i);
			if(curr.get(1) > max_x) max_x = curr.get(1);
			if(curr.get(1) < min_x) min_x = curr.get(1);
			if(curr.get(2) > max_y) max_y = curr.get(2);
			if(curr.get(2) < min_y) min_y = curr.get(2);
		}
		
		double range_x = max_x - min_x;
		
		return res;
	}*/
	
	public static double addTheta(double theta_0, double theta_add){
		return (theta_0 + theta_add) % 360;
	}
}
