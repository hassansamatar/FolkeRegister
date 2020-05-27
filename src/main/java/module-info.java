module Folkeregister {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens Folkeregister to javafx.fxml;
    opens Folkeregister.personmodel.controllers to javafx.base, javafx.fxml;
    opens Folkeregister.personmodel.IO to javafx.base,javafx.fxml;
    opens  Folkeregister.personmodel to javafx.base;
    exports  Folkeregister.personmodel.controllers to javafx.fxml;
    exports Folkeregister;
}