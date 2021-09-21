package models;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class Student extends User{
    private Course course;
    private Student() {
        super();
    }

    private Student(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
        int course_id = Integer.parseInt(ActiveRecord.find_by("studentscourses", "student_id", parameters[0]).split(" \\| ")[1]);
        this.course = Course.serialize(ActiveRecord.find("courses", course_id));
    }

    public static Student create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality, int course_id){
        Student student = new Student();
        AvaliacaoOO informations = new AvaliacaoOO(name, registration);
        student.setCpf(cpf);
        student.setEmail(email);
        student.setPassword(password);
        student.setBirthdate(birthdate);
        student.setState(state);
        student.setNationality(nationality);
        student.setCourse(course_id);
        student.setInformations(informations);
        return student;
    }

    public static Student serialize(String student_stringified){
        if(student_stringified == null || !student_stringified.split(" \\| ")[9].equals("Student")) return null;
        Student student = new Student(student_stringified.split(" \\| "));
        return student;
    }
    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public Course getCourse() {
        return course;
    }
    public boolean validateCourse(Course course){
        boolean response = true;
        if(course == null){
            this.appendError("O departamento deve existir");
            response = false;
        }
        return response;
    }
    public void setCourse(int course_id){
        String course_stringified = ActiveRecord.find("courses", course_id);
        Course course = Course.serialize(course_stringified);
        if(validateCourse(course)) this.course = course;
    }
}
