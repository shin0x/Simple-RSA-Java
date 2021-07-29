import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Main extends JFrame {
    public Main(){
        initWindow();
    }

    private void initWindow() {
        this.setSize(600, 400);
        this.setTitle("RSA - Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //init layout
        this.setLayout(new GridLayout(5,1 ));

        //0 -> e; 1 -> N; 2 -> d;

        JPanel[] descriptionPanels = new JPanel[3];
        JTextField[] textFields = new JTextField[3];
        JLabel[] descriptionLabels = new JLabel[3];
        String[] description = {"e:", "n:", "d:"};

        for (int i = 0; i < descriptionLabels.length; i++){
            descriptionLabels[i] = new JLabel(description[i]);
            textFields[i] = new JTextField("", 50);

            descriptionPanels[i] = new JPanel(new FlowLayout());
            descriptionPanels[i].add(descriptionLabels[i]);
            descriptionPanels[i].add(textFields[i]);

        }


        //text input and output
        JPanel inputOutputPanel = new JPanel(new GridLayout(1, 2));

        JTextArea inputTextArea = new JTextArea("Input");
        inputTextArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inputTextArea.selectAll();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        inputTextArea.setLineWrap(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        JTextArea outputTextArea = new JTextArea("Output");
        outputTextArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                outputTextArea.selectAll();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        inputOutputPanel.add(inputScrollPane);
        inputOutputPanel.add(outputScrollPane);


        //Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(e -> {
            outputTextArea.setText(encrypt(textFields, inputTextArea.getText()));
        });

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(e -> {
            outputTextArea.setText(decrypt(textFields, inputTextArea.getText()));
        });

        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        //add everything to the frame
        for(JPanel p:descriptionPanels)
            this.add(p);

        this.add(inputOutputPanel);
        this.add(buttonPanel);

        this.setVisible(true);
    }


    //TextFields: 0 -> e; 1 -> N; 2 -> d;
    private String encrypt(JTextField[] textFields, String text) {
        //init values
        String e = "";
        String n = "";

        e = textFields[0].getText();
        n = textFields[1].getText();

        if(e.equals("") && n.equals("") && isANumber(text)){
            RSA rsa = new RSA();
            textFields[0].setText(rsa.getE().toString());
            textFields[1].setText(rsa.getN().toString());
            textFields[2].setText(rsa.getD().toString());

            return (rsa.encryptMessage(new BigInteger(text))).toString();
        }

        //if e and n are entered and valid numbers
        else if(!e.equals("") && !n.equals("") && isNumberValid(e) && isANumber(n) && isANumber(text)){
            RSA rsa = new RSA(new BigInteger(e), new BigInteger(n));
            if(textFields[2].getText().equals(""))
                textFields[2].setText(String.valueOf(rsa.getD()));
            return (rsa.encryptMessage(new BigInteger(text))).toString();
        }

        else{
            JOptionPane.showMessageDialog(null, "Please enter correct numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

    }

    private String decrypt(JTextField[] textFields, String text) {
        //init values
        String e, n, d = "";
        e = textFields[0].getText();
        n = textFields[1].getText();
        d = textFields[2].getText();

        //if d and n are entered and valid numbers
        if(!d.equals("") && !n.equals("") && isANumber(d) && isANumber(n) && isANumber(text)){
            return (new RSA(new BigInteger(e), new BigInteger(n), new BigInteger(d)).decryptMessage(new BigInteger(text))).toString();
        }

        else{
            JOptionPane.showMessageDialog(null, "Please enter correct numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    private boolean isNumberValid(String number){
        //check if it is a positive valid number
        if(number.matches("^[+]?\\d+([.]\\d+)?$")){
            //check if it is a prime number
            BigInteger bigInteger = new BigInteger(number);
            return bigInteger.isProbablePrime(100);
        }
        return false;
    }

    private boolean isANumber(String number){
        return number.matches("-?\\d+(\\.\\d+)?");
    }


    public static void main(String[] args) {
        Main main = new Main();

    }
}
