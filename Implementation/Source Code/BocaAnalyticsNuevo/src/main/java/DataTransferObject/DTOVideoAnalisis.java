package DataTransferObject;

public class DTOVideoAnalisis {
	private String videoPath;
	private String outVideoPath;
	private String reportPath;
	private int timeOfProcessing;
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
	 * @return the timeOfProcessing
	 */
	public int getTimeOfProcessing() {
		return timeOfProcessing;
	}
	/**
	 * @param timeOfProcessing the timeOfProcessing to set
	 */
	public void setTimeOfProcessing(int timeOfProcessing) {
		this.timeOfProcessing = timeOfProcessing;
	}
	/**
	 * @return the outVideoPath
	 */
	public String getOutVideoPath() {
		return outVideoPath;
	}
	/**
	 * @param outVideoPath the outVideoPath to set
	 */
	public void setOutVideoPath(String outVideoPath) {
		this.outVideoPath = outVideoPath;
	}
	/**
	 * @return the reportPath
	 */
	public String getReportPath() {
		return reportPath;
	}
	/**
	 * @param reportPath the reportPath to set
	 */
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
}
