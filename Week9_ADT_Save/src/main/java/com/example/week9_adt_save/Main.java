package com.example.week9_adt_save;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private final BinaryTree<Integer> tree = new BinaryTree<>();
    private final TreeCanvas<Integer> canvas = new TreeCanvas<>(tree);

    @Override public void start(Stage s) {
        TextField val  = new TextField(); val.setPromptText("number");
        TextField file = new TextField("bst.ser"); file.setPrefWidth(80);

        Button ins = new Button("Insert"), del=new Button("Delete"),
                sea = new Button("Search"), sav=new Button("Save"),
                loa = new Button("Load");

        ins.setOnAction(e -> act(val, v -> tree.insert(v)));
        del.setOnAction(e -> act(val, v -> tree.delete(v)));
        sea.setOnAction(e -> act(val, v -> canvas.setHighlight(v)));
        sav.setOnAction(e -> {
            try { tree.saveToFile(file.getText()); }
            catch (Exception ex) { alert("Save error", ex.getMessage()); }
        });
        loa.setOnAction(e -> {
            try {
                BinaryTree<Integer> t = BinaryTree.loadFromFile(file.getText());
                if (t != null) { tree.setRoot(t.getRoot()); canvas.setHighlight(null); canvas.redraw(); }
            } catch (Exception ex) { alert("Load error", ex.getMessage()); }
        });

        HBox controls = new HBox(8, new Label("Val"), val, ins, del, sea,
                new Label("File"), file, sav, loa);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(canvas, controls, null, null, null);
        Scene scene = new Scene(root, 900, 600);
        s.setScene(scene); s.setTitle("BST Visualiser (JavaFX)"); s.show();
    }

    private interface Op { void go(int v); }

    private void act(TextField tf, Op op) {
        try { int v = Integer.parseInt(tf.getText()); op.go(v); canvas.redraw(); }
        catch (NumberFormatException ignored) { alert("Input", "Enter an int"); }
        tf.clear();
    }

    private static void alert(String h, String c) {
        var a = new Alert(Alert.AlertType.INFORMATION, c, ButtonType.OK);
        a.setHeaderText(h); a.showAndWait();
    }

    public static void main(String[] args) { launch(args); }
}
