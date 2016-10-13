package DataTransferObject;

public class DTOGroundTruth {
	private String videoPath;
	private String grundVideoPath;
	private double diceValue;
	/**
	 * @return the videoPath
	 */
	public String getVideoPath() {
		return videoPath;
	}
	/**
	 * @param videoPath the videoPath to set
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	/**
	 * @return the grundVideoPath
	 */
	public String getGrundVideoPath() {
		return grundVideoPath;
	}
	/**
	 * @param grundVideoPath the grundVideoPath to set
	 */
	public void setGrundVideoPath(String grundVideoPath) {
		this.grundVideoPath = grundVideoPath;
	}
	/**
	 * @return the diceValue
	 */
	public double getDiceValue() {
		return diceValue;
	}
	/**
	 * @param diceValue the diceValue to set
	 */
	public void setDiceValue(double diceValue) {
		this.diceValue = diceValue;
	}
}
