package entity;


public class Video implements java.io.Serializable {
	//NIGELS
	private String name;
	private String fileLocation;
	
	public Video(String name, String fileLocation) {
		super();
		this.name = name;
		this.fileLocation = fileLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	
	//Syafiq's
		private String destinationDirectory;
		private String sourceDirectory;
		private String filename;
		private long fileSize;
		private byte[] fileData;
		private String status;
		private int id;
		
		public Video(){}
		
		private  final long serialVersionUID = 1L;
		
		public String getDestinationDirectory() {
			return destinationDirectory;
		}
		public void setDestinationDirectory(String destinationDirectory) {
			this.destinationDirectory = destinationDirectory;
		}
		public String getSourceDirectory() {
			return sourceDirectory;
		}
		public void setSourceDirectory(String sourceDirectory) {
			this.sourceDirectory = sourceDirectory;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public long getFileSize() {
			return fileSize;
		}
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		public byte[] getFileData() {
			return fileData;
		}
		public void setFileData(byte[] fileData) {
			this.fileData = fileData;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public  long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public  int getId(){
			return id;
		}
	
}
