package br.edu.ifsp.biblioteca;


import br.edu.ifsp.biblioteca.domain.Livro;

public class BibliotecaApplication {

    public static void main(String[] args) {

        Livro livro = new Livro(
                1L,
                "9788508145607",
                "Dom Casmurro",
                2026
        );

        Livro livro2 = new Livro(
                2L,
                "98989",
                "Dom Quixote",
                2025
        );

        Livro livro3 = new Livro(
                3L,
                "9898555559",
                "Crime e Castigo",
                1913
        );

        System.out.println(livro);
        System.out.println(livro2);
        System.out.println(livro3);
    }
}
