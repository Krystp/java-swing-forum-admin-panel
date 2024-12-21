import javax.swing.*;
import java.awt.Font;


public class Pomoc {

    JFrame frame;

    /**
     * @wbp.parser.entryPoint
     */

    public Pomoc() {

            frame = new JFrame();
            frame.setTitle("Pomoc");
            frame.setSize(529, 449);
            frame.getContentPane().setLayout(null);

            JTextPane txtpnwKategoriiUytkownicy = new JTextPane();
            txtpnwKategoriiUytkownicy.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtpnwKategoriiUytkownicy.setText("-W kategorii U\u017Cytkownicy mo\u017Cesz wy\u015Bwietli\u0107 z sortowaniem wszystkich obecnych u\u017Cytkownik\u00F3w w systemie, mo\u017Cesz doda\u0107 nowych u\u017Cytkownik\u00F3w, zaktualizowa\u0107 obecnych lub usun\u0105\u0107 jakiego\u015B u\u017Cytkownika\r\n-W kategorii Posty mo\u017Cesz wy\u015Bwietli\u0107 aktualne posty, doda\u0107 nowe lub usun\u0105\u0107 istniej\u0105ce\r\n-W kategorii Kategorie mo\u017Cesz wy\u015Bwietli\u0107 kategorie\r\n\r\nSkr\u00F3ty Nawigacyjne:\r\nCtrl-B  Przeniesie nas do kategorii Home\r\nCtrl-N  Przeniesie nas do kategorii Ustawienia\r\nCtrl-K  Przeniesie nas do kategorii Posty\r\nCtrl-H  Przeniesie nas do kategorii U\u017Cytkownicy\r\nCtrl-M  Przeniesie nas do kategorii Aktywnosc\r\nCtrl-P  Przeniesie nas do kategorii Kategorie\r\nCtrl-U  Przeniesie nas do kategorii Rankingi\r\nCtrl-T  Przeniesie nas do kategorii Wiadomosci\r\nSkr\u00F3ty Opcji:\r\nCtrl-W  Wyloguje nas z obecnego konta\r\nCtrl-F  Zamknie aplikacj\u0119");
            txtpnwKategoriiUytkownicy.setBounds(57, 28, 401, 358);
            frame.getContentPane().add(txtpnwKategoriiUytkownicy);

            frame.setVisible(true);

    }
}