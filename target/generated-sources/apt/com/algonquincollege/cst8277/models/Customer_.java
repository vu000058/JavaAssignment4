package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.PlatformUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:35:48")
@StaticMetamodel(Customer.class)
public class Customer_ extends ModelBase_ {

    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile ListAttribute<Customer, Cart> carts;
    public static volatile SingularAttribute<Customer, PlatformUser> platformUser;
    public static volatile SingularAttribute<Customer, String> email;

}