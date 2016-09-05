package Logic.ImageProcessor;

public abstract class Detector {
	protected Object image;
	
	public Detector(Object image) {
		this.image = image;
	}
	
	public abstract Object Detect();
	
	public void setImage(Object image) {
		this.image = image;
	}
	

}