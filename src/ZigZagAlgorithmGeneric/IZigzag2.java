package ZigZagAlgorithmGeneric;

import java.util.List;

public interface IZigzag2<T> {
	
	public boolean isMaximum(double previous, double current, double next);
	public boolean isMinimum(double previous, double current, double next);

	public boolean isTrueExtremum(double previous, double current , double percentage);
	
	public List<T> extremumsFinder(Long size, double percentage);

}
