package models.exceptions;

public class AvaliacaoOONaoInformadaException extends Exception{
    @Override
    public String getMessage(){
        return "o parâmetro não foi informado";
    }
}
