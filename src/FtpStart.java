/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/
import java.io.File;

public class FtpStart {

	
	public static void main(String[] args) {
		 
		System.out.println("System start..."); 
		
		  
		FileController filecontr = new FileController();
		File[]  ListFiles = filecontr.ListFiles();
		String pOriginArq = "F:\\FTPCONTROLE\\PHP\\arquivophpftp.txt" ;
		String pDestinyArq = "F:\\FTPCONTROLE\\Destino\\arquivophpftp.txt" ;
		
		 
	    
	    for (int i = 0; i < ListFiles.length; i++) { 
	    	
	    	pOriginArq = "F:\\FTPCONTROLE\\PHP\\"+ListFiles[i].getName() ;
	    	pDestinyArq = "F:\\FTPCONTROLE\\Destino\\"+ListFiles[i].getName()  ;
	    	    	 
			filecontr.MoveFiles(pOriginArq, pDestinyArq);
	    	
	    	
	    } 
		 
		
	}
		

}
