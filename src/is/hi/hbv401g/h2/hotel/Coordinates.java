package is.hi.hbv401g.h2.hotel;

public class Coordinates{

	private float longtitude;
	private float latitude;

	public Coordinates(float longtitude, float latitude){
		this.longtitude = longtitude;
		this.latitude = latitude;
	};

	public float[] getCoordinates(){
		float [] coordinates = {longtitude, latitude};
		return coordinates;
	};

	// NOT FINISHED
	public double distanceTo(Coordinates coords){
		/* MISSING LOGIC*/
		return 0.0;
	};
}