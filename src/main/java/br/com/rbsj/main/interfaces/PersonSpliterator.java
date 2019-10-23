package br.com.rbsj.main.interfaces;

import br.com.rbsj.model.Pessoa;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Pessoa> {

    private Spliterator<String> lineSpliterator;
    private String nome;
    private int idade;
    private String cidade;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }


    public boolean tryAdvance(Consumer<? super Pessoa> action) {

        if(this.lineSpliterator.tryAdvance(linha-> this.nome = linha) &&
           this.lineSpliterator.tryAdvance(linha-> this.idade = Integer.parseInt(linha)) &&
            this.lineSpliterator.tryAdvance(linha -> this.cidade = linha)){

            Pessoa ps = new Pessoa(this.nome, this.idade, this.cidade);
            action.accept(ps);

            return true;
        }

        return false;
    }

    public Spliterator<Pessoa> trySplit() {
        return null;
    }

    public long estimateSize() {
        return lineSpliterator.estimateSize() / 3;
    }

    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
