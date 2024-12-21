import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;


public class JDBCTest implements Runnable {

    protected TimerThread timerThread;

    @Override
    public void run() {

        //new LoginFrame().displayGUI();
        LoginFrame logowanie = new LoginFrame();
        logowanie.displayGUI();

        JFrame frame = new JFrame("Forum");


        JLabel nawigacja1 = new JLabel("Ustawienia");
        nawigacja1.setFont(nawigacja1.getFont().deriveFont(30.0f));
        nawigacja1.setBounds(320, 130, 1000, 40);
        frame.add(nawigacja1);
        nawigacja1.setVisible(false);

        JLabel nawigacja2 = new JLabel("Uzytkownicy");
        nawigacja2.setFont(nawigacja2.getFont().deriveFont(30.0f));
        nawigacja2.setBounds(320, 130, 1000, 40);
        frame.add(nawigacja2);
        nawigacja2.setVisible(false);

        JLabel nawigacja3 = new JLabel("Posty");
        nawigacja3.setFont(nawigacja3.getFont().deriveFont(30.0f));
        nawigacja3.setBounds(350, 130, 1000, 40);
        frame.add(nawigacja3);
        nawigacja3.setVisible(false);

        JLabel nawigacja4 = new JLabel("Kategorie");
        nawigacja4.setFont(nawigacja4.getFont().deriveFont(30.0f));
        nawigacja4.setBounds(330, 130, 1000, 40);
        frame.add(nawigacja4);
        nawigacja4.setVisible(false);

        JLabel nawigacja5 = new JLabel("Wiadomosci");
        nawigacja5.setFont(nawigacja5.getFont().deriveFont(30.0f));
        nawigacja5.setBounds(320, 130, 1000, 40);
        frame.add(nawigacja5);
        nawigacja5.setVisible(false);

        JLabel nawigacja6 = new JLabel("Home");
        nawigacja6.setFont(nawigacja6.getFont().deriveFont(30.0f));
        nawigacja6.setBounds(350, 130, 1000, 40);
        frame.add(nawigacja6);
        nawigacja6.setVisible(false);

        JLabel nawigacja7 = new JLabel("Aktywność");
        nawigacja7.setFont(nawigacja7.getFont().deriveFont(30.0f));
        nawigacja7.setBounds(320, 130, 1000, 40);
        frame.add(nawigacja7);
        nawigacja7.setVisible(false);

        JLabel nawigacja8 = new JLabel("Rankingi");
        nawigacja8.setFont(nawigacja8.getFont().deriveFont(30.0f));
        nawigacja8.setBounds(330, 130, 1000, 40);
        frame.add(nawigacja8);
        nawigacja8.setVisible(false);

        JMenu menu, menu2, menu3;
        {
            JMenuBar menuBar = new JMenuBar();
            menu = new JMenu("Opcje");
            menu2 = new JMenu("Nawigacja");

            menu3 = new JMenu("Pomoc");
            ActionListener al77 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Pomoc();
                }
            };
            menu3.addActionListener(al77);

