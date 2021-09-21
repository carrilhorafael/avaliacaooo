package models;

import models.exceptions.AvaliacaoOONaoInformadaException;

public class AvaliacaoOO {
    private String name, registration;
    public AvaliacaoOO(String name, String registration){
        this.setName(name);
        this.setRegistration(registration);
    } 

    public String getName() {
        return name;
    }
    public String getRegistration() {
        return registration;
    }
    public boolean validateName(String value) throws AvaliacaoOONaoInformadaException{
        boolean response = true;
        if (value.isBlank()){
            throw new AvaliacaoOONaoInformadaException();
        }
        return response;
    }
    public boolean validateRegistration(String value) throws AvaliacaoOONaoInformadaException{
        boolean response = true;
        if (value.isBlank()){
            throw new AvaliacaoOONaoInformadaException();
        }else {
            if(!value.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}")){
                throw new AvaliacaoOONaoInformadaException();
            }
        }
        return response;
    }
    public void setName(String name){
        try{
            validateName(name); 
            this.name = name;
        }catch(AvaliacaoOONaoInformadaException e){
            System.out.println(e.getMessage());
        }
    }

    public void setRegistration(String registration){
        try{
            validateName(registration); 
            this.registration = registration;
        }catch(AvaliacaoOONaoInformadaException e){
            System.out.println(e.getMessage());
        }
    }

}