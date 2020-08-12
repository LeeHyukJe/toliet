package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapInfoRepository extends JpaRepository<KaKaoMapInfo,Long> {
}
