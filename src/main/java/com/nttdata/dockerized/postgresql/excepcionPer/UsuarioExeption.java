package com.nttdata.dockerized.postgresql.excepcionPer;

public class UsuarioExeption extends RuntimeException{
    private int codigoDeError;

    public UsuarioExeption(String mensaje,int codigoDeError) {
        super(mensaje);
        this.codigoDeError = codigoDeError;
    }

    public int getCodigoDeError(){
        return codigoDeError;
    }
}
