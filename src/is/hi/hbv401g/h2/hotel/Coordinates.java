package is.hi.hbv401g.h2.hotel;

public class Coordinates{

	private double latitude;
	private double longtitude;

	public Coordinates(double latitude, double longtitude){
		this.latitude = latitude;
		this.longtitude = longtitude;
	};

	public double[] getCoordinates(){
		double [] coordinates = {latitude, longtitude};
		return coordinates;
	};

	public double distanceTo(Coordinates coords){
		double x1 = this.latitude;
		double y1 = this.longtitude;
		double x2 = coords.latitude;
		double y2 = coords.longtitude;
		
		double x3 = x2 - x1;
		double y3 = y2 - y1;
		double result = Math.sqrt( x3*x3 + y3*y3);
		return result;
	};
}