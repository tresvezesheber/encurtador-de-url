package br.com.hebio.encurtadordeurl.service;

import org.springframework.stereotype.Service;

@Service
public class LinkService {

    public String generateCode() {
        String code = new String();
        String posssible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 5; i++) {
            code += posssible.charAt((int) Math.floor(Math.random() * posssible.length()));
        }
        return code;
    }
}
