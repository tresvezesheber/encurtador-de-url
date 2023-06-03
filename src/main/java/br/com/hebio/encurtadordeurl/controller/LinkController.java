package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping()
    public @ResponseBody String showCode() {
        return linkService.generateCode();
    }
}
