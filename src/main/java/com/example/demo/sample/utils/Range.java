package com.example.demo.sample.utils;




public enum Range {

    TEN(10),
    HUNDRED(100),
    THOUSAND(1000);

    private int val;
    Range(int val) {
        this.val = val;
    }

    public static int getVal(Range range) {
        for (Range r:Range.values()) {
            if (r.equals(range)) {
                return r.val;
            }
        }
        return -1;
    }
    public static Range getRange(String range) {
        if (range.equals("10")) {
            return TEN;
        }
        if (range.equals("100")){
            return HUNDRED;
        }
        return THOUSAND;

    }
}
