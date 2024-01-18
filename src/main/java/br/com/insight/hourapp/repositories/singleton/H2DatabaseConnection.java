package br.com.insight.hourapp.repositories.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * @author Marcos Vinicius
 * @apiNote Classe no padrão singleton, fornecendo apenas 
 *		    uma única instancia de conexão com o banco 
 *		    de dados para toda a aplicação
 */
public class H2DatabaseConnection {

	private static final Logger logger = Logger.getLogger(H2DatabaseConnection.class);
	
	private static EntityManagerFactory emf = null;
	
	
	public static EntityManagerFactory createDatabaseConnection() {
		try {
			if(emf == null) {
				emf = Persistence.createEntityManagerFactory("hourControll");
			}
			
			logger.info("Conexão com o banco estabelecida com sucesso!");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			System.gc();
		}
		
		return emf;
	}
	
}
