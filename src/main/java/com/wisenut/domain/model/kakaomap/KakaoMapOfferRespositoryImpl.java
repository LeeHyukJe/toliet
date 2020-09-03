package com.wisenut.domain.model.kakaomap;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class KakaoMapOfferRespositoryImpl implements KakaoMapOfferRepositoryCustom{

    private final JPAQueryFactory queryFactory;
}
