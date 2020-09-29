package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import com.wisenut.infrastructures.repository.kakao.KakaoMapOfferRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoMapOfferRepository extends JpaRepository<KaKaoMapOffer, Long>, KakaoMapOfferRepositoryCustom {
    KaKaoMapOffer findByType(String type);
}
