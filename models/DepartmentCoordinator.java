package models;


import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class DepartmentCoordinator extends User{
    private DepartmentCoordinator() {
        super();
    }

    private DepartmentCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public static DepartmentCoordinator create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        DepartmentCoordinator coordinator = new DepartmentCoordinator();
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

    public static DepartmentCoordinator serialize(String coordinator_stringified){
        if(coordinator_stringified == null || !coordinator_stringified.split(" \\| ")[9].equals("DepartmentCoordinator")) return null;
        DepartmentCoordinator coordinator = new DepartmentCoordinator(coordinator_stringified.split(" \\| "));
        return coordinator;
    }
    public static ArrayList<DepartmentCoordinator> arraySerialize(ArrayList<String> coordinator_stringifieds){
        if(coordinator_stringifieds == null) return null;
        ArrayList<DepartmentCoordinator> coordinators = new ArrayList<>();
        coordinator_stringifieds.forEach(cs -> {
            DepartmentCoordinator coordinator = DepartmentCoordinator.serialize(cs);
            coordinators.add(coordinator);
        });
        return coordinators;
    }

    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public Department getDepartment(){
        String department_stringified = ActiveRecord.find_by("departments", "department_coordinator_id", this.getId()+"");
        Department department = Department.serialize(department_stringified);
        return department;
    }
}
