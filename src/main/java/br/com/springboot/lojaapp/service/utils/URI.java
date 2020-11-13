package br.com.springboot.lojaapp.service.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URI {


    public static List<Integer> CategoriasLista(String s) {

        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static String decodeString(String nomeProduto) {
        try {
            return URLDecoder.decode(nomeProduto, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
