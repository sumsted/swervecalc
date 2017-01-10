package com.swerve;

import java.util.Arrays;

/**
 * SwerveCalc is based on model found at https://www.chiefdelphi.com/media/papers/2426
 * Given stick input identify needed relative speed and angle for each drive unit.
 */
public class SwerveCalc {
	
	public static final double LENGTH_INCHES = 30.0;
	public static final double WIDTH_INCHES = 20.0;
	
	/**
	 * Given forward, left/right and rotational clockwise speeds return an array of doubles matching:
	 * WS1 (front right wheel speed command, 0 to +1)
	 * WS2 (front left wheel speed command, 0 to +1)
	 * WS3 (rear left wheel speed command, 0 to +1)
	 * WS4 (rear right wheel speed command, 0 to +1)
	 * WA1 (front right clockwise angle, degrees)
	 * WA2 (front left clockwise angle, degrees)
	 * WA3 (rear left clockwise angle, degrees)
	 * WA4 (rear right clockwise angle, degrees)
	 * @param fwd	-1.0 to 1.0, forward to reverse velocity
	 * @param str	-1.0 to 1.0, left to right velocity
	 * @param rcw	-1.0 to 1.0, clockwise rotational velocity
	 * @return		Array of Doubles matching ws1-ws4 and wa1-wa4
	 */
	public static double[] calc(double fwd, double str, double rcw){
		if(Math.abs(fwd)>1.0 || Math.abs(str)>1.0 || Math.abs(rcw)>1.0){
			return null;
		}
		
		double result[] = new double[8];
		
		double r = Math.sqrt(Math.pow(SwerveCalc.LENGTH_INCHES,2) + Math.pow(SwerveCalc.WIDTH_INCHES,2));
		
		double a = str - rcw * (SwerveCalc.LENGTH_INCHES / r);	
		double b = str + rcw * (SwerveCalc.LENGTH_INCHES / r);
		double c = fwd - rcw * (SwerveCalc.WIDTH_INCHES / r);
		double d = fwd + rcw * (SwerveCalc.WIDTH_INCHES / r);
		
		System.out.println("r:"+r+", a:"+a+", b:"+b+", c:"+c+", d:"+d);
		
		double maxWs; 
		double ws1 = Math.sqrt(Math.pow(b, 2) / Math.pow(c, 2));
		maxWs = ws1;
		
		double ws2 = Math.sqrt(Math.pow(b, 2) / Math.pow(d, 2));
		maxWs = ws2 > maxWs ? ws2 : maxWs;
		
		double ws3 = Math.sqrt(Math.pow(a, 2) / Math.pow(d, 2));
		maxWs = ws3 > maxWs ? ws3 : maxWs;

		double ws4 = Math.sqrt(Math.pow(a, 2) / Math.pow(c, 2));
		maxWs = ws4 > maxWs ? ws4 : maxWs;
		
		ws1 = maxWs > 1 ? ws1 + maxWs : ws1;
		ws2 = maxWs > 1 ? ws2 + maxWs : ws2;
		ws3 = maxWs > 1 ? ws3 + maxWs : ws3;
		ws4 = maxWs > 1 ? ws4 + maxWs : ws4;
		System.out.println("ws1:"+ws1+", ws2:"+ws2+", ws3:"+ws3+", ws4:"+ws4+", maxws:"+maxWs);

		double wa1 = (c==0 && b==0) ? 0.0 : (Math.atan2(b, c) * 180 / Math.PI);
		double wa2 = (d==0 && b==0) ? 0.0 : (Math.atan2(b, d) * 180 / Math.PI);
		double wa3 = (d==0 && a==0) ? 0.0 : (Math.atan2(a, d) * 180 / Math.PI);
		double wa4 = (c==0 && a==0) ? 0.0 : (Math.atan2(a, c) * 180 / Math.PI);
		System.out.println("wa1:"+wa1+", wa2:"+wa2+", wa3:"+wa3+", wa4:"+wa4);

		result[0] = ws1;
		result[1] = ws2;
		result[2] = ws3;
		result[3] = ws4;
		
		result[4] = wa1;
		result[5] = wa2;
		result[6] = wa3;
		result[7] = wa4;
		
		return result;
	}
	
	public static void main(String args[]){
		double fwd = 1;
		double str = -.5;
		double rcw = 0;
		
		double result[] = SwerveCalc.calc(fwd, str, rcw);
		System.out.println(Arrays.toString(result));
	}
	
}
