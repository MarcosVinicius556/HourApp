package br.com.insight.hourapp.services.interfaces;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import br.com.insight.hourapp.entities.interfaces.BaseEntity;
import br.com.insight.hourapp.repositories.interfaces.BaseRepository;

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
			throw new ServiceException("Não foi possível inserir a entidade. Motivo: " + e.getMessage());
		}
	}
	
	/**
	 * @param BaseEntity
	 */
	default void insertOrUpdate(Entity obj) {
		try {
			getRepository().insertOrUpdate(obj);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível inserir ou atualizar a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param List<BaseEntity>
	 */
	default void insertAll(List<BaseEntity> objs) {
		try {
			getRepository().insertAll(objs);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível inserir a lista de entidades. Motivo: " + e.getMessage());
		}
	}
	
	/**
	 * @param List<BaseEntity>
	 */
	default void insertOrUpdateAll(List<BaseEntity> objs) {
		try {
			getRepository().insertAllOrUpdateAll(objs);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível inserir a lista de entidades. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	default void remove(Entity obj) {
		try {
			getRepository().remove(obj);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível remover a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param List<BaseEntity>
	 */
	default void removeAll(List<BaseEntity> objs) {
		try {
			getRepository().removeAll(objs);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível remover a lista de entidades. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	default void update(Entity obj) {
		try {
			getRepository().update(obj);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível atualizar a entidade. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param List<BaseEntity>
	 */
	default void updateAll(List<BaseEntity> objs) {
		try {
			getRepository().updateAll(objs);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível atualizar a lista de entidades. Motivo: " + e.getMessage());
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
			throw new ServiceException("Não foi possível fazer a busca da entidade. Motivo: " + e.getMessage());
		}
	}
	
	/**
	 * @param String typed text typed for search
	 */
	@SuppressWarnings("unchecked")
	default List<Entity> findAllLike(BaseEntity entity, String field, String typed){
		try {
			return (List<Entity>) getRepository().findAllLike(entity, field, typed);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível fazer a busca LIKE. Motivo: " + e.getMessage());
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
			throw new ServiceException("Não foi possível fazer a busca de TODOS. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param String query
	 */
	default List<Object> loadByQuery(String query) {
		try {
			return getRepository().loadByQuery(query);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível executar a busca por query. Motivo: " + e.getMessage());
		}
	}

	/**
	 * @param BaseEntity
	 */
	default boolean exists(Entity obj) {
		try {
			return getRepository().exists(obj);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível verificar a entidade. Motivo: " + e.getMessage());
		}
	}
	
	default void executeQuery(String query) {
		try {
			getRepository().executeQuery(query);
		} catch (Exception e) {
			throw new ServiceException("Não foi possível executar a query. Motivo: " +e.getMessage());
		}
	}

}
