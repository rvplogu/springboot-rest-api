package com.scop.androidmdm.userservice.server;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;


public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {
	 
    @Override
    public Identifier toPhysicalCatalogName( Identifier identifier,  JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }
 
    @Override
    public Identifier toPhysicalColumnName( Identifier identifier,  JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }
 
    @Override
    public Identifier toPhysicalSchemaName( Identifier identifier,  JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }
 
    @Override
    public Identifier toPhysicalSequenceName( Identifier identifier,  JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }
 
    @Override
    public Identifier toPhysicalTableName( Identifier identifier,  JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }
 
    private Identifier convertToSnakeCase( Identifier identifier) {
         String regex = "([a-z])([A-Z])";
         String replacement = "$1_$2";
         String newName = identifier.getText()
          .replaceAll(regex, replacement)
          .toLowerCase();
        System.out.println(newName);
        return Identifier.toIdentifier(newName);
    }

	
}