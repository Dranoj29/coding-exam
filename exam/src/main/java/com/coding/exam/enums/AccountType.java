package com.coding.exam.enums;

public enum AccountType {

	S("Savings"),
	C("Checking");
	
	private final String value;

    private AccountType(String label) {
        this.value = label;
    }
    
    public String getValue() {
        return this.value;
    }
}
