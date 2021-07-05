/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/
import java.util.List;

public class FTPData {
 
	private String ftpConnection;
	private String ftpLogin;
	private String ftpPassword;
	private int    ftpPort;
	private String ftpServerPath;
	private String sourcePath;
	private String DestinationPath;
	private List<String> ListFiles;
	private List<String> DirectoryList;
	
	public String getftpConnection() {
		return ftpConnection;
	}
	public void setftpConnection(String ftpConnection) {
		this.ftpConnection = ftpConnection;
	}
	public String getFtpLogin() {
		return ftpLogin;
	}
	public void setFtpLogin(String ftpLogin) {
		this.ftpLogin = ftpLogin;
	}
	public String getftpPassword() {
		return ftpPassword;
	}
	public void setftpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	public int getftpPort() {
		return ftpPort;
	}
	public void setftpPort(int ftpPort) {
		this.ftpPort = ftpPort;
	}
	public String getftpServerPath() {
		return ftpServerPath;
	}
	public void setftpServerPath(String ftpServerPath) {
		this.ftpServerPath = ftpServerPath;
	}
	public String getsourcePath() {
		return sourcePath;
	}
	public void setsourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getDestinationPath() {
		return DestinationPath;
	}
	public void setDestinationPath(String DestinationPath) {
		this.DestinationPath = DestinationPath;
	}
	public List<String> getListFiles() {
		return ListFiles;
	}
	public void setListFiles(List<String> ListFiles) {
		this.ListFiles = ListFiles;
	}
	public List<String> getDirectoryList() {
		return DirectoryList;
	}
	public void setDirectoryList(List<String> DirectoryList) {
		this.DirectoryList = DirectoryList;
	}
 

	
	
	
	
	
}
