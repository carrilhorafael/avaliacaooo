package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.SchoolYearsController;
import models.SchoolYear;

public class SchoolYearTableCard extends JPanel{
    public SchoolYearTableCard(SchoolYear schoolYear){
        super(new FlowLayout(FlowLayout.TRAILING));
        JLabel syLabel = new JLabel(schoolYear.getYear()+"."+schoolYear.getSemester() + " (" + schoolYear.getStatus() + ")");
        syLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteSy = new JButton("Deletar " + schoolYear.getYear() + "." + schoolYear.getSemester());
        deleteSy.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteSy.setBackground(new Color(32, 178, 170));
        deleteSy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchoolYearsController.destroy(schoolYear.getId());
            }
        });
        this.add(syLabel);
        this.add(deleteSy);
    }
}