package org.kobic.ntis;


public enum Field {
    cn, //과제번호
    bi, //Defalut (cn, ti, au, ab, kw, og, pb, mn, bp, bn, bt, op 포함)
    ti, //과제명
    au, //연구 책임자, 연구 참여자
    pm, //연구 책임자
    pp, //연구 참여자
    ab, //연구목표, 연구내용, 기대효과
    kw, //키워드
    og, //발주기관명
    pb, //주관연구기관명
    mn, //부처명
    bp, //예산사업명
    bn, //기관사업명
    bt, //대과제명
    sp, //보안과제여부 (1:일반 , 2;보안)
    ps, //연구시작일
    pe, //연구종료일
    op, //기관세부과제번호
    mc, //부처코드
    py, //기준년도
    ma, //대표전문기관명
    mi, //연구책임자인물ID
    ri, //연구참여자인물ID
    sa, //구분류코드-대
    sb, //구분류코드-중
    sc, //구분류코드-소
    ts, //총연구시작일
    te, //총연구종료일
    sa1,//신분류코드1-대
    sb1,//신분류코드1-중
    sc1,//신분류코드1-소
    sa2,//신분류코드2-대
    sb2,//신분류코드2-중
    sc2,//신분류코드2-소
    sa3,//신분류코드3-대
    sb3,//신분류코드3-중
    sc3,//신분류코드3-소
    ap1,//적용분야분류코드1
    ap2,//적용분야분류코드2
    ap3,//적용분야분류코드3
    tp1,//임시분류코드1
    tp2,//임시분류코드2
    tp3,//임시분류코드3
    pa,//연구수행주체코드
    dp,//연구개발단계코드
    tl,//기술수명주기코드
    re,//지역구분코드
    es,//경제사회목적코드
    cp,//연속과제여부(Y/N)
    po;//정책과제여부(Y/N)
	
    public static boolean isMember(String aName) {
    	//parameter 값이 Field 의 원소에 속하는지 여부를 boolean 형태로 반환
        Field[] fields = Field.values();
        for(Field field : fields) {
            if(field.name().equals(aName)) {
                return true;
            }
        }
        return false;
    }

}
