package com.wisenut.domain.model.kakaomap;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoMapOfferRepository extends JpaRepository<KaKaoMapOffer, Long>, KakaoMapOfferRepositoryCustom{
}
