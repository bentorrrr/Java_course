package com.example.week8_generic;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Comparator;

public class MainApp extends Application {
    private final ObservableList<Student> data = FXCollections.observableArrayList();

    @Override public void start(Stage stage) {
        /* ---------- controls ---------- */
        TableView<Student> table = table();

        ComboBox<String> fieldBox = new ComboBox<>(
                FXCollections.observableArrayList("ID","First","Last","GPA"));
        fieldBox.getSelectionModel().selectFirst();

        ToggleGroup dirGroup = new ToggleGroup();
        RadioButton asc  = new RadioButton("Asc"); asc.setToggleGroup(dirGroup); asc.setSelected(true);
        RadioButton desc = new RadioButton("Desc"); desc.setToggleGroup(dirGroup);

        Button sortBtn = new Button("Sort");
        sortBtn.setOnAction(e -> sort(fieldBox.getValue(), desc.isSelected()));

        Button addBtn = new Button("Add");   addBtn.setOnAction(e -> createStudentDialog());
        Button delBtn = new Button("Delete");delBtn.setOnAction(e -> {
            Student sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) data.remove(sel);
        });

        HBox top = new HBox(8, new Label("Field:"), fieldBox, asc, desc, sortBtn, addBtn, delBtn);
        top.setPadding(new Insets(8));

        VBox root = new VBox(top, table);
        Scene sc = new Scene(root, 640, 400);
        stage.setTitle("Student sorter");
        stage.setScene(sc); stage.show();
    }


    private TableView<Student> table() {
        TableView<Student> t = new TableView<>(data);
        t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student,String>  id  = new TableColumn<>("ID");
        TableColumn<Student,String>  fn  = new TableColumn<>("First");
        TableColumn<Student,String>  ln  = new TableColumn<>("Last");
        TableColumn<Student,Number>  gpa = new TableColumn<>("GPA");

        // Lambdas that create a property wrapper each time the cell is rendered
        id.setCellValueFactory (c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getId()));
        fn.setCellValueFactory (c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getFirstName()));
        ln.setCellValueFactory (c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getLastName()));
        gpa.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyDoubleWrapper(c.getValue().getGpa()));

        t.getColumns().addAll(id, fn, ln, gpa);
        return t;
    }


    private void sort(String field, boolean isDesc) {
        Comparator<Student> cmp = switch (field) {
            case "ID"   -> StudentComparators.BY_ID;
            case "First"-> StudentComparators.BY_First;
            case "Last" -> StudentComparators.BY_Last;
            case "GPA"  -> StudentComparators.BY_GPA;
            default     -> StudentComparators.BY_ID;
        };
        if (isDesc) cmp = cmp.reversed();
        data.sort(cmp);        // ← built-in; or SortUtil.quickSort(data, cmp);
    }

    private void createStudentDialog() {
        Dialog<Student> dlg = new Dialog<>();
        dlg.setTitle("New student");

        TextField id   = new TextField();
        TextField fn   = new TextField();
        TextField ln   = new TextField();
        TextField gpa  = new TextField();

        GridPane gp = new GridPane(); gp.setHgap(10); gp.setVgap(10);
        gp.addRow(0, new Label("ID:"),   id);
        gp.addRow(1, new Label("First:"),fn);
        gp.addRow(2, new Label("Last:"), ln);
        gp.addRow(3, new Label("GPA:"),  gpa);

        dlg.getDialogPane().setContent(gp);
        dlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dlg.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    double g = Double.parseDouble(gpa.getText());
                    return new Student(id.getText(), fn.getText(), ln.getText(), g);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                }
            }
            return null;
        });

        dlg.showAndWait().ifPresent(s -> {
            if (data.stream().anyMatch(st -> st.getId().equals(s.getId()))) {
                new Alert(Alert.AlertType.WARNING, "Duplicate ID").show();
            } else {
                data.add(s);
            }
        });
    }

    public static void main(String[] args) { launch(args); }
}
