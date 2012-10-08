package com.study.dao.actions;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.study.entity.*;

@Repository
@Transactional
public class DAOImpl implements DAOInterf {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Object save(Object obj) {
		sessionFactory.getCurrentSession().save(obj);
		return obj;
	}

	@Override
	public String checkLoginFree(User user) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User u WHERE u.login=:login");
		query.setParameter("login", user.getLogin());
		if (query.uniqueResult() != null) {
			return "busy";
		} else {
			return "free";
		}
	}

	@Override
	public User getAuthorize(User user) {
		System.out.println("CALL");
			Query query = sessionFactory.getCurrentSession().createQuery(
					"SELECT u FROM User u WHERE "
							+ "u.login=:login and u.password=:password");
			query.setParameter("login", user.getLogin());
			query.setParameter("password", user.getPassword());
			return (User) query.uniqueResult();
	}

	@Override
	public Phrases getNextPhraseByLanguage(Phrases currentPhrases) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"SELECT f FROM Phrases f WHERE "
							+ "f.order=:order and f.language=:language");
			query.setParameter("order", currentPhrases.getOrder() + 1);
			query.setParameter("language", currentPhrases.getLanguage());
			return (Phrases) query.uniqueResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public Phrases getLastPhraseByLanguage(User user, Phrases currentLanguage) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"SELECT p FROM Phrases p join p.users u WHERE u.id=:id"
							+ " and p.language=:language");
			query.setParameter("id", user.getId());
			query.setParameter("language", currentLanguage.getLanguage());
			return (Phrases) query.uniqueResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public void addLanguage(User user, Phrases phrases) {
		user = (User) sessionFactory.getCurrentSession().load(User.class,
				user.getId());
		user.getPhrases().add(phrases);
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Boolean updateLanguageForUser(User user, Phrases phrases) {
			user = (User) sessionFactory.getCurrentSession().load(User.class,
					user.getId());
			phrases = (Phrases) sessionFactory.getCurrentSession().load(
					Phrases.class, phrases.getId());
			Collection<Phrases> listPhrases = user.getPhrases();
			for (Phrases phrase : listPhrases) {
				if (phrase.getLanguage().ordinal() == phrases.getLanguage()
						.ordinal()) {
					user.getPhrases().remove(phrase);
					break;
				}
			}
			user.getPhrases().add(phrases);
			sessionFactory.getCurrentSession().update(user);
		return true;
	}

	@Override
	public Phrases getPhrasesByLanguageAndOrder(Phrases phrases) {
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"SELECT p FROM Phrases p WHERE p.order=:order and p.language=:language");
			query.setParameter("order", phrases.getOrder());
			query.setParameter("language", phrases.getLanguage());
			return (Phrases) query.uniqueResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public Phrases updateNumberPhraseForUser(User user, Phrases phrases) {
		phrases = getPhrasesByLanguageAndOrder(phrases);
		if (phrases == null || !updateLanguageForUser(user, phrases)) {
			return null;
		}
		return phrases;
	}
}
