package com.swerve;

public class TestStickConversion {

	
	 /* @param fwd	-1.0 to 1.0, forward to reverse velocity
	 * @param str	-1.0 to 1.0, left to right velocity
	 * @param rcw	-1.0 to 1.0, clockwise rotational velocity
	 * @return		Array of Doubles matching ws1-ws4 and wa1-wa4
	 */
	static double joystickDeadband = 0.10;
	
	public static void execute(double forwardAxis){
		
		// logic in Teleop.execute()
		//---------------------------
		double fwd = (Math.abs(forwardAxis) > joystickDeadband) ? forwardAxis : 0.0;

		if(Math.abs(fwd)>1.0){
			System.out.println(String.format("EXIT forwardAxis: %5.2f, fwd: %5.2f  NO SWERVE", forwardAxis, fwd));
			return;
		}
		//---------------------------
		
		System.out.println(String.format("calculating swerve and set drive Output with forwardAxis: %5.2f fwd: %5.2f", forwardAxis, fwd));
	}
	static double MIN_STICK = -5.0;
	static double MAX_STICK = 5.0;

	public static void main(String args[]){
		for(double forwardAxis = MIN_STICK;forwardAxis < MAX_STICK;forwardAxis+=.2){
			execute(forwardAxis);
		}
	}
}
