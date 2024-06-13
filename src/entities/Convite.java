package entities;

public class Convite {
    private int id;
    private Compromisso compromisso;
    private Usuario usuarioConvidado;
    private String status;

    public Convite() {}

    public Convite(Compromisso compromisso, Usuario usuarioConvidado, String status) {
        this.compromisso = compromisso;
        this.usuarioConvidado = usuarioConvidado;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public Usuario getUsuarioConvidado() {
        return usuarioConvidado;
    }

    public void setUsuarioConvidado(Usuario usuarioConvidado) {
        this.usuarioConvidado = usuarioConvidado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status!= null &&!status.equals("aceito") &&!status.equals("recusado") &&!status.equals("pendente")) {
            throw new IllegalArgumentException("Status inválido. Deve ser 'aceito', 'recusado' ou 'pendente'.");
        }
        this.status = status;
    }
}