            JMenuItem i1 = new JMenuItem("Home");
            KeyStroke ctrlB = KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i1.setAccelerator(ctrlB);
            ActionListener al01 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(true);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                }
            };
            i1.addActionListener(al01);

            JMenuItem i2 = new JMenuItem("Ustawienia");
            KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i2.setAccelerator(ctrlN);
            ActionListener al02 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(true);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                }
            };
            i2.addActionListener(al02);

            JMenuItem i3 = new JMenuItem("Posty");
            KeyStroke ctrlK = KeyStroke.getKeyStroke(KeyEvent.VK_K, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i3.setAccelerator(ctrlK);
            ActionListener al8 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(true);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                    new Posty();
                }
            };
            i3.addActionListener(al8);

            JMenuItem i4 = new JMenuItem("Uzytkownicy");
            KeyStroke ctrlH = KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i4.setAccelerator(ctrlH);
            ActionListener al2 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(true);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                    new Uzytkownicy();
                }
            };
            i4.addActionListener(al2);

            JMenuItem i5 = new JMenuItem("Aktywnosc");
            KeyStroke ctrlM = KeyStroke.getKeyStroke(KeyEvent.VK_M, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i5.setAccelerator(ctrlM);
            ActionListener al22 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(true);
                    nawigacja8.setVisible(false);
                }
            };
            i5.addActionListener(al22);

            JMenuItem i6 = new JMenuItem("Kategorie");
            KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i6.setAccelerator(ctrlP);
            ActionListener al05 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(true);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                    new Kategorie();
                }
            };
            i6.addActionListener(al05);

            JMenuItem i7 = new JMenuItem("Rankingi");
            KeyStroke ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i7.setAccelerator(ctrlU);
            ActionListener al97 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(false);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(true);
                }
            };
            i7.addActionListener(al97);

            JMenuItem i8 = new JMenuItem("Wiadomosci");
            KeyStroke ctrlT = KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            i8.setAccelerator(ctrlT);
            ActionListener al60 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nawigacja1.setVisible(false);
                    nawigacja2.setVisible(false);
                    nawigacja3.setVisible(false);
                    nawigacja4.setVisible(false);
                    nawigacja5.setVisible(true);
                    nawigacja6.setVisible(false);
                    nawigacja7.setVisible(false);
                    nawigacja8.setVisible(false);
                }
            };
            i8.addActionListener(al60);

            JMenuItem wyloguj = new JMenuItem("Wyloguj");
            KeyStroke ctrlW = KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            wyloguj.setAccelerator(ctrlW);
            ActionListener al3 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int a = JOptionPane.showConfirmDialog(wyloguj,"Czy chcesz sie wylogowac?");
                    if (a == JOptionPane.YES_OPTION) {
                        frame.setVisible(false);
                        new LoginFrame().displayGUI();
                    }
                    frame.setVisible(true);
                }
            };
            wyloguj.addActionListener(al3);

            JMenuItem wyjscie = new JMenuItem("Wyjscie");
            KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
            wyjscie.setAccelerator(ctrlF);
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            wyjscie.addActionListener(al);

            menu.add(wyloguj);
            menu.add(wyjscie);
            menu2.add(i1);
            menu2.add(i2);
            menu2.add(i3);
            menu2.add(i4);
            menu2.add(i5);
            menu2.add(i6);
            menu2.add(i7);
            menu2.add(i8);
            menuBar.add(menu);
            menuBar.add(menu2);
            menuBar.add(menu3);
            frame.setJMenuBar(menuBar);
        }




        JButton Ustawienia = new JButton("Ustawienia");
        Ustawienia.setBounds(20, 30, 100, 25);
        Ustawienia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(true);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
            }
        });
        frame.add(Ustawienia);

        JButton Adm_User = new JButton("Uzytkownicy");
        Adm_User.setBounds(20, 80, 100, 25);
        Adm_User.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(true);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
                new Uzytkownicy();
            }
        });
        frame.add(Adm_User);

        JButton Posty = new JButton("Posty");
        Posty.setBounds(150, 80, 100, 25);
        Posty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(true);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
                new Posty();
            }
        });
        frame.add(Posty);

        JButton Kategorie = new JButton("Kategorie");
        Kategorie.setBounds(280, 80, 100, 25);
        Kategorie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(true);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
                new Kategorie();
            }
        });
        frame.add(Kategorie);

        JButton Wiadomosci = new JButton("Wiadomosci");
        Wiadomosci.setBounds(410, 80, 100, 25);
        Wiadomosci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(true);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
            }
        });
        frame.add(Wiadomosci);

        JButton Home = new JButton("Home");
        Home.setBounds(150, 30, 100, 25);
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(true);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(false);
            }
        });
        frame.add(Home);

        JButton Aktywnosc = new JButton("Aktywnosc");
        Aktywnosc.setBounds(280, 30, 100, 25);
        Aktywnosc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(true);
                nawigacja8.setVisible(false);
            }
        });
        frame.add(Aktywnosc);

        JButton Rankingi = new JButton("Rankingi");
        Rankingi.setBounds(410, 30, 100, 25);
        Rankingi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nawigacja1.setVisible(false);
                nawigacja2.setVisible(false);
                nawigacja3.setVisible(false);
                nawigacja4.setVisible(false);
                nawigacja5.setVisible(false);
                nawigacja6.setVisible(false);
                nawigacja7.setVisible(false);
                nawigacja8.setVisible(true);
            }
        });
        frame.add(Rankingi);

        JLabel tekst_log_naz = new JLabel("Witaj: "+logowanie.txfLogin.getText());
                tekst_log_naz.setFont(tekst_log_naz.getFont().deriveFont(18.0f));
                tekst_log_naz.setBounds(540,10, 200,30);
                frame.add(tekst_log_naz);

        JButton wyloguj = new JButton("Wyloguj");
        wyloguj.setBounds(540,40, 100,25);
        wyloguj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int a = JOptionPane.showConfirmDialog(wyloguj,"Czy chcesz sie wylogowac?");
                if (a == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    new LoginFrame().displayGUI();
                }
                frame.setVisible(true);
            }
        });
        frame.add(wyloguj);


        MyCanvas zdj=new MyCanvas();
        zdj.setSize(600,550);
        zdj.setVisible(true);
        frame.add(zdj);

        JLabel tekst1 = new JLabel("Super apka desktopowa do administrowania super forum");
        tekst1.setFont(tekst1.getFont().deriveFont(18.0f));
        tekst1.setBounds(140, 550, 1000, 40);
        frame.add(tekst1);

        frame.setSize(800,709);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JStatusBar statusBar = new JStatusBar();
        JLabel leftLabel = new JLabel("Aplikacja dziala poprawnie");
        statusBar.setLeftComponent(leftLabel);

        final JLabel dateLabel = new JLabel();
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.addRightComponent(dateLabel);

        final JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.addRightComponent(timeLabel);

        contentPane.add(statusBar, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        timerThread = new TimerThread(dateLabel, timeLabel);
        timerThread.start();


        //frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    public void exitProcedure() {
        timerThread.setRunning(false);
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new JDBCTest());

        //Setting up properties
        String host,port,db,user;
        if (Files.notExists(Paths.get("config.ini"))) {
            FileOutputStream out = new FileOutputStream("config.ini");
            Properties applicationProps = new Properties();

            applicationProps.setProperty("Host", "127.0.0.1");
            applicationProps.setProperty("Port", "55555");
            applicationProps.setProperty("db", "forum");
            applicationProps.setProperty("user", "root");

            applicationProps.store(out, "---No Comment---");
            out.close();
            host = applicationProps.getProperty("Host");
            port = applicationProps.getProperty("Port");
            db = applicationProps.getProperty("db");
            user = applicationProps.getProperty("user");
        }
        else {
            Properties applicationProps = new Properties();
            FileInputStream in = new FileInputStream("config.ini");
            applicationProps.load(in);
            in.close();
            System.out.println(applicationProps.getProperty("port"));
            host = applicationProps.getProperty("Host");
            port = applicationProps.getProperty("Port");
            db = applicationProps.getProperty("db");
            user = applicationProps.getProperty("user");
        }
    }

    public class TimerThread extends Thread {

        protected boolean isRunning;

        protected JLabel dateLabel;
        protected JLabel timeLabel;

        protected SimpleDateFormat dateFormat =
                new SimpleDateFormat("EEE, d MMM yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread(JLabel dateLabel, JLabel timeLabel) {
            this.dateLabel = dateLabel;
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        dateLabel.setText(dateFormat.format(currentTime));
                        timeLabel.setText(timeFormat.format(currentTime));
                    }
                });

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }

}