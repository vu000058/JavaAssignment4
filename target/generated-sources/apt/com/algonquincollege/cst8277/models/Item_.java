package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.CartItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:35:48")
@StaticMetamodel(Item.class)
public class Item_ extends ModelBase_ {

    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile ListAttribute<Item, CartItem> cartItems;

}