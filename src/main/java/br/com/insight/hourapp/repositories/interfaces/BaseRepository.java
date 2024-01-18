package br.com.insight.hourapp.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.com.insight.hourapp.entities.interfaces.BaseEntity;
import br.com.insight.hourapp.repositories.singleton.H2DatabaseConnection;

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
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível persistir a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}
	
	default void insertOrUpdate(BaseEntity obj) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(obj);
				em.getTransaction().commit();
			} catch (RollbackException e) {
				em.getTransaction().begin();
				em.merge(obj);
				em.getTransaction().commit();
			}
			System.out.println("[Entity has saved correctly]:" + obj.toString());
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar ou atualizar as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param List<BaseEntity>
	 * @return true if success
	 * @throws Exception 
	 */
	default void insertAll(List<BaseEntity> objs) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			em.getTransaction().begin();
			for(BaseEntity obj : objs) {
				em.persist(obj);	
				System.out.println("[Entity was saved correctly]: " + obj.toString());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível persistir as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}
	
	/**
	 * @param List<BaseEntity>
	 * @return true if success
	 * @throws Exception 
	 */
	default void insertAllOrUpdateAll(List<BaseEntity> objs) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();

			for(BaseEntity obj : objs) {
				try {
					em.getTransaction().begin();
					em.persist(obj);
					em.getTransaction().commit();
				} catch (RollbackException e) {
					em.getTransaction().begin();
					em.merge(obj);
					em.getTransaction().commit();
				}
				System.out.println("[Entity was saved correctly]: " + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível persistir as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param BaseEntity
	 * @return true if success
	 * @throws Exception 
	 */
	default void remove(BaseEntity obj) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			em.getTransaction().begin();
			em.remove(em.contains(obj) ? obj : em.merge(obj));
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível remover a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param List<BaseEntity>
	 * @return true if success
	 * @throws Exception 
	 */
	default <T extends BaseEntity>  void removeAll(List<T> objs) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();

			em.getTransaction().begin();
			for(BaseEntity obj : objs) {
				em.remove(em.contains(obj) ? obj : em.merge(obj));
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível remover as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param BaseEntity
	 * @return true if success
	 * @throws Exception 
	 */
	default void update(BaseEntity obj) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();

			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível atualizar a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param List<BaseEntity>
	 * @return true if success
	 * @throws Exception 
	 */
	default void updateAll(List<BaseEntity> objs) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();

			em.getTransaction().begin();
			objs.forEach(em::merge);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível atualizar os dados das entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @apiNote Retorna tudo da entidade passada por parâmetro
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	default List<BaseEntity> findAll(BaseEntity entity) throws Exception{
		EntityManager em = null;
		TypedQuery<? extends BaseEntity> queryEntity = null;
		List<BaseEntity> lst = new ArrayList<>();
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			queryEntity =  em.createQuery("SELECT c FROM "+entity.getClass().getName()+" c ", entity.getClass());
			queryEntity.getResultList().forEach(lst::add);
		} catch (Exception e) {
			throw new Exception("Não foi possível buscar todas as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
		return lst;
	}

	/**
	 * @apiNote Faz uma consulta retornando aquilo que condiz com o que foi digitado
	 * @param entity
	 * @param field
	 * @param typed
	 * @return
	 * @throws Exception 
	 */
	default List<BaseEntity> findAllLike(BaseEntity entity, String field, String typed) throws Exception {
		EntityManager em = null;
		TypedQuery<? extends BaseEntity> queryEntity = null;
		List<BaseEntity> lst = new ArrayList<>();
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			queryEntity =  em.createQuery("SELECT c FROM "+entity.getClass().getName()+" c WHERE upper(c."+field+") LIKE upper(CAST('%"+typed+"%' as string))", entity.getClass());
			queryEntity.getResultList().forEach(lst::add);
		} catch (Exception e) {
			throw new Exception("Não foi possível buscar todas as entidades. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
		return lst;
	}
	
	/**
	 * @param BaseEntity
	 * @return Entity
	 * @throws Exception 
	 */
	default BaseEntity findById(BaseEntity obj) throws Exception {
		EntityManager em = null;

		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			return em.find(obj.ClassType(), obj.getId());
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @apiNote Executa uma query JPQL
	 * @param BaseEntity
	 * @return List of entity
	 * @throws Exception 
	 */
	default List<Object> loadByQuery(String query) throws Exception {
		EntityManager em = null;
		TypedQuery<Object> queryToExec = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			queryToExec = em.createQuery(query, Object.class);
			return queryToExec.getResultList();
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}
	
	/**
	 * @apiNote Executa uma query SQL
	 * @param BaseEntity
	 * @return List of entity
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	default List<Object> loadByNativeQuery(String query) throws Exception {
		EntityManager em = null;
		Query queryToExec = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			queryToExec = em.createNativeQuery(query);
			return queryToExec.getResultList();
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar a entidade. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param BaseEntity
	 * @return true if success
	 * @throws Exception 
	 */
	default boolean exists(BaseEntity obj) throws Exception {
		EntityManager em = null;

		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();

			return em.find(obj.getClass(), obj.getId()) == null ? false : true;
		} catch (Exception e) {
			throw new Exception("Não foi possível verificar se a entidade existe. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}
	
	default void executeQuery(String query) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			em.getTransaction().begin();
			em.createQuery(query).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível executar a query. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}
	
	default void executeNativeQuery(String query) throws Exception {
		EntityManager em = null;
		try {
			em = H2DatabaseConnection.createDatabaseConnection().createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery(query).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Não foi possível executar a query. Motivo: " + e.getMessage());
		} finally {
			closeEntitySession(em);
		}
	}

	/**
	 * @param EntityManager em
	 * @apiNote Fecha a sessão ativa no escopo do método
	 */
	default void closeEntitySession(EntityManager em) {
		if (em != null)
			em.close();
		em = null;
	}
	

}
