package controlador;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.Cuenta;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModificaCuentas implements Initializable {

    //Cuentas bancaria precreadas.
    Cuenta cuenta1 = new Cuenta(1, "Alfonso", "18/07/2019", 1000.0);
    Cuenta cuenta2 = new Cuenta(2, "Jose Antonio", "2/08/2019", 2000.0);
    Cuenta cuenta3 = new Cuenta(3, "Juan Pablo", "12/04/2020", 50000.0);
    Cuenta cuenta4 = new Cuenta(4, "Ivan", "6/07/2020", 15000.0);
    Cuenta cuenta5 = new Cuenta(5, "Alex", "20/09/2021", 10000.0);

    //ArrayList para almacenar las cuentas bancarias.
    ArrayList<Cuenta> listaCuentas = new ArrayList<>();

    //Atributos
    public int contadorCuentas = 0;
    public boolean identBotNueva = false;

    //Elementos de la interfaz.
    public Label etiExist, etiNueva, numExist, num50000;
    public TextField numero, titular, fecha, saldo;
    public Button siguiente, atras, nueva, aceptar, cancelar;

    //Método que se ejecuta automaticamente al iniciar el programa.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Do nothing because of X and Y.
    }

    public void iniciaLista(ArrayList<Cuenta> listaCuentas){

        this.listaCuentas = listaCuentas;
        //Llama al método para contar el total de cuentas y las que tienen más de 50000 de saldo.
        contarCuentas();

        //Llama al método para actualizar los campos de texto.
        actualizarDatos();

        //Llama al método que crea los menús contextuales de los botones "<<" y ">>".
        menuContextual();

    }

    //Método que se ejecutará al pulsar el botón ">>".
    public void accionSiguiente() {

        //Si se ha llegado al final de la lsita, cambia el botón ">>" por "Nueva".
        if (contadorCuentas == (listaCuentas.size()) - 2) {
            identBotNueva = true;
            siguiente.setVisible(false);
            siguiente.setDisable(true);
            nueva.setVisible(true);
            nueva.setDisable(false);
        }

        //Si no se ha llegado al final de la lista aumenta el contador.
        if (contadorCuentas < (listaCuentas.size()) - 1) {
            contadorCuentas++;
        }

        //Si pasas el inicio de la lista, muestra el botón "<<".
        if (contadorCuentas > 0) {
            atras.setVisible(true);
            atras.setDisable(false);
        }

        //Llama al método para actualizar los campos de texto.
        actualizarDatos();
    }

    //Método que se ejecutará al pulsar el botón "<<".
    public void accionAtras() {

        //Si se retrocedes desde el final de la lsita, cambia el botón "Nueva" por ">>".
        if (identBotNueva) {
            siguiente.setVisible(true);
            siguiente.setDisable(false);
            nueva.setVisible(false);
            nueva.setDisable(true);
            identBotNueva = false;
        }

        //Si no se ha llegado al principio de la lista disminuye el contador.
        if (contadorCuentas > 0) {
            contadorCuentas--;
        }

        //Si se ha llegado al principio de la lista oculta el botón "<<".
        if (contadorCuentas == 0) {
            atras.setVisible(false);
            atras.setDisable(true);
        }

        //Llama al método para actualizar los campos de texto.
        actualizarDatos();
    }

    //Método que se ejecutará al pulsar el botón "Nueva".
    public void accionNueva() {

        //Cambia el título.
        etiExist.setVisible(false);
        etiNueva.setVisible(true);

        //Limpia los campos de texto.
        numero.clear();
        titular.clear();
        fecha.clear();
        saldo.clear();

        //Permite que se pueda escribir en los campos de texto.
        numero.setEditable(true);
        titular.setEditable(true);
        fecha.setEditable(true);
        saldo.setEditable(true);

        //Oculta los botones "Nueva" y "<<".
        nueva.setVisible(false);
        nueva.setDisable(true);
        atras.setVisible(false);
        atras.setDisable(true);

        //Muestra los botones "Aceptar" y "Cancelar".
        aceptar.setVisible(true);
        aceptar.setDisable(false);
        cancelar.setVisible(true);
        cancelar.setDisable(false);
    }

    //Método que se ejecutará al pulsar el botón "Nueva".
    public void accionAceptar() {

        //Almacena el color que tendrán los campos de texto cuando se detecte un error de formato.
        String redField = "-fx-background-color: #ED6D6D";

        //Limpia las propiedades de los campos de texto.
        numero.setStyle("");
        titular.setStyle("");
        fecha.setStyle("");
        saldo.setStyle("");

        boolean isValid = true; //Boolean para indicar si el formato es correcto o no.
        new SimpleDateFormat("dd-MM-yyyy"); //Formato que deben tener las fechas.

        //Se ejecuta si el campo "Número" NO está vacío y es un número.
        if (!numero.getText().isEmpty() && !isNumeric(numero.getText())) {

            //Comprueba si la cuenta ya existe.
            for (Cuenta cuenta : listaCuentas) {
                if (Integer.parseInt(numero.getText()) == cuenta.getNumCuenta()) {
                    isValid = false;
                    numero.setStyle(redField);
                }
            }
        } else { //Se ejecuta si el campo está "Número" vacío o No es un número.
            isValid = false;
            numero.setStyle(redField);
        }

        //Se ejecuta si el campo "Titular" NO está vacío.
        if (!titular.getText().isEmpty()) {

            //Se ejecuta si el contenido del campo es un número.
            if (!isNumeric(titular.getText())) {
                isValid = false;
                titular.setStyle(redField);
            }
        } else { //Se ejecuta si el campo está "Titular" vacío.
            isValid = false;
            titular.setStyle(redField);
        }

        //Se ejecuta si el campo "Fecha" NO está vacío.
        if (!fecha.getText().isEmpty()) {

            //Se ejecuta si el formato de la fecha NO es correcto.
            if (!validDate(fecha.getText())) {
                isValid = false;
                fecha.setStyle(redField);
            }
        } else { //Se ejecuta si el campo está "Fecha" vacío.
            isValid = false;
            fecha.setStyle(redField);
        }

        //Se ejecuta si el campo "Saldo" NO está vacío.
        if (!saldo.getText().isEmpty()) {

            //Se ejecutara si el contenido del campo no es un número.
            if (isNumeric(saldo.getText())) {
                isValid = false;
                saldo.setStyle(redField);
            }
        } else { //Se ejecuta si el campo está "Saldo" vacío.
            isValid = false;
            saldo.setStyle(redField);
        }

        //Se ejecutara si el contenido de todos los campos de texto son correctos.
        if (isValid) {

            //Crea una nueva cuenta con los datos de los campos de texto.
            Cuenta newCuenta = new Cuenta(Integer.parseInt(numero.getText()), titular.getText(), fecha.getText(), Double.parseDouble(saldo.getText()));
            listaCuentas.add(newCuenta); //Añade la cuenta a la lista.
            contadorCuentas++; //Suma 1 al contador total de cuentas.

            //Cambia el título.
            etiNueva.setVisible(false);
            etiExist.setVisible(true);

            //Evita que se pueda escribir en los campos de texto.
            numero.setEditable(false);
            titular.setEditable(false);
            fecha.setEditable(false);
            saldo.setEditable(false);

            //Oculta los botones "Aceptar" y "Cancelar".
            aceptar.setVisible(false);
            aceptar.setDisable(true);
            cancelar.setVisible(false);
            cancelar.setDisable(true);

            //Muestra los botones "Nueva" y "<<".
            atras.setVisible(true);
            atras.setDisable(false);
            nueva.setVisible(true);
            nueva.setDisable(false);

            //Llama al método para contar el total de cuentas y las que tienen más de 50000 de saldo.
            contarCuentas();
        }
    }

    //Método que se ejecutará al pulsar el botón "Cancelar".
    public void accionCancelar() {

        //Limpia las propiedades de los campos de texto.
        numero.setStyle("");
        titular.setStyle("");
        fecha.setStyle("");
        saldo.setStyle("");

        //Cambia el título.
        etiNueva.setVisible(false);
        etiExist.setVisible(true);

        //Evita que se pueda escribir en los campos de texto.
        numero.setEditable(false);
        titular.setEditable(false);
        fecha.setEditable(false);
        saldo.setEditable(false);

        //Llama al método para actualizar los campos de texto.
        actualizarDatos();

        //Oculta los botones "Aceptar" y "Cancelar".
        aceptar.setVisible(false);
        aceptar.setDisable(true);
        cancelar.setVisible(false);
        cancelar.setDisable(true);

        //Muestra los botones "Nueva" y "<<".
        atras.setVisible(true);
        atras.setDisable(false);
        nueva.setVisible(true);
        nueva.setDisable(false);
    }

    //Método para contar el total de cuentas y las que tienen más de 50000 de saldo.
    public void contarCuentas() {

        int cont = 0;

        //Indica el número total de cuentas existentes a partir del tamaño de la lista.
        numExist.setText(String.valueOf(listaCuentas.size()));

        //Busca en la lista las cuentas que tengan más de 50000 de saldo.
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getSaldo() >= 50000) {
                cont++;
            }
        }

        //Indica el número de cuentas con más de 50000 de saldo según las que haya encontrado el bucle "for".
        num50000.setText(String.valueOf(cont));
    }

    //Método para actualizar los campos de texto.
    public void actualizarDatos() {

        numero.setText(String.valueOf(listaCuentas.get(contadorCuentas).getNumCuenta()));
        titular.setText(listaCuentas.get(contadorCuentas).getTitular());
        fecha.setText(listaCuentas.get(contadorCuentas).getFechaApertura());
        saldo.setText(String.valueOf(listaCuentas.get(contadorCuentas).getSaldo()));
    }

    //Método para averiguar si el contenido de un campo es numérico o no.
    public boolean isNumeric(String strNum) {

        //Devuelve false si la cadena esta vacía.
        if (strNum == null) {
            return false;
        }

        //Si la cadena se puede tranformar en double devuelve true.
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }

        return false;
    }

    //Método para averiguar que el formato de una fecha es correcto o no.
    public boolean validDate(String fecha) {

        Date date;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            date = dateFormat.parse(fecha);
            Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
            calendario.setTime(date);
            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            if (mes > 12 || dia > 31 || ano > 2021) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    //Método que crea los menús contextuales de los botones "<<" y ">>".
    public void menuContextual() {

        //Crea los menús contextuales con sus opciones.
        ContextMenu menuAtras = new ContextMenu(new MenuItem("Atrás"), new MenuItem("Back"), new MenuItem("Derrière"), new MenuItem("Atrás"));
        ContextMenu menuSiguiente = new ContextMenu(new MenuItem("Adelante"), new MenuItem("Next"), new MenuItem("Prochain"), new MenuItem("Seguinte"));

        menuSiguiente.setStyle("");
        //Agrega cada menú a su respectivo botón.
        siguiente.setContextMenu(menuSiguiente);
        atras.setContextMenu(menuAtras);
    }
}