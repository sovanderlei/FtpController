/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;

//download the jar commons-net-1.4.1.jar 
// import org.apache.commons.net.ftp.FTPClient;
// https://commons.apache.org/proper/commons-net/download_net.cgi
// http://www.java2s.com/Code/Jar/a/Downloadapachecommonsnetjar.htm

public class FTPController {

	/**
	 * Lists files that exist in a path on the server. FTP
	 * 
	 * @return an FTPData objects fed with a file list
	 * @throws SocketException
	 * @throws IOException
	 */
	public static FTPData ListFilesDirectoriesFromFTP() throws SocketException, IOException {

		FTPData dadosftp = new FTPData();

		List<String> ListFiles = new ArrayList<String>();
		List<String> ListDirect = new ArrayList<String>();
		GetPropertyValues prop = new GetPropertyValues();
		FTPData ftpdados = prop.getPropValues();

		FTPClient ftp = new FTPClient();

		ftp.connect(ftpdados.getftpConnection());
		boolean success = ftp.login(ftpdados.getFtpLogin(), ftpdados.getftpPassword());
		// porta 21
		
		 if (!success) {
             System.out.println("Can not log into ftp server");
             return null;
         }
		 

		ftp.changeWorkingDirectory(ftpdados.getftpServerPath());


		String[] arq = ftp.listNames();

		System.out.println("listing files: \n");

		for (String item : arq) {

			// System.out.println(f);
			if (item.indexOf(".") > 0) {
				ListFiles.add(item);
			} else {
				ListDirect.add(item);
			}

		}

		if (ListFiles.size() > 0) {
			dadosftp.setListFiles(ListFiles);
		}

		if (ListDirect.size() > 0) {
			dadosftp.setDirectoryList(ListDirect);
		}

         
		ftp.logout();
		ftp.disconnect();
        
		return dadosftp;

	}


	/**
	 * Send Files To FTP
	 * @throws SocketException
	 * @throws IOException
	 */
	public static void SendFilesToFTP() throws SocketException,	IOException {

		FTPClient ftp = new FTPClient();
 
		GetPropertyValues prop = new GetPropertyValues();
		FTPData ftpdados = prop.getPropValues();
		
		ftp.connect(ftpdados.getftpConnection());
		ftp.login(ftpdados.getFtpLogin(), ftpdados.getftpPassword());
		
		//ftp.setBufferSize(1024);
		//ftp.enterLocalPassiveMode();
		//ftp.enterLocalActiveMode();
	        

		FileInputStream fileSend =	new FileInputStream("F:\\FTPCONTROLE\\PHP\\arquivophpftp.txt");

		if (ftp.storeFile("/Web/PHP/arquivophpftp.txt", fileSend))
			System.out.println("File stored successfully!");
		else
			System.out.println("Error storing file.");

		ftp.logout();
		ftp.disconnect();
		
	}

		
	/**
	 * Download Files To FTP
	 * @param args
	 * @throws SocketException
	 * @throws IOException
	 */
	public static void DownloadFilesToFTP(String[] args) throws SocketException,	IOException {

		FTPClient ftp = new FTPClient();
		GetPropertyValues prop = new GetPropertyValues();
		FTPData ftpdados = prop.getPropValues();
		
		ftp.connect(ftpdados.getftpConnection());
		ftp.login(ftpdados.getFtpLogin(), ftpdados.getftpPassword());
 
		ftp.changeWorkingDirectory(ftpdados.getftpServerPath());

		String[] arq = ftp.listNames();

		FileOutputStream fos =	new FileOutputStream("F:\\FTPCONTROLE\\PHP\\arquivophpftp.txt");

		if (ftp.retrieveFile("RD-4410-00412[192017].log", fos))
			System.out.println("Download successful!");
		else
			System.out.println("Error downloading file.");

		ftp.logout();
		ftp.disconnect();
		
		
	}
	
	
	
	
	
	
	
	
	

}
