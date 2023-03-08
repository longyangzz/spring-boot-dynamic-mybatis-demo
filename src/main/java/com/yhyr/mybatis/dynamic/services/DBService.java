package com.yhyr.mybatis.dynamic.services;

import com.yhyr.mybatis.dynamic.domain.DBInfo;

import java.util.List;

/**
 * Created by YHYR on 2017-12-25
 */

public interface DBService {
    DBInfo getDBInfoByprimayrId(int primayrId);

    List< DBInfo> getDBInfos();
}
