package es.ieslosmontecillos.lambdaactionevent;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.lang.Math.*;


public class LambdaCalculator extends Application {

    double calculateMonthlyPay(String intrst, String yr, String lamt){
        try{
            double interest = Double.parseDouble(intrst);
            double yrs = Double.parseDouble(yr);
            double loan = Double.parseDouble(lamt);

            return (loan * (interest / (100*12))) / (1 - (1 / pow(1 + (interest / (100*12)), (12 * yrs))));
        }catch(NumberFormatException err){
            return -1;
        }
    }
    @Override
    public void start(Stage stage) {
        // Creación de un GridPane y asignación al Scene
        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 320, 240);

        // Interés anual
        Label annual = new Label("Annual Interest Rate:");
        TextField annl = new TextField();
        root.add(annual,0,0);
        root.add(annl,1,0);
        annl.setAlignment(Pos.BASELINE_RIGHT);

        // Número de años
        Label nyears = new Label("Number of Years:");
        TextField nyrs = new TextField();
        root.add(nyears,0,1);
        root.add(nyrs,1,1);
        nyrs.setAlignment(Pos.BASELINE_RIGHT);

        // Hipoteca
        Label lamount = new Label("Loan ammount:");
        TextField lamnt = new TextField();
        root.add(lamount,0,2);
        root.add(lamnt,1,2);
        lamnt.setAlignment(Pos.BASELINE_RIGHT);

        // Paga mensual
        Label monthpay = new Label("Monthly Payment:");
        TextField mnthpy = new TextField();
        root.add(monthpay,0,3);
        root.add(mnthpy,1,3);
        mnthpy.setAlignment(Pos.BASELINE_RIGHT);

        // Paga total
        Label total = new Label("Total payment:");
        TextField tot = new TextField();
        root.add(total,0,4);
        root.add(tot,1,4);
        tot.setAlignment(Pos.BASELINE_RIGHT);

        // Cálculo del total
        Button calculate = new Button("Calculate");
        root.add(calculate,1,5);

        calculate.setOnAction(e-> {
            double mpay = calculateMonthlyPay(annl.getText(),nyrs.getText(), lamnt.getText());

            // Verifica si está introducido un número
            if(mpay==-1){
                annl.setText("");
                nyrs.setText("");
                lamnt.setText("");
            }else {
                mnthpy.setText(String.format("$%.2f",mpay));
                tot.setText(String.format("$%.2f",(mpay*12)));
            }
        });
        stage.setTitle("Lambda Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}