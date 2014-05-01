package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.LanguageDao;
import com.blazwin.contests.entity.Language;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageDaoImpl extends DaoImpl<Language> implements LanguageDao {
    public LanguageDaoImpl(){
        super(Language.class);
    }
}
