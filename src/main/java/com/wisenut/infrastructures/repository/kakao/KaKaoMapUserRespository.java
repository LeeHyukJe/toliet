package com.wisenut.infrastructures.repository.kakao;

import com.wisenut.domain.model.kakaomap.KakaoMapUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KaKaoMapUserRespository extends JpaRepository<KakaoMapUser,Long> {
}
