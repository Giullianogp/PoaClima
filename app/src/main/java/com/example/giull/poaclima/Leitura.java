package com.example.giull.poaclima;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by giull on 23/06/2017.
 */

public class Leitura implements Serializable {

    private int id;
    private String estacao;
    private String endereco;
    private String bairro;
    private double latitude;
    private double longitude;
    private Date data;
    private double temperaturaInterna;
    private double umidadeInterna;
    private double temperaturaExterna;
    private double umidadeExterna;
    private double chuvaDiaria;
    private double pressao;
    private double velocidadeVento;
    private double direcaoVento;
    private double velocidadeVentoRajada;
    private double direcaoVentoRajada;
    private double quadranteVento;
    private double sensacaoTermica;
    private double pontoOrvalho;
    private double alturaNuvens;
    private int idRessonare;


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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTemperaturaInterna() {
        return temperaturaInterna;
    }

    public void setTemperaturaInterna(double temperaturaInterna) {
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

    public void setTemperaturaExterna(double temperaturaExterna) {
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

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(double direcaoVento) {
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

    public double getQuadranteVento() {
        return quadranteVento;
    }

    public void setQuadranteVento(double quadranteVento) {
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

    public void setAlturaNuvens(double alturaNuvens) {
        this.alturaNuvens = alturaNuvens;
    }

    public int getIdRessonare() {
        return idRessonare;
    }

    public void setIdRessonare(int idRessonare) {
        this.idRessonare = idRessonare;
    }
}
