package br.com.insight.hourapp.web.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;
import br.com.insight.hourapp.web.repositories.singleton.MemoryStorage;

/**
 * @author Marcos Vinicius Angeli Costa.
 *
 * @mail marcosvinicios4132@gmail.com
 * 
 * @apiNote Está é a interface padrão, da qual todos os demais repositories
 *          deverão herdar, possui as implementações mais básicas já
 *          desenvolvidas para evitar duplicidade de código e redundância
 */
public interface BaseRepository {

	/**
	 * @param Entity obj
	 * @throws Exception 
	 * @apiNote Método reponsável por inserir uma entidade no banco.
	 */
	default void insert(BaseEntity obj) throws Exception {
		MemoryStorage memory = new MemoryStorage();
		String className = obj.getClass().getName();
		try {
			long lastId = getLastIdNumber(className);
			obj.setId(lastId + 1); //" AUTO_INCREMENT "
			
			memory.insertIntoMemory(className, obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	

	/**
	 * @param BaseEntity
	 * @return true if success
	 * @throws Exception 
	 */
	default void remove(BaseEntity obj) throws Exception {
		MemoryStorage memory = new MemoryStorage();
		try {
			memory.removeFromMemory(obj.getClass().getName(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 * @return true if success
	 * @throws Exception 
	 */
	default void update(BaseEntity obj) throws Exception {
		MemoryStorage memory = new MemoryStorage();
		List<Object> lst = memory.readAllIntoMemory(obj.getClass().getName());
		try {
			//Remove o objeto antigo
			memory.removeFromMemory(obj.getClass().getName(), obj);
			
			//Adiciona o novo
			memory.insertIntoMemory(obj.getClass().getName(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}


	/**
	 * @apiNote Retorna tudo da entidade passada por parâmetro
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	default List<BaseEntity> findAll(BaseEntity obj) throws Exception{
		MemoryStorage memory = new MemoryStorage();
		List<Object> lst = memory.readAllIntoMemory(obj.getClass().getName());
		List<BaseEntity> lstReturn = new ArrayList<>();
		try {
			lst.forEach(o -> lstReturn.add((BaseEntity)o));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return lstReturn;
	}

	/**
	 * @param BaseEntity
	 * @return Entity
	 * @throws Exception 
	 */
	default BaseEntity findById(BaseEntity obj) throws Exception {
		MemoryStorage memory = new MemoryStorage();
		List<Object> lst = memory.readAllIntoMemory(obj.getClass().getName());
		BaseEntity objectFind = null;
		try {
			if(lst.size() == 0) {
				throw new RuntimeException("Nenhum registro encontrado!");
			}
			Optional<Object> opObj = lst.stream().filter(o -> ((BaseEntity)o).getId() == obj.getId()).findFirst();
			if(!opObj.isPresent())
				throw new RuntimeException("Nenhum registro com o ID. " + obj.getId() + " encontrado" );
			
			objectFind = (BaseEntity) opObj.get();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return objectFind;
	}


	private long getLastIdNumber(String className) {
		MemoryStorage memory = new MemoryStorage();
		long lastId = 0;
		try {
			List<Object> lst = memory.readAllIntoMemory(className);
			if(lst.size() == 0) //Caso não encontre nada, é por quê será a primeira inserção
				return 0;
			BaseEntity temp = (BaseEntity) lst.stream()
							   .sorted((o1, o2) -> ((BaseEntity)o1).getId().compareTo(((BaseEntity)o2).getId()))
							   .findFirst().get();
			lastId = temp.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return lastId;
	}
	

}
