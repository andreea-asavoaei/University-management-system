import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class VizualizareDatePersonaleGUI extends JFrame {
    List<Student> stud;
    String email;
    JFrame frame;
    JPanel panel1, panel2;
    JLabel welcome, labelNrMatricol, labelNume, labelPrenume, labelCnp, labelDataNasterii, labelNrTelefon, labelAdresa, labelAdresaEmail, labelFacultate, labelSpecializare, labelCicluUniversitar, labelAn, labelNrCredite;
    JTextField nrMatricol, nume, prenume, cnp, dataNasterii, nrTelefon, adresa, adresaEmail, facultate, specializare, cicluUniversitar, an, nrCredite;
    public VizualizareDatePersonaleGUI(String email){
        frame = new JFrame("Vizualizare date persoane");

        labelNrMatricol = new JLabel("Număr matricol: ");
        labelNume = new JLabel("Nume: ");
        labelPrenume = new JLabel("Prenume: ");
        labelCnp = new JLabel("CNP: ");
        labelDataNasterii = new JLabel("Data nașterii: ");
        labelNrTelefon = new JLabel("Număr de telefon: ");
        labelAdresa = new JLabel("Adresă: ");
        labelAdresaEmail = new JLabel("Adresă de email: ");
        labelFacultate = new JLabel("Facultate: ");
        labelSpecializare = new JLabel("Specializare: ");
        labelCicluUniversitar = new JLabel("Ciclu universitar: ");
        labelAn = new JLabel("An universitar: ");
        labelNrCredite = new JLabel("Număr de credite: ");

        ManagerGUI mng = new ManagerGUI();
        stud = mng.getInstance().getListaStudenti();
        String welcomeName;
        for(Student s:stud){
            if(s.getEmailAddress().equals(email)) {
                welcomeName = s.getLastName() + " " + s.getFirstName();
                nrMatricol = new JTextField(String.valueOf(s.getID()));
                nume = new JTextField(s.getLastName());
                prenume = new JTextField(s.getFirstName());
                cnp = new JTextField(s.getCnp());
                dataNasterii =  new JTextField(String.valueOf(s.getDob()));
                nrTelefon = new JTextField(s.getPhoneNumber());
                adresa = new JTextField(s.getAddress());
                adresaEmail = new JTextField(s.getEmailAddress());
                facultate = new JTextField(s.getFaculty());
                specializare = new JTextField(s.getDepartment());
                cicluUniversitar = new JTextField(s.getDegree().toString());
                an = new JTextField(String.valueOf(s.getYear()));
                nrCredite = new JTextField(String.valueOf(s.getNumberOfCredits()));
                welcome = new JLabel("Bun venit, " + welcomeName + "!");
            }
        }
        //set fields not editable so the user cannot modify any data
        nrMatricol.setEditable(false);
        nume.setEditable(false);
        prenume.setEditable(false);
        cnp.setEditable(false);
        dataNasterii.setEditable(false);
        nrTelefon.setEditable(false);
        adresa.setEditable(false);
        adresaEmail.setEditable(false);
        facultate.setEditable(false);
        specializare.setEditable(false);
        cicluUniversitar.setEditable(false);
        an.setEditable(false);
        nrCredite.setEditable(false);


        //set bounds for JLabels
        //welcome line
        welcome.setFont(new Font(String.valueOf(welcome.getFont().getName()),Font.PLAIN,20)); //set increased font size
        welcome.setBounds(240,2,300,100);
        //row 1
        labelNrMatricol.setBounds(150,100,120,25);
        nrMatricol.setBounds(270,100,270,25);
        //row 2
        labelNume.setBounds(150,130,120,25);
        nume.setBounds(270,130,270,25);
        //row 3
        labelPrenume.setBounds(150,160,120,25);
        prenume.setBounds(270,160,270,25);
        //row 4
        labelCnp.setBounds(150,190,120,25);
        cnp.setBounds(270,190,270,25);
        //row 5
        labelDataNasterii.setBounds(150,220,120,25);
        dataNasterii.setBounds(270,220,270,25);
        //row 6
        labelNrTelefon.setBounds(150,250,120,25);
        nrTelefon.setBounds(270,250,270,25);
        //row 7
        labelAdresa.setBounds(150,280,120,25);
        adresa.setBounds(270,280,270,25);
        //row 8
        labelAdresaEmail.setBounds(150,310,120,25);
        adresaEmail.setBounds(270,310,270,25);
        //row 9
        labelFacultate.setBounds(150,340,120,25);
        facultate.setBounds(270,340,270,25);
        //row 10
        labelSpecializare.setBounds(150,370,120,25);
        specializare.setBounds(270,370,270,25);
        //row 11
        labelCicluUniversitar.setBounds(150,400,120,25);
        cicluUniversitar.setBounds(270,400,270,25);
        //row 12
        labelAn.setBounds(150,430,120,25);
        an.setBounds(270,430,270,25);
        //row 13
        labelNrCredite.setBounds(150,460,120,25);
        nrCredite.setBounds(270,460,270,25);

        frame.add(welcome);

        frame.add(labelNrMatricol);
        frame.add(nrMatricol);

        frame.add(labelNume);
        frame.add(nume);

        frame.add(labelPrenume);
        frame.add(prenume);

        frame.add(labelCnp);
        frame.add(cnp);

        frame.add(labelDataNasterii);
        frame.add(dataNasterii);

        frame.add(labelNrTelefon);
        frame.add(nrTelefon);

        frame.add(labelAdresa);
        frame.add(adresa);

        frame.add(labelAdresaEmail);
        frame.add(adresaEmail);

        frame.add(labelFacultate);
        frame.add(facultate);

        frame.add(labelSpecializare);
        frame.add(specializare);

        frame.add(labelCicluUniversitar);
        frame.add(cicluUniversitar);

        frame.add(labelAn);
        frame.add(an);

        frame.add(labelNrCredite);
        frame.add(nrCredite);

        frame.setLayout(null);
        //set frame size
        frame.setPreferredSize(new Dimension(700,620));
        frame.pack();
        //set window in the middle of the screen
        frame.setLocationRelativeTo(null);
        //set the default close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //block resize operation
        frame.setResizable(false);
        //make visible frame
        frame.setVisible(true);

    }
    public static void main(String[] args){
        VizualizareDatePersonaleGUI a = new VizualizareDatePersonaleGUI("irina.damian@gmail.com");
    }
}
