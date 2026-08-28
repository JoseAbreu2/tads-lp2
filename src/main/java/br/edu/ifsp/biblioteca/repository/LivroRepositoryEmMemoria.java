package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoryEmMemoria implements ILivroRepository{


    private final Map<Long, Livro> livros = new HashMap<>();
    private Long sequenciaId = 0L;

    @Override
    public Livro salvar(Livro livro) {

        if (livro.getId() == null){
            this.sequenciaId = this.sequenciaId + 1;
            livro.setId(this.sequenciaId);
        }

        this.livros.put(livro.getId(), livro);

        return livro;
    }

    @Override
    public List<Livro> listarTodos() {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        return colecaoLivros;
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {

        return Optional.ofNullable(this.livros.get(id));

        // Usando o operador ternário
        //Livro l = this.livros.get(id);
        //return l == null ? Optional.empty() : Optional.of(l);

        // usando if's
        //if (l == null){
        //    return Optional.empty();
       // }

       // return Optional.of(l);
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for (int i = 0; i < colecaoLivros.size(); i++){

            Livro livro = colecaoLivros.get(i);

            if (livro.getIsbn().equalsIgnoreCase(isbn)){
                return Optional.of(livro);
            }
        }

//        for each
//        for (Livro livro : colecaoLivros) {
//
//            if (livro.getIsbn().equals(isbn)) {
//                return Optional.of(livro);
//            }
//        }

        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());
        List<Livro> livrosSelecionados = new ArrayList<>();

        for (int i = 0; i < colecaoLivros.size(); i++){

            Livro l = colecaoLivros.get(i);
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
               livrosSelecionados.add(l);
            }

        }

        return livrosSelecionados;
    }
}
