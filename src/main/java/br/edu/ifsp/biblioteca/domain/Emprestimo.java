package br.edu.ifsp.biblioteca.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    public static final int PRAZO_EM_DIAS = 14;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exemplar_id")
    private Exemplar exemplar;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "data_prevista_devoluacao")
    private LocalDate dataPrevistaDevoluacao;

    protected Emprestimo(){}

    public Emprestimo(Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo){
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevoluacao = dataEmprestimo.plusDays(PRAZO_EM_DIAS);
    }

    public boolean isAtivo(){
        return this.dataDevolucao == null;
    }

    public boolean isAtrasado(LocalDate hoje){
        return this.isAtivo() && this.dataPrevistaDevoluacao.isBefore(hoje);
    }

    public long diasDeAtraso(LocalDate hoje){

        if(!this.isAtrasado(hoje)){
            return 0;
        }

        return ChronoUnit.DAYS.between(this.dataPrevistaDevoluacao, hoje);
    }

    public void registrarDevolucao(LocalDate quando){

        if (!this.isAtivo()){
            throw new IllegalStateException("Este empréstimo já foi devolvido!");
        }

        this.dataDevolucao = quando;
        this.exemplar.devolver();
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevistaDevoluacao() {
        return dataPrevistaDevoluacao;
    }

    @Override
    public String toString(){

        return "Empréstimo #" + this.id
                + " | previsão para " + this.dataPrevistaDevoluacao
                + (this.isAtivo() ? " | EM ABERTO: " : " | devolvido em: " )
                + this.dataDevolucao;
    }
}
