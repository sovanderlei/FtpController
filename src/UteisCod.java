/**Class for objects where will be contained, values ​​and methods for it
* @author vanderlei soares de oliveira
* @version 0.01
* @since Release 02 of the application
*/

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.management.Query;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;

public class UteisCod {
	
	
	public static String data_Formatar(Date pData, String sMascara) {
		Date data = pData;
		SimpleDateFormat formatador = new SimpleDateFormat(sMascara);//"dd-MM-yyyy"
		String DataRetorno = formatador.format(data);
		return DataRetorno;				
	}
	   
	//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	//String dataString = dataFormatada //  <= sua data no formato de String; 
    //Date date = formatter.parse(dataString);	
	 
	public static Date data_stringToDate( String stringDate ) {
		
		Date date = null;
		
		try {
	        SimpleDateFormat sdf =
	            new SimpleDateFormat( "dd/MM/yyyy", new Locale( "pt_BR" ) );
	        date = sdf.parse( stringDate );
	    } catch ( ParseException e ) {
	    	e.printStackTrace();
	    }
	    
	    return date;
	}

	public static int data_diasEntreDuasDatas( Date initialDate, Date finalDate ) {
		
		if( initialDate == null || finalDate == null ) {
			return 0;
		}

		int days = ( int ) ( ( finalDate.getTime() - initialDate.getTime() )/( 24*60*60*1000 ) );
		
		
		return ( days > 0 ? days : 0 ) ;
	}

	public static String data_dateToString( Date date ) {	
		
	    SimpleDateFormat sdf =
	        new SimpleDateFormat( "dd/MM/yyyy", new Locale( "pt_BR" ) );
	    String dateFormated = sdf.format( date );
	    
	    return dateFormated;
	}

	public static Date data_TirarHoras( Date date ) {		
		return ( data_stringToDate( data_dateToString( date ) ) );
	}

	public static int data_getWorkingDays( Date initialDate, Date finalDate ){

		int workingDays = 0;
		int totalDays = data_diasEntreDuasDatas( initialDate, finalDate );		
		
		Calendar calendar = new GregorianCalendar();
		
		//Setando o calendar com a Data Inicial
		calendar.setTime(initialDate);
		
		for( int i = 0; i <= totalDays; i++ ) {
			
			if( !( calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ) && !( calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ) ) {
				workingDays++;
			}
			calendar.add( Calendar.DATE, 1 );
			
		}		
		return workingDays;


	} 
	
    public static String data_Horas( Date pdate) {
        Date date = pdate;
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return  formattedDate;
    }

