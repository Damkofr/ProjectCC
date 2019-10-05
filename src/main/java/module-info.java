module ProjectCPApril {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.base;
    exports com.controllerCP to javafx.graphics;
    opens com.controllerCP to javafx.fxml;
    exports com.logicCP to javafx.graphics;
    opens com.logicCP to javafx.fxml;
    exports com.databaseCP to javafx.graphics;
    opens com.databaseCP to javafx.fxml, javafx.base,java.sql;


}