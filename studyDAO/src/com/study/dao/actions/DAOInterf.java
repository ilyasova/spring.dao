package com.study.dao.actions;

import com.study.entity.*;

public interface DAOInterf {

	public Object save(Object obj);

	public void addLanguage(User user, Phrases phrases);

	public User getAuthorize(User user);

	public Phrases getNextPhraseByLanguage(Phrases currentPhrases);

	public Phrases getLastPhraseByLanguage(User user, Phrases currentLanguage);

	public Phrases getPhrasesByLanguageAndOrder(Phrases phrases);

	public String checkLoginFree(User user);

	public Boolean updateLanguageForUser(User user, Phrases phrases);

	public Phrases updateNumberPhraseForUser(User user, Phrases phrases);
}
