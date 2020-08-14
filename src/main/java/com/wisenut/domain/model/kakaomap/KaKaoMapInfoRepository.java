package com.wisenut.domain.model.kakaomap;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KaKaoMapInfoRepository extends JpaRepository<KaKaoMapInfo,Long> {

    Optional<KaKaoMapInfo> findByAddressName(String addressName);
    Optional<KaKaoMapInfo> findByPlaceNameContaining(String stationName);
}
