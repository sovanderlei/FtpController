/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileController {

	private static final Logger loggers = LoggerFactory.getLogger(FileController.class);
	
	public File[] ListFiles() {

		File[] files = null;

		try {
 
			File f = new File("F:\\FTPCONTROLE\\PHP");
 
			FilenameFilter filter = new FilenameFilter() {

				public boolean accept(File f, String name) {
					return name.endsWith(".txt");
				}
			};

			files = f.listFiles(filter);

			System.out.println("Files are:");

			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i].getName());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			loggers.error("Errors"+e.getMessage());
		}

		return files;

	}

	public void CopyFiles(String pOriginArq, String pDestinyArq) {

		try {

			File source = new File(pOriginArq);
			File dest = new File(pDestinyArq);

			FileUtils.copyFile(source, dest);

		} catch (IOException e) {  
			System.err.println(e.getMessage());
			loggers.error("Errors"+e.getMessage());
		}

	}

	public void MoveFiles(String pOriginArq, String pDestinyArq) {

		try {

			File source = new File(pOriginArq);
			File dest = new File(pDestinyArq);

			FileUtils.copyFile(source, dest);

			if (dest.exists()) {
				FileUtils.forceDeleteOnExit(source);
			}

		} catch (IOException e) { 
			System.err.println(e.getMessage());
			loggers.error("Errors"+e.getMessage());
		}

	}

}
