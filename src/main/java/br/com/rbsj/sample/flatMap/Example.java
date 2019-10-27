package br.com.rbsj.sample.flatMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Example {

    public static void main(String[] args) throws IOException {

//        Stream<String> lines = Files.lines(Paths.get("src/principal.txt"));
//        System.out.println("Total de linhas arquivo principal: " + lines.count());
//
        Stream<String> lines1 = Files.lines(Paths.get("src/split1.txt"));
        Stream<String> lines2 = Files.lines(Paths.get("src/split2.txt"));
        Stream<String> lines3 = Files.lines(Paths.get("src/split3.txt"));
        Stream<String> lines4 = Files.lines(Paths.get("src/split4.txt"));
//
//        System.out.println("Stream 1: " + lines1.count());
//        System.out.println("Stream 2: " + lines2.count());
//        System.out.println("Stream 3: " + lines3.count());
//        System.out.println("Stream 4: " + lines4.count());

        Stream<Stream<String>> streamOfStream = Stream.of(lines1, lines2, lines3, lines4);

//        System.out.println("Count Streams: " + streamOfStream.count());

//        Stream<String> streamOfLines = streamOfStream.flatMap(strm -> strm);
//
//        System.out.println("Count lines: "+streamOfLines.count());

        Stream<String> streamOfLines = streamOfStream.flatMap(Function.identity());
//        System.out.println("Count lines: "+streamOfLines.count());

        Function<String, Stream<String>> lineSplitter = line -> Pattern.compile(" ").splitAsStream(line);

        Stream<String> streamOfWords = streamOfLines
                .flatMap(lineSplitter)
                .map(word -> word.toLowerCase())
                .filter(word -> word.length() == 5)
                .distinct();

        System.out.println(streamOfWords.count());
    }
}
