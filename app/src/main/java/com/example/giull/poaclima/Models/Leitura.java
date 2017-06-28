package com.example.giull.poaclima.Models;

import java.io.Serializable;

/**
 * Created by giull on 23/06/2017.
 */

public class Leitura implements Serializable {

    private int id;
    private String estacao;
    private String endereco;
    private String bairro;
    private Double latitude;
    private Double longitude;
    private String data;
    private Double temperaturaInterna;
    private Double umidadeInterna;
    private Double temperaturaExterna;
    private Double umidadeExterna;
    private Double chuvaDiaria;
    private Double pressao;
    private Double velocidadeVento;
    private Double direcaoVento;
    private Double velocidadeVentoRajada;
    private Double direcaoVentoRajada;
    private String quadranteVento;
    private Double sensacaoTermica;
    private Double pontoOrvalho;
    private Double alturaNuvens;
    private Integer idRessonare;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTemperaturaInterna() {
        return temperaturaInterna;
    }

    public void setTemperaturaInterna(Double temperaturaInterna) {
        this.temperaturaInterna = temperaturaInterna;
    }

    public double getUmidadeInterna() {
        return umidadeInterna;
    }

    public void setUmidadeInterna(double umidadeInterna) {
        this.umidadeInterna = umidadeInterna;
    }

    public double getTemperaturaExterna() {
        return temperaturaExterna;
    }

    public void setTemperaturaExterna(Double temperaturaExterna) {
        this.temperaturaExterna = temperaturaExterna;
    }

    public double getUmidadeExterna() {
        return umidadeExterna;
    }

    public void setUmidadeExterna(double umidadeExterna) {
        this.umidadeExterna = umidadeExterna;
    }

    public double getChuvaDiaria() {
        return chuvaDiaria;
    }

    public void setChuvaDiaria(double chuvaDiaria) {
        this.chuvaDiaria = chuvaDiaria;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(Double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(Double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public double getVelocidadeVentoRajada() {
        return velocidadeVentoRajada;
    }

    public void setVelocidadeVentoRajada(double velocidadeVentoRajada) {
        this.velocidadeVentoRajada = velocidadeVentoRajada;
    }

    public double getDirecaoVentoRajada() {
        return direcaoVentoRajada;
    }

    public void setDirecaoVentoRajada(double direcaoVentoRajada) {
        this.direcaoVentoRajada = direcaoVentoRajada;
    }

    public String getQuadranteVento() {
        return quadranteVento;
    }

    public void setQuadranteVento(String quadranteVento) {
        this.quadranteVento = quadranteVento;
    }

    public double getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public double getPontoOrvalho() {
        return pontoOrvalho;
    }

    public void setPontoOrvalho(double pontoOrvalho) {
        this.pontoOrvalho = pontoOrvalho;
    }

    public double getAlturaNuvens() {
        return alturaNuvens;
    }

    public void setAlturaNuvens(Double alturaNuvens) {
        this.alturaNuvens = alturaNuvens;
    }

    public int getIdRessonare() {
        return idRessonare;
    }

    public void setIdRessonare(int idRessonare) {
        this.idRessonare = idRessonare;
    }
}
