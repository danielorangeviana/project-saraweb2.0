package com.br.saraweb20.entities.enums;

public enum ReligiousTitle {
	
	CAPELAO(1),
	PADRE(2);
	
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
