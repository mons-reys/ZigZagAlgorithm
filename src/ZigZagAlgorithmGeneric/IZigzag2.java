package ZigZagAlgorithmGeneric;

import java.util.List;

public interface IZigzag2<T> {
	
	public boolean isMaximum(double previous, double current, double next);
	public boolean isMinimum(double previous, double current, double next);

	public boolean isTrueExtremum(double previous, double current , double percentage);
	
	public List<T> extremumsFinder(double percentage);
	
	
	
	public double  calculateVolume(List<T> tracks);
	public List<Track> filter(List<Track> tracks);
}
