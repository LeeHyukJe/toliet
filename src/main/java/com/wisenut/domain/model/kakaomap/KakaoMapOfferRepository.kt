package com.wisenut.domain.model.kakaomap

import org.springframework.data.jpa.repository.JpaRepository
import com.wisenut.infrastructures.repository.kakao.KakaoMapOfferRepositoryCustom

interface KakaoMapOfferRepository : JpaRepository<KaKaoMapOffer?, Long?>, KakaoMapOfferRepositoryCustom {
    fun findByType(type: String?): KaKaoMapOffer?
}