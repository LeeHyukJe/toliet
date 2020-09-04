package com.wisenut.infrastructures.repository.kakao;

import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoMapOfferRepository extends JpaRepository<KaKaoMapOffer, Long>, KakaoMapOfferRepositoryCustom{
    KaKaoMapOffer findByType(String type);
}
