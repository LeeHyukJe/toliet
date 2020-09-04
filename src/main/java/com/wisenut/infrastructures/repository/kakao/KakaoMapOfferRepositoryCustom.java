package com.wisenut.infrastructures.repository.kakao;

import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;

import java.util.List;

public interface KakaoMapOfferRepositoryCustom {

    IMapOffer findMapById(Long id);
}
