package com.br.saraweb20.entities.enums;

public enum ReligiousTitle {
	
	BISPO(1),
	PADRE(2),
	ARCEBISPO(3),
	DIACONO(4);
	
	
	private int code;
	
	private ReligiousTitle(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ReligiousTitle valueOf(int code) {
		for(ReligiousTitle value : ReligiousTitle.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid ReligiousTitle code!");
	}
}
