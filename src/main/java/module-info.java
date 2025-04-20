module org.example.csc311regexhmwk {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311regexhmwk to javafx.fxml;
    exports org.example.csc311regexhmwk;
}