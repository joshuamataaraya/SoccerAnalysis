package Logic.ImageProcessor;

public abstract class ImageProcessor {

	public ImageProcessor() {};
	
	public abstract Object mask(Object image, Object alphaMin, Object alphaMax);
	public abstract Object rgb2hsv(Object image);
	public abstract Object dilate(Object image);
	public abstract Object complement(Object image);
	public abstract Object or(Object image1, Object image2);
	
}
