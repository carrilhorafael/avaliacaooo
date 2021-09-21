package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class Teacher extends User {
    private Department department;
    private Teacher() {
        super();
    }

    private Teacher(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
        int department_id = Integer.parseInt(ActiveRecord.find_by("teachersdepartments", "teacher_id", parameters[0]).split(" \\| ")[1]);
        this.department = Department.serialize(ActiveRecord.find("departments", department_id));
    }

    public static Teacher create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality, int department_id){
        Teacher teacher = new Teacher();
        AvaliacaoOO informations = new AvaliacaoOO(name, registration);
        teacher.setCpf(cpf);
        teacher.setEmail(email);
        teacher.setPassword(password);
        teacher.setBirthdate(birthdate);
        teacher.setState(state);
        teacher.setNationality(nationality);
        teacher.setDepartment(department_id);
        teacher.setInformations(informations);
        return teacher;
    }

    public static Teacher serialize(String teacher_stringified){
        if(teacher_stringified == null || !teacher_stringified.split(" \\| ")[9].equals("Teacher")) return null;
        Teacher teacher = new Teacher(teacher_stringified.split(" \\| "));
        return teacher;
    }

    public static ArrayList<Teacher> arraySerialize(ArrayList<String> teacher_stringifieds){
        ArrayList<Teacher> response = new ArrayList<>();
        teacher_stringifieds.forEach(ts -> {
            Teacher teacher = Teacher.serialize(ts);
            response.add(teacher);
        });
        return response;
    }

    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public Department getDepartment() {
        return department;
    }
    public ArrayList<Classroom> getClassrooms(){
        ArrayList<String> classroom_stringifieds = ActiveRecord.where("classrooms", "teacher", Integer.toString(this.getId()));
        ArrayList<Classroom> classroom = Classroom.arraySerialize(classroom_stringifieds);
        return classroom;
    }
    public boolean validateDepartment(Department department){
        boolean response = true;
        if(department == null){
            this.appendError("O departamento deve existir");
            response = false;
        }
        return response;
    }
    public void setDepartment(int department_id){
        String department_stringified = ActiveRecord.find("departments", department_id);
        Department department = Department.serialize(department_stringified);
        if(validateDepartment(department)) this.department = department;
    }
}
