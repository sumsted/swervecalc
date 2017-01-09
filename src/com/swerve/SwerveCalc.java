package com.swerve;

import java.util.Arrays;

public class SwerveCalc {
	
	private static double  LENGTH_INCHES = 30.0;
	private static double WIDTH_INCHES = 24.0;
	
	public static Double[] calc(double fwd, double str, double rcw){
		if(Math.abs(fwd)>1.0 || Math.abs(str)>1.0 || Math.abs(rcw)>1.0){
			return null;
		}
		
		Double result[] = new Double[8];
		
		double r = Math.sqrt(Math.pow(SwerveCalc.LENGTH_INCHES,2) + Math.pow(SwerveCalc.WIDTH_INCHES,2));
		
		double a = str - rcw * (SwerveCalc.LENGTH_INCHES / r);	
		double b = str + rcw * (SwerveCalc.LENGTH_INCHES / r);
		double c = fwd - rcw * (SwerveCalc.WIDTH_INCHES / r);
		double d = fwd + rcw * (SwerveCalc.WIDTH_INCHES / r);
		
		System.out.println("r:"+r+", a:"+a+", b:"+b+", c:"+c+", d:"+d);
		
		double maxWs; 
		double ws1 = Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2));
		maxWs = ws1;
		
		double ws2 = Math.sqrt(Math.pow(b, 2) + Math.pow(d, 2));
		maxWs = ws2 > maxWs ? ws2 : maxWs;
		
		double ws3 = Math.sqrt(Math.pow(a, 2) + Math.pow(d, 2));
		maxWs = ws3 > maxWs ? ws3 : maxWs;

		double ws4 = Math.sqrt(Math.pow(a, 2) + Math.pow(c, 2));
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
		double fwd = 0.5;
		double str = 0.2;
		double rcw = 0.1;
		
		Double result[] = SwerveCalc.calc(fwd, str, rcw);
		System.out.println(Arrays.toString(result));
	}
	
}
