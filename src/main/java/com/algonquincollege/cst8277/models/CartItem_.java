package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-05T21:29:59.794+0000")
@StaticMetamodel(CartItem.class)
public class CartItem_ extends ModelBase_ {
	public static volatile SingularAttribute<CartItem, Cart> cart;
	public static volatile SingularAttribute<CartItem, Item> item;
	public static volatile SingularAttribute<CartItem, Integer> quantity;
}
