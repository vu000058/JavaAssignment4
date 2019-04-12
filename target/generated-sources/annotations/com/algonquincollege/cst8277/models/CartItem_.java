package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:25:36")
@StaticMetamodel(CartItem.class)
public class CartItem_ extends ModelBase_ {

    public static volatile SingularAttribute<CartItem, Item> item;
    public static volatile SingularAttribute<CartItem, Integer> quantity;
    public static volatile SingularAttribute<CartItem, Cart> cart;

}