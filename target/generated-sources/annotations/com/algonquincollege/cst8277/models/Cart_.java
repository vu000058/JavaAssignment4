package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.CartItem;
import com.algonquincollege.cst8277.models.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:25:36")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {

    public static volatile ListAttribute<Cart, CartItem> cartItems;
    public static volatile SingularAttribute<Cart, Customer> customer;

}