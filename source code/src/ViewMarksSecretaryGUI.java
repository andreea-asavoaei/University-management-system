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
import java.util.HashSet;

public class ViewMarksSecretaryGUI {
    private JFrame frame;
    private JComboBox<Department> department;
    private JComboBox<Subject> subject;
    private HashSet<Department> specializari;
    private HashSet<Subject> materii;
    private HashSet<MarkByDepartment> note;
    private String facultate;
    private JButton inapoi;
    private MarkTableModel dataModel = new MarkTableModel(7);
    private JTable tabelNote = new JTable();
    private JScrollPane scrollPane = new JScrollPane(tabelNote);
    public ViewMarksSecretaryGUI(String email){
        /*
        ====================
        initialize variables
        ====================
        */
        frame = new JFrame("Vizualizare note");
        department = new JComboBox<>();
        subject = new JComboBox<>();
        inapoi = new JButton("Înapoi");

        ManagerGUI mng = new ManagerGUI();
        facultate = mng.getFacultateDupaEmail(email);
        specializari = mng.getInstance().getSetSpecializari();
        materii = mng.getInstance().getSetMaterii();
        note = mng.getInstance().getSetNoteDupaSpecializare();
        int i = 0;

        department.addItem(new Department("Toate specializările"));

        for(Department d:specializari){
            if(d.getFaculty().equals(facultate)){
                department.addItem(d);
            }
        }
        for(MarkByDepartment m:note){
            if(m.getFaculty().equals(facultate)) {
                dataModel.setValueAt(m.getStudentLastName(), i, 0);
                dataModel.setValueAt(m.getStudentFirstName(), i, 1);
                dataModel.setValueAt(m.getMark(), i, 2);
                dataModel.setValueAt(m.getSubject(), i, 3);
                dataModel.setValueAt(m.getDepartment(), i, 4);
                dataModel.setValueAt(m.getProfessor(), i, 5);
                dataModel.setValueAt(m.getDateAdded(), i, 6);
                i++;
            }
        }
        String[] coloane = {"NUME STUDENT", "PRENUME STUDENT", "NOTĂ","MATERIE", "SPECIALIZARE", "PROFESOR", "DATA ULTIMEI MODIFICĂRI"};
        TableModel model = new DefaultTableModel(dataModel.getNote(), coloane) {
            public boolean isCellEditable(int row, int column){
                //set cells uneditable
                return false;
            }
        };
        //add elements to the frame
        frame.add(scrollPane);
        frame.add(department);
        frame.add(inapoi);
        //set white background
        frame.getContentPane().setBackground(Color.WHITE);
        tabelNote.setModel(model);
        scrollPane.setViewportView(tabelNote);
        //set bounds for elements
        scrollPane.setBounds(42,110,1100,183);
        department.setBounds(42,60,300,25);
        inapoi.setBounds(510,320,145,25);
        //button design
        inapoi.setBorderPainted(false);
        inapoi.setBackground(new Color(233,233,233));
        inapoi.setForeground(new Color(100,100,100));
        //set frame icon
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("resources/1.png")));
        }catch(IOException ie){
            ie.printStackTrace();
        }
        frame.setLayout(null);
        //set frame size
        frame.setPreferredSize(new Dimension(1200,450));
        frame.pack();
        //set window in the middle of the screen
        frame.setLocationRelativeTo(null);
        //set the default close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //block resize operation
        frame.setResizable(false);
        //set frame visible
        frame.setVisible(true);
        /*
        ==============
        define actions
        ==============
        */
        //add marks into table when selecting a department
        //display the subject combobox
        department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                dataModel.removeTable();
                subject.removeAllItems();
                for(MarkByDepartment m:note){
                    if(department.getSelectedItem().toString().equals("Toate specializările")){
                        dataModel.setValueAt(m.getStudentLastName(),i,0);
                        dataModel.setValueAt(m.getStudentFirstName(),i,1);
                        dataModel.setValueAt(m.getMark(),i,2);
                        dataModel.setValueAt(m.getSubject(),i,3);
                        dataModel.setValueAt(m.getDepartment(),i,4);
                        dataModel.setValueAt(m.getProfessor(),i,5);
                        dataModel.setValueAt(m.getDateAdded(),i,6);
                        i++;
                    }else if(m.getDepartment().equals(department.getSelectedItem().toString())){
                        dataModel.setValueAt(m.getStudentLastName(),i,0);
                        dataModel.setValueAt(m.getStudentFirstName(),i,1);
                        dataModel.setValueAt(m.getMark(),i,2);
                        dataModel.setValueAt(m.getSubject(),i,3);
                        dataModel.setValueAt(m.getDepartment(),i,4);
                        dataModel.setValueAt(m.getProfessor(),i,5);
                        dataModel.setValueAt(m.getDateAdded(),i,6);
                        i++;
                    }
                }
                String[] coloane = {"NUME STUDENT", "PRENUME STUDENT", "NOTĂ","MATERIE", "SPECIALIZARE", "PROFESOR", "DATA ULTIMEI MODIFICĂRI"};
                TableModel model = new DefaultTableModel(dataModel.getNote(), coloane) {
                    public boolean isCellEditable(int row, int column){
                        //set cells uneditable
                        return false;
                    }
                };
                subject.addItem(new Subject("Toate materiile"));
                for(Subject s:materii){
                    if(s.getDepartment().equals(department.getSelectedItem().toString())){
                        subject.addItem(s);
                    }
                }
                tabelNote.setModel(model);
                frame.add(subject);
                subject.setBounds(360,60,300,25);

            }
        });
        //add marks into table when selecting a subject
        subject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                dataModel.removeTable();
                if(subject.getItemCount() > 0) {
                    for (MarkByDepartment m:note) {
                        if (subject.getSelectedItem().toString().equals("Toate materiile")) {
                            dataModel.setValueAt(m.getStudentLastName(), i, 0);
                            dataModel.setValueAt(m.getStudentFirstName(), i, 1);
                            dataModel.setValueAt(m.getMark(), i, 2);
                            dataModel.setValueAt(m.getSubject(), i, 3);
                            dataModel.setValueAt(m.getDepartment(), i, 4);
                            dataModel.setValueAt(m.getProfessor(), i, 5);
                            dataModel.setValueAt(m.getDateAdded(), i, 6);
                            i++;
                        } else if (m.getSubject().equals(subject.getSelectedItem().toString())) {
                            dataModel.setValueAt(m.getStudentLastName(), i, 0);
                            dataModel.setValueAt(m.getStudentFirstName(), i, 1);
                            dataModel.setValueAt(m.getMark(), i, 2);
                            dataModel.setValueAt(m.getSubject(), i, 3);
                            dataModel.setValueAt(m.getDepartment(), i, 4);
                            dataModel.setValueAt(m.getProfessor(), i, 5);
                            dataModel.setValueAt(m.getDateAdded(), i, 6);
                            i++;
                        }
                    }
                    String[] coloane = {"NUME STUDENT", "PRENUME STUDENT", "NOTĂ", "MATERIE", "SPECIALIZARE", "PROFESOR", "DATA ULTIMEI MODIFICĂRI"};
                    TableModel model = new DefaultTableModel(dataModel.getNote(), coloane) {
                        public boolean isCellEditable(int row, int column) {
                            //set cells uneditable
                            return false;
                        }
                    };
                    tabelNote.setModel(model);
                }
            }
        });
        //go back to user menu
        inapoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                SecretaryMenuGUI window = new SecretaryMenuGUI(email);
            }
        });
    }
}
