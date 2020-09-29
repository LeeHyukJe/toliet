package com.wisenut.domain.model.kakaomap;


import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KaKaoMapInfoRepository extends JpaRepository<KaKaoMapInfo,Long>, KaKaoMapInfoRepositoryCustom {

}
