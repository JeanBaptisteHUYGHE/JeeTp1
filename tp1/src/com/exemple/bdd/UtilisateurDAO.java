package com.exemple.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.exemple.beans.Utilisateur;

public class UtilisateurDAO extends DAOContext {
    public static Boolean isValidLogin( String login, String password ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            // String strSql = "SELECT * FROM T_Users WHERE login='" + login +
            // "' AND password='" + password + "'";
            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                statement.setString( 2, password );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static Boolean isValidEmail( String login ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {

            String strSql = "SELECT * FROM Utilisateur WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static void creerUtilisateur( Utilisateur utilisateur ) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "INSERT INTO Utilisateur (email, mot_de_passe, nom, isadmin, date_inscription) VALUES (?, ?, ?, ?, NOW())";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setString( 1, utilisateur.getEmail() );
                statement.setString( 2, utilisateur.getMotDePasse() );
                statement.setString( 3, utilisateur.getNom() );
                statement.setBoolean( 4, utilisateur.getAdmin() );
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public static Utilisateur getUtilisateurByEmail( String email ) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "SELECT id, email, nom, date_inscription, isadmin from Utilisateur WHERE email=?;";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setString( 1, email);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                	Utilisateur utilisateur = new Utilisateur();
                	utilisateur.setId(result.getLong(1));
                	utilisateur.setEmail(result.getString(2));
                	utilisateur.setMotDePasse("");
                	utilisateur.setNom(result.getString(3));
                	utilisateur.setDateInscription(result.getTimestamp(4));
                	utilisateur.setAdmin(result.getBoolean(5));
                	return utilisateur;
                }
                return null;
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public static ArrayList<Utilisateur> getUtilisateursList() {
    	ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
    	
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "SELECT id, email, nom, date_inscription, isadmin from Utilisateur;";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                	Utilisateur utilisateur = new Utilisateur();
                	utilisateur.setId(result.getLong(1));
                	utilisateur.setEmail(result.getString(2));
                	utilisateur.setMotDePasse("");
                	utilisateur.setNom(result.getString(3));
                	utilisateur.setDateInscription(result.getTimestamp(4));
                	utilisateur.setAdmin(result.getBoolean(5));
                	users.add(utilisateur);
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    return users;
    }
    
    public static void removeUtilisateurByEmail(String email) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "DELETE FROM Utilisateur WHERE email=?;";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
            	statement.setString( 1, email);
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
}
