package br.com.rbsj.main;

import br.com.rbsj.main.interfaces.PersonSpliterator;
import br.com.rbsj.model.Pessoa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreateSpliterator {

    public static void main(String[] args) {
        Path path = Paths.get("src/people.txt");

        try(Stream<String> linhas = Files.lines(path)) {

            Spliterator<String> lineSpliterator = linhas.spliterator();
            Spliterator<Pessoa> peopleSpliterator = new PersonSpliterator(lineSpliterator);

            Stream<Pessoa> pessoa = StreamSupport.stream(peopleSpliterator, false);
            pessoa.forEach(System.out::println);
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
