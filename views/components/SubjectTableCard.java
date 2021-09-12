package views.components;

import views.MyDepartmentView;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.SubjectsController;
import models.Subject;

public class SubjectTableCard extends JPanel{
    public SubjectTableCard(Subject subject, MyDepartmentView container){
        super(new FlowLayout(FlowLayout.CENTER));
        JLabel subjectLabel = new JLabel(subject.getName()+ " | Carga horária: " + subject.getCh() + " horas | Numero de turmas cadastradas: " + subject.getClassrooms().size());
        subjectLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteSubjectBtn = new JButton("Deletar matéria");
        deleteSubjectBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteSubjectBtn.setBackground(new Color(32, 178, 170));
        deleteSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Confirmar", "Cancelar" };
                int response = JOptionPane.showOptionDialog(null, "Clique confirmar para continuar", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(response == 0){
                    SubjectsController.destroy(subject.getId());
                }
                // container.reload();
            }
        });
        JButton createClassroomBtn = new JButton("Criar turma");
        createClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        createClassroomBtn.setBackground(new Color(32, 178, 170));
        createClassroomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new CreateClassroomView();
                container.dispose();
            }
        });
        this.add(subjectLabel);
        this.add(deleteSubjectBtn);
        this.add(createClassroomBtn);
    }
}
