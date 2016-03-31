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

	public float distanceTo(Coordinates coords){
		/* MISSING */
	};
}