package models.abstracts;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.AvaliacaoOO;


public abstract class User {
    private AvaliacaoOO informations;
    private String cpf, email, password, state, birthdate, nationality;
    private int id;
    private ArrayList<String> errors = new ArrayList<>();
    private static int next_user_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[1]);

    public User(){}

    public User(String id, String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        AvaliacaoOO informations = new AvaliacaoOO(name, registration);
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.id = Integer.parseInt(id);
        this.informations = informations;
    }

    public String stringify(){
        return this.id + " | " + this.informations.getName() + " | " + this.cpf + " | " + this.email + " | " + this.password + " | " + this.informations.getRegistration() + " | " + this.birthdate + " | " + this.state + " | " + this.nationality + " | " + this.getClass().toString().split("\\.")[1];
    }

    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_user_id;
            next_user_id++;
            return ActiveRecord.save("users", this.stringify());
        }return false;
    }


    public boolean authenticate (String password){
        return this.password.equals(password);
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return informations.getName();
    }
    public String getPassword() {
        return password;
    }
    public String getRegistration() {
        return informations.getRegistration();
    }
    public String getBirthdate() {
        return birthdate;
    }
    public String getNationality() {
        return nationality;
    }
    public String getState() {
        return state;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }

    // validators e setters
    
    public boolean validateCpf(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("O cpf não pode ficar em branco");
            response = false;
        }else {
            if(!value.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}")){
                this.errors.add("Formato errado de cpf. (Utilize XXX.XXX.XXX-XX)");
                response = false;
            }
        }
        return response;
    }
    public boolean validateEmail(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("O email não pode ficar em branco");
            response = false;
        }else {
            if(!value.matches("[a-z]{2,}@id.uff.br")){
                this.errors.add("Utilize o email do iduff.");
                response = false;
            }
        }
        return response;
    }
    public boolean validateBirthdate(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("A data de nascimento não pode ficar em branco");
            response = false;
        }else {
            if(!value.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
                this.errors.add("Formato errado de data. (Utilize DD/MM/AAAA)");
                response = false;
            }
        }
        return response;
    }
    public boolean validatePassword(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("A senha não pode ficar em branco");
            response = false;
        }else {
            if(value.length() < 8){
                this.errors.add("A senha deve ter 8 caracteres");
                response = false;
            }
        }
        return response;
    }
    
    public boolean validateState(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("O estado do usuário não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateNationality(String value){
        boolean response = true;
        if (value.isBlank()){
            this.errors.add("A nacionalidade não pode ficar em branco");
            response = false;
        }
        return response;
    }

    public void setInformations(AvaliacaoOO informations) {
        this.informations = informations;
    }    
    public void setCpf(String cpf){
        if(validateCpf(cpf)) this.cpf = cpf;
    }
    public void setEmail(String email){
        if(validateEmail(email)) this.email = email;
    }
    public void setPassword(String password){
        if(validatePassword(password)) this.password = password;
    }
    public void setBirthdate(String birthdate){
        if(validateBirthdate(birthdate)) this.birthdate = birthdate;
    }
    public void setState(String state){
        if(validateState(state)) this.state = state;
    }
    public void setNationality(String nationality){
        if(validateNationality(nationality)) this.nationality = nationality;
    }
    public void appendError(String error) {
        this.errors.add(error);
    }

}
