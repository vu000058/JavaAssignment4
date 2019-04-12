package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T21:10:40.191+0000")
@StaticMetamodel(Item.class)
public class Item_ extends ModelBase_ {
	public static volatile ListAttribute<Item, CartItem> cartItems;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, String> description;
	public static volatile SingularAttribute<Item, Double> price;
}
