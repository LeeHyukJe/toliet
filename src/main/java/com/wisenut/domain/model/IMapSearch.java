package com.wisenut.domain.model;

import java.util.Date;

public interface IMapSearch {

    void newSearch(Long id, Date createdDate, Long userId, Long mapId);
}
