package com.wisenut.domain.model.kakaomap

import com.wisenut.infrastructures.repository.kakao.KaKaoMapInfoRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface KaKaoMapInfoRepository : JpaRepository<KaKaoMapInfo?, Long?>, KaKaoMapInfoRepositoryCustom