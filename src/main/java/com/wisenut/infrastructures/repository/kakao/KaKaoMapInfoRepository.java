package com.wisenut.infrastructures.repository.kakao;


import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KaKaoMapInfoRepository extends JpaRepository<KaKaoMapInfo,Long>, KaKaoMapInfoRepositoryCustom {

}
