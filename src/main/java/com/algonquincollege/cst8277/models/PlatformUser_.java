package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T23:07:35.818+0000")
@StaticMetamodel(PlatformUser.class)
public class PlatformUser_ extends ModelBase_ {
	public static volatile SetAttribute<PlatformUser, PlatformRole> platformRoles;
	public static volatile SingularAttribute<PlatformUser, Customer> customer;
	public static volatile SingularAttribute<PlatformUser, String> username;
	public static volatile SingularAttribute<PlatformUser, String> pwHash;
}
