package br.com.insight.hourapp.resources.util;

import java.io.BufferedReader;

import org.apache.log4j.Logger;

/**
 * @author Marcos Vinicius 
 * @apiNote Faz a conversão do BufferedReader recebido em requisições para JSON
 */
public class BufferedReaderToJson {

	private static final Logger logger = Logger.getLogger(BufferedReaderToJson.class);
	
	public static String bufferedReaderToJson(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        String json = "";
        //Try with Resources faz com que não seja necessário declarar explicitamente "reader.close();"
        try(BufferedReader reader = bufferedReader) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            
            json = sb.toString();
        } catch(Exception e) {
        	logger.error(e);
        } 
        
        return json;
	}
	
}
