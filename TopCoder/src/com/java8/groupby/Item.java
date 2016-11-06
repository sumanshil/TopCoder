package com.java8.groupby;

import java.math.BigDecimal;

/**
 * Created by SumanChandra on 9/4/2016.
 */
public class Item {
    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String apple, int qty, BigDecimal bigDecimal) {
        this.name = apple;
        this.qty = qty;
        this.price = bigDecimal;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Item)obj).name);
    }

    public String getName() {
        return this.name;
    }
}
