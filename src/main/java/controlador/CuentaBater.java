package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cuenta;

import java.io.IOException;
import java.util.ArrayList;

public class CuentaBater extends Application {

    Cuenta cuenta1 = new Cuenta(1, "Alfonso", "18/07/2019", 1000.0);
    Cuenta cuenta2 = new Cuenta(2, "Jose Antonio", "2/08/2019", 2000.0);
    Cuenta cuenta3 = new Cuenta(3, "Juan Pablo", "12/04/2020", 50000.0);
    Cuenta cuenta4 = new Cuenta(4, "Ivan", "6/07/2020", 15000.0);
    Cuenta cuenta5 = new Cuenta(5, "Alex", "20/09/2021", 10000.0);

    //ArrayList para almacenar las cuentas bancarias.
    private ArrayList<Cuenta> listaCuentas = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        listaCuentas.add(cuenta1);
        listaCuentas.add(cuenta2);
        listaCuentas.add(cuenta3);
        listaCuentas.add(cuenta4);
        listaCuentas.add(cuenta5);
        FXMLLoader fxmlLoader = new FXMLLoader(CuentaBater.class.getResource("/vista/VisualizaCuentas.fxml"));
        Parent root = fxmlLoader.load();
        ModificaCuentas controlador = fxmlLoader.getController();
        controlador.iniciaLista(listaCuentas);
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(CuentaBater.class.getResourceAsStream("/vista/logo.png")));
        stage.setTitle("Aplicación Cuentas Bancarias");
        scene.getStylesheets().add(getClass().getResource("/vista/VisorStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {


        launch();
    }

}