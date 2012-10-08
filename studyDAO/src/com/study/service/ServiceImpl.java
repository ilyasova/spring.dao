package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.actions.*;
import com.study.entity.*;

@Service
@Transactional
public class ServiceImpl implements ServiceInterf {

	@Autowired
	private DAOInterf dAOInterf;

	@Override
	// @Transactional
	public void addLanguage(User user, Phrases phrases) {
		dAOInterf.addLanguage(user, phrases);
	}

	@Override
	public String checkLoginFree(User user) {
		return dAOInterf.checkLoginFree(user);
	}

	@Override
	// @Transactional
	public User getAuthorize(User user) {
		return dAOInterf.getAuthorize(user);
	}

	@Override
	public Phrases getLastPhraseByLanguage(User user, Phrases currentLanguage) {
		return dAOInterf.getLastPhraseByLanguage(user, currentLanguage);
	}

	@Override
	public Phrases getNextPhraseByLanguage(Phrases currentPhrases) {
		return dAOInterf.getNextPhraseByLanguage(currentPhrases);
	}

	@Override
	public Phrases getPhrasesByLanguageAndOrder(Phrases phrases) {
		return dAOInterf.getPhrasesByLanguageAndOrder(phrases);
	}

	@Override
	// @Transactional
	public Object save(Object obj) {
		return dAOInterf.save(obj);
	}

	@Override
	@Transactional
	public Boolean updateLanguageForUser(User user, Phrases phrases) {
		return dAOInterf.updateLanguageForUser(user, phrases);
	}

	@Override
	// @Transactional
	public Phrases updateNumberPhraseForUser(User user, Phrases phrases) {
		return dAOInterf.updateNumberPhraseForUser(user, phrases);
	}
}