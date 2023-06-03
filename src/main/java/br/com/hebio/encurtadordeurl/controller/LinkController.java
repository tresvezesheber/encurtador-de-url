package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.repository.LinkRepository;
import br.com.hebio.encurtadordeurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkService linkService;

    @GetMapping()
    public Iterable<Link> listLinks() {
        Iterable<Link> links = linkRepository.findAll();
        return links;
    }


    @GetMapping("/gerar")
    public @ResponseBody String showCode() {
        return linkService.generateCode();
    }
}
