package models;


import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class CourseCoordinator extends User {
    private CourseCoordinator() {
        super();
    }

    private CourseCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public static CourseCoordinator create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        CourseCoordinator coordinator = new CourseCoordinator();
        AvaliacaoOO informations = new AvaliacaoOO(name, registration);
        coordinator.setCpf(cpf);
        coordinator.setEmail(email);
        coordinator.setPassword(password);
        coordinator.setBirthdate(birthdate);
        coordinator.setState(state);
        coordinator.setNationality(nationality);
        coordinator.setInformations(informations);
        return coordinator;
    }

    public static CourseCoordinator serialize(String coordinator_stringified){
        if(coordinator_stringified == null || !coordinator_stringified.split(" \\| ")[9].equals("CourseCoordinator")) return null;
        CourseCoordinator coordinator = new CourseCoordinator(coordinator_stringified.split(" \\| "));
        return coordinator;
    }

    public static ArrayList<CourseCoordinator> arraySerialize(ArrayList<String> coordinator_stringifieds){
        if(coordinator_stringifieds == null) return null;
        ArrayList<CourseCoordinator> coordinators = new ArrayList<>();
        coordinator_stringifieds.forEach(cs -> {
            CourseCoordinator coordinator = CourseCoordinator.serialize(cs);
            coordinators.add(coordinator);
        });
        return coordinators;
    }

    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public Course getCourse(){
        String course_stringified = ActiveRecord.find_by("courses", "course_coordinator_id", Integer.toString(this.getId()));
        Course response = Course.serialize(course_stringified);
        return response;
    }
}
