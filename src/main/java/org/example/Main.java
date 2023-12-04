package org.example;
import javax.swing.*;
import  java.awt.*;
import  java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));

        JTextField inputField1 = new JTextField();
        JTextField inputField2 = new JTextField();
        JTextField outputField = new JTextField();
        outputField.setEnabled(false);
        outputField.setEditable(false);

        inputField1.setFont((new Font("Arial", Font.PLAIN, 30)));
        inputField2.setFont((new Font("Arial", Font.PLAIN, 30)));
        outputField.setFont((new Font("Arial", Font.PLAIN, 30)));

        JLabel label1 = new JLabel("Número 1:");
        label1.setFont((new Font("Arial", Font.BOLD, 30)));
        JLabel label2 = new JLabel("Número 2:");
        label2.setFont((new Font("Arial", Font.BOLD, 30)));
        JLabel label3 = new JLabel("RESULTADO:");
        label3.setFont((new Font("Arial", Font.BOLD, 30)));

        panel.add(label1);
        panel.add(inputField1);
        panel.add(label2);
        panel.add(inputField2);
        panel.add(label3);
        panel.add(outputField);

        String[] buttonLabels ={
                "+", "-", "/", "X"
        };

        for (String label: buttonLabels){
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            panel.add(button);

            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    double num1, num2, result = 0.0;
                    //Transformar em métodos
                    try {
                        num1 = Double.parseDouble(inputField1.getText());
                        num2 = Double.parseDouble(inputField2.getText());

                        if (label.equals("+")) {
                            Calculadora obj = new Calculadora();
                            result = obj.soma(num1,num2);
                        } else if (label.equals("-")) {
                            Calculadora obj = new Calculadora();
                            result = obj.subt(num1,num2);
                        }
                        else if (label.equals("X")) {
                            Calculadora obj = new Calculadora();
                            result = obj.mult(num1,num2);
                        }
                        else if (label.equals("/")) {
                            Calculadora obj = new Calculadora();
                            if (num2 != 0) {
                              result = obj.div(num1,num2);

                            } else {
                                outputField.setText("Erro: Divisão por zero");
                                return;
                            }
                        }
                    outputField.setText(Double.toString(result));
                }
                catch(NumberFormatException ex){
                    outputField.setText("Erro: Entrada Inválida");
                          }
                      }
                 });
            }

        port(8080);

        get("/json", (req,res) -> {

            double soma, subt, mult, div;

            soma =Double.parseDouble(inputField1.getText()) + Double.parseDouble(inputField2.getText());
            subt =Double.parseDouble(inputField1.getText()) - Double.parseDouble(inputField2.getText());
            mult =Double.parseDouble(inputField1.getText()) * Double.parseDouble(inputField2.getText());
            div =Double.parseDouble(inputField1.getText()) / Double.parseDouble(inputField2.getText());

           String content =  "{ \"param1\": \" " + inputField1.getText()+
                   "\", \"param2\": \""+ inputField2.getText() +
                   "\", \"soma\": \""+ Double.toString(soma) +
                   "\", \"subt\": \""+ Double.toString(subt) +
                   "\", \"mult\": \""+ Double.toString(mult) +
                    "\", \"div\": \""+ Double.toString(div) + "\"}";

           return content;
        });

        get("/xml", (req, res) ->{

            double soma, subt, mult, div;

            soma =Double.parseDouble(inputField1.getText()) + Double.parseDouble(inputField2.getText());
            subt =Double.parseDouble(inputField1.getText()) - Double.parseDouble(inputField2.getText());
            mult =Double.parseDouble(inputField1.getText()) * Double.parseDouble(inputField2.getText());
            div =Double.parseDouble(inputField1.getText()) / Double.parseDouble(inputField2.getText());

            String content = "<res><param1>" + inputField1.getText()+ "</param1>" +
            "<param2>"  + inputField2.getText() + " </param2>" +
                    "<soma>" + Double.toString(soma) + "</soma>"+
                    "<subt>" + Double.toString(subt) + "</subt>"+
                    "<mult>" + Double.toString(mult) + "</mult"+
                    "<div>" + Double.toString(div) + "</div></res>";
            return content;
                });

        path("/calculadora", () -> {
            post("/subt/", (req, res) -> "abcde");

            get("/param1/:p", (req, res)-> {
                String op1 = req.params(":p");
                inputField1.setText(op1);
                return "Parâmetro 1:" + op1;
            });

            get("/param2/:p", (req, res)-> {
                String op1 = req.params(":p");
                inputField2.setText(op1);
                return "Parâmetro 2:" + op1;
            });




        });
            frame.add(panel);
    frame.setVisible(true);
    }
}



