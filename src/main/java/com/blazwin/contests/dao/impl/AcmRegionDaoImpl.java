package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.AcmRegionDao;
import com.blazwin.contests.entity.AcmRegion;
import org.springframework.stereotype.Repository;

@Repository
public class AcmRegionDaoImpl extends DaoImpl<AcmRegion> implements AcmRegionDao {
    public AcmRegionDaoImpl(){
        super(AcmRegion.class);
    }
}
