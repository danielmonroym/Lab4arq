package com.udea.concesionario4.com.udea.concesionario4.modelo;

import com.udea.concesionario4.com.udea.concesionario4.modelo.Authority;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-07T13:32:54")
@StaticMetamodel(User.class)
public class User_ extends AbstractAuditingEntity_ {

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Instant> resetDate;
    public static volatile SingularAttribute<User, String> langKey;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> activationKey;
    public static volatile SingularAttribute<User, String> resetKey;
    public static volatile SetAttribute<User, Authority> authorities;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> activated;

}