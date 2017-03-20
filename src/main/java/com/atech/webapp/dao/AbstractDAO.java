package com.atech.webapp.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atech.webapp.entity.IDomain;

/**
 * Classe base para acesso a dados atraves da interface generica que sera
 * implementada por cada uma delas Como por objetivo ela ira gerenciar a conexao
 * com o banco de dados bem como algumas transacoes
 * 
 * 
 * @param <T>
 *            - Classe a ser implementada como Generic
 * 
 * @author paulo.rodenas
 */
@Transactional(readOnly = true)
public abstract class AbstractDAO<T extends IDomain> {

	/**
	 * Responsavel pela conexao com o banco de dados atraves do hibernate
	 */
	@PersistenceContext private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	/**
	 * Obtem pelo id da entidade
	 */
	@SuppressWarnings("unchecked")
	public T findById(T entity) throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName() + " e where e." + findIdField(getType()) + "=:ID");
		query.setParameter("ID", findIdValue(getType(), entity));
		List<T> list = query.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Obtem pelo id 
	 */
	@SuppressWarnings("unchecked")
	public T findById(Integer id) throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName() + " e where e." + findIdField(getType()) + "=:ID");
		query.setParameter("ID", id);
		List<T> list = query.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Obtem pelo id e da o Fetch no campo selecionado
	 */
	@SuppressWarnings("unchecked")
	public T findByIdFetchAll(Integer id) throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName() + "  e FETCH ALL PROPERTIES where e." + findIdField(getType()) + "=:ID");
		query.setParameter("ID", id);
		List<T> list = query.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Retorna os registros Ativos
	 */
	@SuppressWarnings("unchecked")
	public List<T> findActives() throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName() + " e where e.isActive =:active order by e.dateInsert desc ");
		query.setParameter("active", Boolean.TRUE);
		List<T> list = query.getResultList();
		return list;
	}
	
	/**
	 * Lista todos da entidade
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName());
		List<T> list = query.getResultList();
		return list;
	}

	/**
	 * Lista todos da entidade atraves da parametrizacao de paginacao
	 * 
	 * @return lista de entidades
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(int firstResult, int maxsize) throws DataAccessException {
		Query query = em.createQuery("from " + getType().getSimpleName() + " order by " + findIdField(getType()));
		query.setFirstResult(firstResult);
		query.setMaxResults(maxsize);
		List<T> list = query.getResultList();
		return list;
	}

	/**
	 * Salva uma entidade especificada
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public T save(T entity) throws DataAccessException {
		return em.merge(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void persist(T entity) throws DataAccessException {
		em.persist(entity);
	}

	/**
	 * Salva uma entidade especificada
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public T merge(T entity) throws DataAccessException {
		return em.merge(entity);
	}

	public int count() throws DataAccessException {
		return ((Long) em.createQuery("select count(*) from " + getType().getSimpleName()).getSingleResult()).intValue();
	}

	/**
	 * Retorna o Type do Generic implementado
	 * 
	 * @return
	 */
	public abstract Class<T> getType();

	/**
	 * Retorna o campo anotado com "Id" o que indica que se trata da primary key
	 * da entidade
	 * 
	 * @param cls
	 *            - Classe que queremos saber o campo anotado
	 * @return o campo anotado ou null se nao for encontrado
	 */
	private String findIdField(Class<T> cls) {
		for (Field field : cls.getDeclaredFields()) {
			String name = field.getName();
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (int i = 0; i < annotations.length; i++) {
				if (annotations[i].annotationType().equals(Id.class)) {
					return name;
				}
			}
		}
		return null;
	}

	/**
	 * Retorna o valor do campo anotado com "Id",o que indica que se trata da
	 * primary key da entidade
	 * 
	 * @param cls
	 *            - Classe que queremos saber o campo anotado
	 * @param obj
	 *            - Objeto do qual voce quer obter o valor
	 * @return O valor ou null se nao encontrado
	 */
	private Object findIdValue(Class<T> cls, Object obj) {
		try {
			for (Field field : cls.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(obj);
				Annotation[] annotations = field.getDeclaredAnnotations();
				for (int i = 0; i < annotations.length; i++) {
					if (annotations[i].annotationType().equals(Id.class)) {
						return value;
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
