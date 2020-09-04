package com.wisenut.infrastructures.repository.kakao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import com.wisenut.domain.model.kakaomap.QKaKaoMapOffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoMapOfferRespositoryCustomImpl implements KakaoMapOfferRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public IMapOffer findMapById(Long id) {
        IMapOffer iMapOffer = queryFactory.selectFrom(QKaKaoMapOffer.kaKaoMapOffer)
                .where(QKaKaoMapOffer.kaKaoMapOffer.id.eq(id))
                .fetchOne();
        return iMapOffer;
    }
}
