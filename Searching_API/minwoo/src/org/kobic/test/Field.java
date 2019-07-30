package org.kobic.test;

public enum Field {
	cn,
	bi,
	ti,
	au,
	pm,
	pp,
	ab,
	kw,
	og,
	pb,
	mn,
	bp,
	bn,
	bt,
	sp,
	ps,
	pe,
	op,
	mc,
	py,
	ma,
	mi,
	ri,
	sa,
	sb,
	sc,
	ts,
	te,
	sa1,
	sb1,
	sc1,
	sa2,
	sb2,
	sc2,
	sa3,
	sb3,
	sc3,
	ap1,
	ap2,
	ap3,
	tp1,
	tp2,
	tp3,
	pa,
	dp,
	tl,
	re,
	es,
	cp,
	po;
	
	public static boolean isMember(String aName) {
		Field[] fields = Field.values();
		for(Field field : fields) {
			if(field.name().equals(aName)) {
				return true;
			}
		}
		return false;
	}
	
}
