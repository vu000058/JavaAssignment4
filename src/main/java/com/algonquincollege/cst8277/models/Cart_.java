package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-10T15:04:31.844+0000")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {
	public static volatile SingularAttribute<Cart, Customer> customer;
	public static volatile ListAttribute<Cart, CartItem> cartItems;
}
