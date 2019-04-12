package com.algonquincollege.cst8277.models;

import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.PlatformRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-10T15:25:36")
@StaticMetamodel(PlatformUser.class)
public class PlatformUser_ extends ModelBase_ {

    public static volatile SetAttribute<PlatformUser, PlatformRole> platformRoles;
    public static volatile SingularAttribute<PlatformUser, String> pwHash;
    public static volatile SingularAttribute<PlatformUser, String> username;
    public static volatile SingularAttribute<PlatformUser, Customer> customer;

}