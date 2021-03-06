import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class EditMarkGUI {
    private JFrame frame;
    private JLabel labelNume, labelPrenume, labelNota, labelMaterie, labelData;
    private JTextField nume, prenume, materie, data;
    private SpinnerModel spinnerModelNota;
    private JSpinner notaMaterie;
    private JButton editeaza, inapoi;
    public EditMarkGUI(int option, String email, String numeStudent, String prenumeStudent, int nota, String materieNota, Date dataUltimeiModificari){
        /*
        ====================
        initialize variables
        ====================
        */
        frame = new JFrame("Modificare notă");
        labelNume = new JLabel("Nume student: ");
        labelPrenume = new JLabel("Prenume student: ");
        labelMaterie = new JLabel("Materie: ");
        labelNota = new JLabel("Notă: ");
        labelData = new JLabel("Data ultimei modificări: ");
        editeaza = new JButton("Editează");
        inapoi = new JButton("Înapoi");
        spinnerModelNota = new SpinnerNumberModel(1,1,10,1);
        notaMaterie = new JSpinner(spinnerModelNota);
        nume = new JTextField(numeStudent);
        prenume = new JTextField(prenumeStudent);
        materie = new JTextField(materieNota);
        notaMaterie.setValue(nota);
        data = new JTextField(dataUltimeiModificari.toString());
        //add elements to the frame
        frame.add(labelNume);
        frame.add(nume);
        frame.add(labelPrenume);
        frame.add(prenume);
        frame.add(labelMaterie);
        frame.add(materie);
        frame.add(labelNota);
        frame.add(notaMaterie);
        frame.add(labelData);
        frame.add(data);
        frame.add(editeaza);
        frame.add(inapoi);
        //set white background
        frame.getContentPane().setBackground(Color.WHITE);
        //set textfields not editable
        nume.setEditable(false);
        prenume.setEditable(false);
        materie.setEditable(false);
        data.setEditable(false);
        //set bounds for elements
        labelNume.setBounds(170,60,170,25);
        nume.setBounds(310,60,250,25);
        labelPrenume.setBounds(170,90,170,25);
        prenume.setBounds(310,90,250,25);
        labelMaterie.setBounds(170,120,170,25);
        materie.setBounds(310,120,250,25);
        labelNota.setBounds(170,150,170,25);
        notaMaterie.setBounds(310,150,250,25);
        labelData.setBounds(170,180,170,25);
        data.setBounds(310,180,250,25);
        editeaza.setBounds(210,225,150,25);
        inapoi.setBounds(370,225,150,25);
        //buttons design
        editeaza.setBorderPainted(false);
        editeaza.setBackground(new Color(233,233,233));
        editeaza.setForeground(new Color(100,100,100));
        inapoi.setBorderPainted(false);
        inapoi.setBackground(new Color(233,233,233));
        inapoi.setForeground(new Color(100,100,100));
        //labels design
        labelNume.setForeground(new Color(100,100,100));
        labelPrenume.setForeground(new Color(100,100,100));
        labelMaterie.setForeground(new Color(100,100,100));
        labelNota.setForeground(new Color(100,100,100));
        labelData.setForeground(new Color(100,100,100));
        //set frame icon
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("resources/1.png")));
        }catch(IOException ie){
            ie.printStackTrace();
        }
        //set frame size
        frame.setPreferredSize(new Dimension(745, 350));
        frame.setLayout(null);
        frame.pack();
        //set window in the middle of the screen
        frame.setLocationRelativeTo(null);
        //set the default close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame visible
        frame.setVisible(true);
        /*
        ==============
        define actions
        ==============
        */
        //update the database with the new data
        editeaza.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (ManagerGUI.updateMarkFromDB(prenumeStudent, numeStudent, (int) notaMaterie.getValue(), materieNota, java.sql.Date.valueOf(LocalDate.now())) == 1) {
                    JOptionPane.showMessageDialog(null, "Nota a fost modificată în baza de date!");
                }
            }
        });
        //go back to select teacher to edit window
        inapoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                if(option == 1) {
                    EditMarkProfessorGUI window = new EditMarkProfessorGUI(email);
                }else if(option == 2){
                    EditMarkSecretaryGUI window = new EditMarkSecretaryGUI(email);
                }else{
                    EditMarkAdminGUI window = new EditMarkAdminGUI();
                }
            }
        });
    }
}
