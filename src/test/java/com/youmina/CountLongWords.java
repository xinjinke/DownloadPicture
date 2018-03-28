package com.youmina;

import org.jsoup.select.Collector;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xinhezhang on 2017/12/26.
 */
public class CountLongWords {

    public static void main(String [] args) throws IOException {

        String comments = new String(Files.readAllBytes(Paths.get("/Users/xinhezhang/Documents/myVps.txt")), StandardCharsets.UTF_8);

        //将字符串分割为一个个单词
        List<String> words = Arrays.asList(comments.split("\\PL+"));

        long count = 0;
        for(String word:words){
            if(word.length()>12){
                count++;
            }
        }
        System.out.println("count lenth = "+count);

        count = words.stream().filter(w ->w.length() > 12).count();

        System.out.println("count lenth = "+count);

        count = words.parallelStream().filter(w ->w.length() > 12).count();

        System.out.println("count lenth = "+count);

        Stream<String> song = Stream.of("gently","down","the","stream");

        System.out.println(song.toString());

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        Stream<BigDecimal> integers = Stream.iterate(BigDecimal.ONE,n ->n.add(BigDecimal.ONE));

        show("integers",integers);

    }


    public static <T> void show(String title,Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE+1).collect(Collectors.toList());

        System.out.print(title+":");
        for(int i = 0;i <firstElements.size(); i++){
            if(i > 0) System.out.print(",");
            if(i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }
}
