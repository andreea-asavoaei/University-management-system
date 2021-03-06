import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

public class EditStudentAdminGUI {
    private JFrame frame;
    private JComboBox<Faculty> faculties;
    private JComboBox<Department> departments;
    private HashSet<Faculty> facultati;
    private HashSet<Department> specializari;
    private HashSet<Student> studenti;
    private StudentTableModel dataModel;
    private JTable tabelStudenti = new JTable();
    private JScrollPane scrollPane = new JScrollPane(tabelStudenti);
    private JButton selecteaza, inapoi;

    public EditStudentAdminGUI() {
        /*
        ====================
        initialize variables
        ====================
        */
        frame = new JFrame("Modificare date student");
        faculties = new JComboBox<>();
        departments = new JComboBox<>();
        selecteaza = new JButton("Selectează");
        inapoi = new JButton("Înapoi");
        ManagerGUI mng = new ManagerGUI();
        facultati = ManagerGUI.getSetFacultati();
        specializari = ManagerGUI.getSetSpecializari();
        studenti = ManagerGUI.getSetStudenti();
        int n = studenti.size();
        dataModel = new StudentTableModel(n);
        //add all faculties into combobox
        faculties.addItem(new Faculty("Toate facultățile"));
        for (Faculty f : facultati) {
            faculties.addItem(f);
        }
        //add all departments into combobox
        departments.addItem(new Department("Toate specializările"));
        for (Department d : specializari) {
            departments.addItem(d);
        }
        //add all students into table
        int i = 0;
        for (Student s : studenti) {
            dataModel.setValueAt(s.getLastName(), i, 0);
            dataModel.setValueAt(s.getFirstName(), i, 1);
            dataModel.setValueAt(s.getCnp(), i, 2);
            dataModel.setValueAt(s.getDob(), i, 3);
            dataModel.setValueAt(s.getPhoneNumber(), i, 4);
            dataModel.setValueAt(s.getAddress(), i, 5);
            dataModel.setValueAt(s.getEmailAddress(), i, 6);
            dataModel.setValueAt(s.getFaculty(), i, 7);
            dataModel.setValueAt(s.getDepartment(), i, 8);
            dataModel.setValueAt(s.getDegree(), i, 9);
            dataModel.setValueAt(s.getYear(), i, 10);
            dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
            i++;
        }
        String[] coloane = {"NUME", "PRENUME", "CNP", "DATA NAȘTERII", "NUMĂR DE TELEFON", "ADRESĂ", "ADRESĂ DE EMAIL", "FACULTATE", "SPECIALIZARE", "CICLU UNIVERSITAR", "AN", "NUMĂR DE CREDITE"};
        TableModel model = new DefaultTableModel(dataModel.getStudenti(), coloane) {
            public boolean isCellEditable(int row, int column) {
                //set cells uneditable
                return false;
            }
        };
        //add elements to the frame
        frame.add(scrollPane);
        frame.add(faculties);
        frame.add(departments);
        frame.add(selecteaza);
        frame.add(inapoi);
        //set white background
        frame.getContentPane().setBackground(Color.WHITE);
        tabelStudenti.setModel(model);
        scrollPane.setViewportView(tabelStudenti);
        //set table sorter
        tabelStudenti.setAutoCreateRowSorter(true);
        //set bounds for elements
        scrollPane.setBounds(42, 110, 1100, 183);
        faculties.setBounds(42, 60, 300, 25);
        departments.setBounds(350, 60, 300, 25);
        selecteaza.setBounds(440, 320, 150, 25);
        inapoi.setBounds(600, 320, 150, 25);
        //button design
        selecteaza.setBorderPainted(false);
        selecteaza.setBackground(new Color(233, 233, 233));
        selecteaza.setForeground(new Color(100, 100, 100));
        inapoi.setBorderPainted(false);
        inapoi.setBackground(new Color(233, 233, 233));
        inapoi.setForeground(new Color(100, 100, 100));
        faculties.setBackground(new Color(233, 233, 233));
        faculties.setForeground(new Color(100, 100, 100));
        departments.setBackground(new Color(233, 233, 233));
        departments.setForeground(new Color(100, 100, 100));
        //set frame icon
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("resources/1.png")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        //set frame size
        frame.setPreferredSize(new Dimension(1200, 450));
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
        //add students into table when selecting a faculty
        faculties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0, q = 0;
                departments.removeAllItems();
                for (Student s : studenti) {
                    if (faculties.getSelectedItem().toString().equals("Toate facultățile")) {
                        q = studenti.size();
                        break;
                    } else if (s.getFaculty().equals(faculties.getSelectedItem().toString())) {
                        q++;
                    }
                }
                dataModel = new StudentTableModel(q);
                for (Student s : studenti) {
                    if (faculties.getSelectedItem().toString().equals("Toate facultățile")) {
                        dataModel.setValueAt(s.getLastName(), i, 0);
                        dataModel.setValueAt(s.getFirstName(), i, 1);
                        dataModel.setValueAt(s.getCnp(), i, 2);
                        dataModel.setValueAt(s.getDob(), i, 3);
                        dataModel.setValueAt(s.getPhoneNumber(), i, 4);
                        dataModel.setValueAt(s.getAddress(), i, 5);
                        dataModel.setValueAt(s.getEmailAddress(), i, 6);
                        dataModel.setValueAt(s.getFaculty(), i, 7);
                        dataModel.setValueAt(s.getDepartment(), i, 8);
                        dataModel.setValueAt(s.getDegree(), i, 9);
                        dataModel.setValueAt(s.getYear(), i, 10);
                        dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
                        i++;
                    } else if (s.getFaculty().equals(faculties.getSelectedItem().toString())) {
                        dataModel.setValueAt(s.getLastName(), i, 0);
                        dataModel.setValueAt(s.getFirstName(), i, 1);
                        dataModel.setValueAt(s.getCnp(), i, 2);
                        dataModel.setValueAt(s.getDob(), i, 3);
                        dataModel.setValueAt(s.getPhoneNumber(), i, 4);
                        dataModel.setValueAt(s.getAddress(), i, 5);
                        dataModel.setValueAt(s.getEmailAddress(), i, 6);
                        dataModel.setValueAt(s.getFaculty(), i, 7);
                        dataModel.setValueAt(s.getDepartment(), i, 8);
                        dataModel.setValueAt(s.getDegree(), i, 9);
                        dataModel.setValueAt(s.getYear(), i, 10);
                        dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
                        i++;
                    }
                }
                String[] coloane = {"NUME", "PRENUME", "CNP", "DATA NAȘTERII", "NUMĂR DE TELEFON", "ADRESĂ", "ADRESĂ DE EMAIL", "FACULTATE", "SPECIALIZARE", "CICLU UNIVERSITAR", "AN", "NUMĂR DE CREDITE"};
                TableModel model = new DefaultTableModel(dataModel.getStudenti(), coloane) {
                    public boolean isCellEditable(int row, int column) {
                        //set cells uneditable
                        return false;
                    }
                };
                departments.addItem(new Department("Toate specializările"));
                for(Department d:specializari){
                    if(faculties.getSelectedItem().toString().equals("Toate facultățile")){
                        departments.addItem(d);
                    }else if(d.getFaculty().equals(faculties.getSelectedItem().toString())){
                        departments.addItem(d);
                    }
                }
                tabelStudenti.setModel(model);
            }
        });
        //add students into table when selecting a department
        departments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0, q = 0;
                if(departments.getItemCount() > 0){
                    for(Student s:studenti){
                        if(faculties.getSelectedItem().toString().equals("Toate facultățile") && departments.getSelectedItem().toString().equals("Toate specializările")){
                            q = studenti.size();
                            break;
                        }else if(departments.getSelectedItem().toString().equals("Toate specializările") && s.getFaculty().equals(faculties.getSelectedItem().toString())) {
                            q++;
                        }else if(s.getDepartment().equals(departments.getSelectedItem().toString())){
                            q++;
                        }
                    }
                    dataModel = new StudentTableModel(q);
                    for(Student s:studenti){
                        if(faculties.getSelectedItem().toString().equals("Toate facultățile") && departments.getSelectedItem().toString().equals("Toate specializările")){
                            dataModel.setValueAt(s.getLastName(), i, 0);
                            dataModel.setValueAt(s.getFirstName(), i, 1);
                            dataModel.setValueAt(s.getCnp(), i, 2);
                            dataModel.setValueAt(s.getDob(), i, 3);
                            dataModel.setValueAt(s.getPhoneNumber(), i, 4);
                            dataModel.setValueAt(s.getAddress(), i, 5);
                            dataModel.setValueAt(s.getEmailAddress(), i, 6);
                            dataModel.setValueAt(s.getFaculty(), i, 7);
                            dataModel.setValueAt(s.getDepartment(), i, 8);
                            dataModel.setValueAt(s.getDegree(), i, 9);
                            dataModel.setValueAt(s.getYear(), i, 10);
                            dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
                            i++;
                        }else if(departments.getSelectedItem().toString().equals("Toate specializările") && s.getFaculty().equals(faculties.getSelectedItem().toString())) {
                            dataModel.setValueAt(s.getLastName(), i, 0);
                            dataModel.setValueAt(s.getFirstName(), i, 1);
                            dataModel.setValueAt(s.getCnp(), i, 2);
                            dataModel.setValueAt(s.getDob(), i, 3);
                            dataModel.setValueAt(s.getPhoneNumber(), i, 4);
                            dataModel.setValueAt(s.getAddress(), i, 5);
                            dataModel.setValueAt(s.getEmailAddress(), i, 6);
                            dataModel.setValueAt(s.getFaculty(), i, 7);
                            dataModel.setValueAt(s.getDepartment(), i, 8);
                            dataModel.setValueAt(s.getDegree(), i, 9);
                            dataModel.setValueAt(s.getYear(), i, 10);
                            dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
                            i++;
                        }else if(s.getDepartment().equals(departments.getSelectedItem().toString())){
                            dataModel.setValueAt(s.getLastName(), i, 0);
                            dataModel.setValueAt(s.getFirstName(), i, 1);
                            dataModel.setValueAt(s.getCnp(), i, 2);
                            dataModel.setValueAt(s.getDob(), i, 3);
                            dataModel.setValueAt(s.getPhoneNumber(), i, 4);
                            dataModel.setValueAt(s.getAddress(), i, 5);
                            dataModel.setValueAt(s.getEmailAddress(), i, 6);
                            dataModel.setValueAt(s.getFaculty(), i, 7);
                            dataModel.setValueAt(s.getDepartment(), i, 8);
                            dataModel.setValueAt(s.getDegree(), i, 9);
                            dataModel.setValueAt(s.getYear(), i, 10);
                            dataModel.setValueAt(s.getNumberOfCredits(), i, 11);
                            i++;
                        }
                    }
                    String[] coloane = {"NUME", "PRENUME", "CNP", "DATA NAȘTERII", "NUMĂR DE TELEFON", "ADRESĂ", "ADRESĂ DE EMAIL", "FACULTATE", "SPECIALIZARE", "CICLU UNIVERSITAR", "AN", "NUMĂR DE CREDITE"};
                    TableModel model = new DefaultTableModel(dataModel.getStudenti(), coloane) {
                        public boolean isCellEditable(int row, int column) {
                            //set cells uneditable
                            return false;
                        }
                    };
                    tabelStudenti.setModel(model);
                }
            }
        });
        //show edit menu when picking a student
        selecteaza.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    DefaultTableModel model = (DefaultTableModel) tabelStudenti.getModel();
                    int indexRandSelectat = tabelStudenti.getSelectedRow();
                    frame.setVisible(false);
                    EditStudentGUI window = new EditStudentGUI(1,"",model.getValueAt(indexRandSelectat, 0).toString(), model.getValueAt(indexRandSelectat, 1).toString(), model.getValueAt(indexRandSelectat, 2).toString(), (Date) model.getValueAt(indexRandSelectat, 3), model.getValueAt(indexRandSelectat, 4).toString(), model.getValueAt(indexRandSelectat, 5).toString(), model.getValueAt(indexRandSelectat, 6).toString(), model.getValueAt(indexRandSelectat, 7).toString(), model.getValueAt(indexRandSelectat, 8).toString(), model.getValueAt(indexRandSelectat, 9).toString(), (int) model.getValueAt(indexRandSelectat, 10), (int) model.getValueAt(indexRandSelectat, 11));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Selectați o înregistrare din tabel!");
                }
            }
        });
        //go back to user menu
        inapoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                AdminMenuGUI window = new AdminMenuGUI();
            }
        });

    }

    public static void main(String[] args) {
        EditStudentAdminGUI window = new EditStudentAdminGUI();
    }
}
