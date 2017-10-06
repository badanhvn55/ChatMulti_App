/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmenuswing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JMenuSwing extends JFrame {

    static Socket socket;
    static ServerSocket server;
    private JMenuBar menuBar;
    private JMenu menu, language;
    private JMenuItem connectServer, Vietnamese, English, Japanese;
    private JTextArea textArea;
    private JTextField text;
    private JButton button;
    private DataInputStream din;
    private DataOutputStream dout;

    public JMenuSwing() {
        super("Client");
        this.setSize(800, 600);
        this.setLayout(null);
        getContentPane().setBackground(Color.getHSBColor(55, 55, 55));

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        connectServer = new JMenuItem("Connect server");
        language = new JMenu("Language");
        language.setActionCommand("lang");
        menu.add(connectServer);
        menu.add(language);
        menu.setActionCommand("menu");
        connectServer.addActionListener(new event());

        Vietnamese = new JMenuItem("Vietnamese");
        Vietnamese.setActionCommand("vn");
        Vietnamese.addActionListener(new language());
        English = new JMenuItem("English");
        English.setActionCommand("en");
        English.addActionListener(new language());
        Japanese = new JMenuItem("Japanese");
        Japanese.setActionCommand("ja");
        Japanese.addActionListener(new language());
        language.add(Vietnamese);
        language.add(English);
        language.add(Japanese);
        menuBar.add(menu);

        textArea = new JTextArea();
        textArea.setBounds(100, 20, 600, 400);
        textArea.setBorder(BorderFactory.createLineBorder(Color.green));

        text = new JTextField();
        text.setBounds(100, 430, 485, 40);

        button = new JButton("send");
        button.setBounds(600, 430, 100, 40);
        button.addActionListener(new event());
        this.add(text);
        this.add(button);
        this.add(textArea);
        setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    class language implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Locale defaultLocale;
            ResourceBundle lang;
            if ("vn".equals(e.getActionCommand())) {
                defaultLocale = new Locale("vi", "VN");
                lang = ResourceBundle.getBundle("lang/language", defaultLocale);
                language.setText(lang.getString("lang"));
                Vietnamese.setText(lang.getString("vi"));
                English.setText(lang.getString("en"));
                Japanese.setText(lang.getString("ja"));
                connectServer.setText(lang.getString("connect"));
                button.setText(lang.getString("send"));
                menu.setText(lang.getString("menu"));
            }
            if ("en".equals(e.getActionCommand())) {
                defaultLocale = new Locale("en", "US");
                lang = ResourceBundle.getBundle("lang/language", defaultLocale);
                language.setText(lang.getString("lang"));
                Vietnamese.setText(lang.getString("vi"));
                English.setText(lang.getString("en"));
                Japanese.setText(lang.getString("ja"));
                connectServer.setText(lang.getString("connect"));
                button.setText(lang.getString("send"));
                menu.setText(lang.getString("menu"));
            }
            if ("ja".equals(e.getActionCommand())) {
                defaultLocale = new Locale("ja", "Japan");
                lang = ResourceBundle.getBundle("lang/language", defaultLocale);
                language.setText(lang.getString("lang"));
                Vietnamese.setText(lang.getString("vi"));
                English.setText(lang.getString("en"));
                Japanese.setText(lang.getString("ja"));
                connectServer.setText(lang.getString("connect"));
                button.setText(lang.getString("send"));
                menu.setText(lang.getString("menu"));
            }
        }

    }

    class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((e.getSource() == button) && (text.getText() != "")) {
                textArea.setText(textArea.getText() + "\n" + "Me: " + text.getText());
                try {
                    textArea.setText(textArea.getText() + "\n" + "Message sending fail: Network error");
                } catch (Exception e1) {
                    try {
                        Thread.sleep(3000);
                        System.exit(0);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                text.setText("");
            }
            if (e.getSource() == connectServer) {
                try {
                    socket = new Socket(InetAddress.getLocalHost(), 5555);
                    /*
		 * for remote pc use ip address of server with function
		 * InetAddress.getByName("Provide ip here")
		 * ChatHistory.setText("Connected to Server");
                     */
                    textArea.setText("Connected to Server");
                    while (true) {
                        try {
                            din = new DataInputStream(socket.getInputStream());
                            String string = din.readUTF();
                            textArea.setText(textArea.getText() + '\n' + "Server: " + string);
                        } catch (Exception e1) {
                            textArea.setText(textArea.getText() + "\n" + "Message sending fail: network error");
                            try {
                                Thread.sleep(3000);
                                System.exit(0);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        JMenuSwing menu = new JMenuSwing();

    }

}
