module br.com.nicolas.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;

    opens br.com.nicolas.dukemarket.javafx to javafx.fxml;
    opens br.com.nicolas.dukemarket.javafx.controller to javafx.fxml;
    
    exports br.com.nicolas.dukemarket.javafx;
    exports br.com.nicolas.dukemarket.javafx.controller;
    exports br.com.nicolas.dukemarket.javafx.DAO;
    exports br.com.nicolas.dukemarket.javafx.model;
    exports connection;
     
    requires mysql.connector.java;
}
