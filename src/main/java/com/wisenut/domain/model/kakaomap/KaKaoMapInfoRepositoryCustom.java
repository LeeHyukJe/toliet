package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapInfo;

import java.util.List;

public interface KaKaoMapInfoRepositoryCustom {
    List<IMapInfo> findByPlaceName(String name);
}
