package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KaKaoMapInfoRepository extends JpaRepository<KaKaoMapInfo,Long> {
}
