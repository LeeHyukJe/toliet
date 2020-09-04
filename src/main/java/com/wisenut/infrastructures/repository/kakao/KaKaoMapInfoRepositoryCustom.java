package com.wisenut.infrastructures.repository.kakao;

import com.wisenut.domain.model.IMapInfo;

import java.util.List;

public interface KaKaoMapInfoRepositoryCustom {
    List<IMapInfo> findByPlaceName(String name);
}
