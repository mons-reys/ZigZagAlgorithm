package ZigZagAlgorithm;

import java.util.List;

public interface IZigzag {
	public List<Double> extremumsFinder(int size, List<Double> values, double percentage);
	public List<Double> extremasFilter(List<Double> values, double percentage);
}
