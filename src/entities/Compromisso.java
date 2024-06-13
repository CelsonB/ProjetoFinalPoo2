package entities;

import java.util.Date;

public class Compromisso {
    private int id;
    private String titulo;
    private String descricao;
    private Date dataHoraInicio;
    private Date dataHoraTermino;
    private String local;
    private Agenda agenda;
    private Date dataHoraNotificacao;


    public Compromisso() {}

    public Compromisso(String titulo, String descricao, Date dataHoraInicio, Date dataHoraTermino, String local, Agenda agenda, Date dataHoraNotificacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraTermino = dataHoraTermino;
        this.local = local;
        this.agenda = agenda;
        this.dataHoraNotificacao = dataHoraNotificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(Date dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Date getDataHoraNotificacao() {
        return dataHoraNotificacao;
    }

    public void setDataHoraNotificacao(Date dataHoraNotificacao) {
        this.dataHoraNotificacao = dataHoraNotificacao;
    }
}