package ZigZagAlgorithmGeneric;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Tra {
	double volume;
	
	@JsonDeserialize(using = DateHandler.class)
	Date date;
	
	
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
