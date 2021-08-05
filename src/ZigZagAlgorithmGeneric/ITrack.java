package ZigZagAlgorithmGeneric;

public class ITrack<T, V> {
	private T time;
	private V volume;
	private TrackType type;
	
	



	public ITrack(T time, V volume){
		this.time = time;
		this.volume = volume;
	}

	
	

	public TrackType getType() {
		return type;
	}




	public void setType(TrackType type) {
		this.type = type;
	}




	public T getTime() {
		return time;
	}


	public void setTime(T time) {
		this.time = time;
	}


	public V getVolume() {
		return volume;
	}


	public void setVolume(V volume) {
		this.volume = volume;
	}
}
