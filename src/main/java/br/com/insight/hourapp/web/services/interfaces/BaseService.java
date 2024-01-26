package br.com.insight.hourapp.web.services.interfaces;

import java.util.List;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;
import br.com.insight.hourapp.web.repositories.interfaces.BaseRepository;

/**
 * 
 * @author Marcos Vinicius Angeli Costa
 * @mail marcosvinicios4132@gmail.com
 * @apiNote Fornece uma implementação padrão e genérica 
 * 			para todos os componentes da camada de services
 *          
 */
public interface BaseService<Entity extends BaseEntity>{
	
	/**
	 * @apiNote Método criado para pegar o repository correspondente ao service sendo utilizado
	 * @return Repositorio para persistir objetos
	 */
	public BaseRepository getRepository();
	
	/**
	 * @param BaseEntity
	 */
	default void insert(Entity obj) {
		try {
			getRepository().insert(obj);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível inserir a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	default void remove(Entity obj) {
		try {
			getRepository().remove(obj);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover a entidade. Motivo: " + e.getMessage());
		}
	}
	
	/**
	 * @param BaseEntity
	 */
	default void removeAll(String className) {
		try {
			getRepository().removeAll(className);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	default void update(Entity obj) {
		try {
			getRepository().update(obj);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível atualizar a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	@SuppressWarnings("unchecked")
	default Entity findById(Entity obj) {
		try {
			return (Entity) getRepository().findById(obj);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível fazer a busca da entidade. Motivo: " + e.getMessage());
		}
	}
	
	/**
	 * @param
	 */
	@SuppressWarnings("unchecked")
	default List<Entity> findAll(BaseEntity entity){
		try {
			return (List<Entity>) getRepository().findAll(entity);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível fazer a busca de TODOS. Motivo: " + e.getMessage());
		}
	}

}
