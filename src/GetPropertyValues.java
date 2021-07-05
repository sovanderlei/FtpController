/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties;

public class GetPropertyValues {
 
	InputStream inputStream;
 
	public FTPData getPropValues() throws IOException {
		FTPData ftpreturn = new FTPData();
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException(" '" + propFileName + "'  not found in the project...");
			}
  
			String ftpConnection = prop.getProperty("ftpConnection");
			String ftpLogin = prop.getProperty("ftpLogin");
			String ftpPassword = prop.getProperty("ftpPassword");
			String ftpPort = prop.getProperty("ftpPort");
			String ftpServerPath = prop.getProperty("ftpServerPath");
			String sourcepath = prop.getProperty("sourcepath");
			String pathDestination = prop.getProperty("pathDestination"); 
			
			ftpreturn.setftpConnection(ftpConnection);  
			ftpreturn.setFtpLogin(ftpLogin);
			ftpreturn.setftpPassword(ftpPassword);
			ftpreturn.setftpPort(Integer.parseInt(ftpPort)); 
			ftpreturn.setftpServerPath(ftpServerPath);
			ftpreturn.setsourcePath(sourcepath);
			ftpreturn.setDestinationPath(pathDestination);
			 			
			 
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			inputStream.close();
		}
		
		return ftpreturn;
		
		
	}
 

	
}

 
