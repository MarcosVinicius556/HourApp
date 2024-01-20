package br.com.insight.hourapp.web.entities.interfaces;

/**
 * @author Marcos Vinicius Angeli Costa.
 * @apiNote interface para padronizar classes do tipo "Entity" 
 * 			para assim facilitar na hora de disponibilizar métodos 
 * 			genéricos para uso na aplicação
 */
public interface BaseEntity {

	public Object getId();
	public Class<? extends BaseEntity> ClassType();
}