	/***
	 * Formata a data no formato Brasil 99/99/9999
	 * @param pData tipo date
	 * @return tipo date
	 */
	public static Date Data_FormatarDataBRA(Date pData) {
		Date dataretono = pData;
		 
		try {
			dataretono = new SimpleDateFormat("dd/MM/yyyy").parse(pData.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return dataretono;
		
		
	}
	 
	/***
	 * Formata a data no formato Brasil 99/99/9999
	 * @param pData tipo date
	 * @return tipo date
	 */
	public static Date Data_FormatarDataBRA(String pData) {
		Date dataretono = null; 
		try {
			dataretono = new SimpleDateFormat("dd/MM/yyyy").parse(pData.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dataretono; 
	}
	
	/***
	 * Formata a data no formato USA 9999-99-99
	 * @param pData tipo date
	 * @return tipo date
	 */
	public static Date Data_FormatarDataUSA(Date pData) {
		Date dataretono = pData;
		 
		try {
			dataretono = new SimpleDateFormat("yyyy-MM-dd").parse(pData.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return dataretono;
		
		
	}
	
	/***
	 * Formata a data no formato USA 9999-99-99
	 * @param pData tipo date
	 * @return tipo date
	 */
	public static Date Data_FormatarDataUSA(String pData) {
	 	Date dataretono = null; 
		try {
			dataretono = new SimpleDateFormat("yyyy-MM-dd").parse(pData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dataretono; 
	}
	
	/***
	 * Criar data atual
	 * @return retorna a data atual
	 */
	public static Date Data_DataAtual() { 
		 java.util.Date date = Calendar.getInstance().getTime();
	     java.util.Date sqlDate = new java.util.Date(date.getTime());  
	     return sqlDate; 
	}
		
	public static Calendar Data_DateToCalendar(Date date, boolean setTimeToZero){ 
	    Calendar calendario = Calendar.getInstance();
	    calendario.setTime(date);
	    if(setTimeToZero){
	        calendario.set(Calendar.HOUR_OF_DAY, 0);
	        calendario.set(Calendar.MINUTE, 0);
	        calendario.set(Calendar.SECOND, 0);
	        calendario.set(Calendar.MILLISECOND, 0);
	    }
	    

	    
	    return calendario;
	}  
	 
	public static int Data_DiferencaDatas(String dataInicial, String dataFinal){
		
		Date data1 = new Date(), data2 = new Date();
		
		Calendar c1 = Calendar.getInstance();
		
		//Pega a primeira data
		c1.set(Integer.parseInt(dataInicial.substring(0, 4)), Integer.parseInt(dataInicial.substring(4, 6)), Integer.parseInt(dataInicial.substring(6, 8)));
		data1.setTime(c1.getTimeInMillis());
		
		//Pega a segunda data
		c1.set(Integer.parseInt(dataFinal.substring(0, 4)), Integer.parseInt(dataFinal.substring(4, 6)), Integer.parseInt(dataFinal.substring(6, 8)));
		data2.setTime(c1.getTimeInMillis());

		return (int) ((data2.getTime() - data1.getTime()) /1000/60/60/24);
	}	
	  
	public static Date Data_adicionarDiasUteis(Date data, Integer qtdeDiasAcrescentados) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(data);
		while(qtdeDiasAcrescentados > 0){
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
			int diaDaSemana = dataInicial.get(Calendar.DAY_OF_WEEK);
			if (diaDaSemana != Calendar.SATURDAY && diaDaSemana != Calendar.SUNDAY) {
				--qtdeDiasAcrescentados;
			}
		}
		return dataInicial.getTime();
	}
				
	public static int Data_getIdade(Date data) {
		Calendar cData = Calendar.getInstance();
		Calendar cHoje= Calendar.getInstance();
		cData.setTime(data);
		cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
		int idade = cData.after(cHoje) ? -1 : 0;
		cData.setTime(data);
		idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
		return idade;
	}
		
	public static int Data_calculaIdade(java.util.Date dataNasc) {

	    Calendar dataNascimento = Calendar.getInstance();  
	    dataNascimento.setTime(dataNasc); 
	    Calendar hoje = Calendar.getInstance();  

	    int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR); 

	    if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
	      idade--;  
	    } 
	    else 
	    { 
	        if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
	            idade--; 
	        }
	    }

	    return idade;
	}
        
	public static String completeToLeft(String value, char c, int size) {
		String result = value;

		while (result.length() < size) {
			result = c + result;
		}

		return result;
		//completeToLeft("12938", '0', 15);
	}
    
	public static String completeToRight(String value, char c, int size) {
		String result = value;

		while (result.length() < size) {
			result = result + c  ;
		}

		return result;
		//completeToLeft("12938", '0', 15);
	}    
	
	public static int somenteNumero(String sNumero) {
	    String numero = sNumero;//"sd657dsf765sdaf756";  
	    numero = numero.replaceAll("[^0-9]*", "");  
	    //System.out.println(numero);  
	    return Integer.parseInt(numero);
	}
			
	public static <T> void xmlGravarDados(T dados, String pCaminho) {
		
		try {
			
			FileOutputStream fos = new FileOutputStream(pCaminho);
			XMLEncoder en = new XMLEncoder(fos);
			en.writeObject(dados);
			en.close();
			fos.close();	
			
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T xmlLerDados(String pCaminho) {
		
		T ListaDados = null;
		try {
			
			FileInputStream fos = new FileInputStream(pCaminho);
			XMLDecoder en = new XMLDecoder(fos);
			ListaDados = (T) en.readObject();
			en.close();
			fos.close();	
			
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		
		return ListaDados;
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> xmlLerListaDados(String pCaminho) {
		
		List<T>  ListaDados = null;
		try {
			
			FileInputStream fos = new FileInputStream(pCaminho);
			XMLDecoder en = new XMLDecoder(fos);
			ListaDados = (List<T>) en.readObject();
			en.close();
			fos.close();	
			
		}catch(Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		
		return ListaDados;
		
	}	
	
	
	
	
	@SuppressWarnings("unused")
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
		
	@SuppressWarnings({ "unused", "resource" })
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
	}
	
	@SuppressWarnings("unused")
	private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
	    FileUtils.copyFile(source, dest);
	}
		
	@SuppressWarnings("unused")
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}
	
	/* EXEMPLO DE USO DO COPIA
	
	 File source = new File("/Users/pankaj/tmp/source.avi");
    File dest = new File("/Users/pankaj/tmp/dest.avi");


    //copy file conventional way using Stream
    long start = System.nanoTime();
    copyFileUsingStream(source, dest);
    System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));

    //copy files using java.nio FileChannel
    source = new File("/Users/pankaj/tmp/sourceChannel.avi");
    dest = new File("/Users/pankaj/tmp/destChannel.avi");
    start = System.nanoTime();
    copyFileUsingChannel(source, dest);
    System.out.println("Time taken by Channel Copy = "+(System.nanoTime()-start));

    //copy files using apache commons io
    source = new File("/Users/pankaj/tmp/sourceApache.avi");
    dest = new File("/Users/pankaj/tmp/destApache.avi");
    start = System.nanoTime();
    copyFileUsingApacheCommonsIO(source, dest);
    System.out.println("Time taken by Apache Commons IO Copy = "+(System.nanoTime()-start));

    //using Java 7 Files class
    source = new File("/Users/pankaj/tmp/sourceJava7.avi");
    dest = new File("/Users/pankaj/tmp/destJava7.avi");
    start = System.nanoTime();
    copyFileUsingJava7Files(source, dest);
    System.out.println("Time taken by Java7 Files Copy = "+(System.nanoTime()-start));
    
	
	*/
	
	/*
	
		import com.google.gson.Gson;
		
		String json = 
            "{"
                + "'title': 'Computing and Information systems',"
                + "'id' : 1,"
                + "'children' : 'true',"
                + "'groups' : [{"
                    + "'title' : 'Level one CIS',"
                    + "'id' : 2,"
                    + "'children' : 'true',"
                    + "'groups' : [{"
                        + "'title' : 'Intro To Computing and Internet',"
                        + "'id' : 3,"
                        + "'children': 'false',"
                        + "'groups':[]"
                    + "}]" 
                + "}]"
            + "}";

        // Now do the magic.
        Data data = new Gson().fromJson(json, Data.class);


		***************
		****************
		
		TestObjectToJson obj = new TestObjectToJson();
	      Gson gson = new Gson();
	
	      //convert java object to JSON format
	      String json = gson.toJson(obj);
	
	      System.out.println(json);
		  // {"data1":100,"data2":"hello"}
		   
		***************
		****************

		Dto response = softConvertValue(jsonData, Dto.class);
		
		
		public static <T> T softConvertValue(Object fromValue, Class<T> toValueType) 
		{
		    ObjectMapper objMapper = new ObjectMapper();
		    return objMapper
		        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		        .convertValue(fromValue, toValueType);
		}
			
	
	*/
	
	
	
	
	

}
 

