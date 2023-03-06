module com.example.fuelqueueclassversionfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fuelqueueclassversionfx to javafx.fxml;
    exports com.example.fuelqueueclassversionfx;
}