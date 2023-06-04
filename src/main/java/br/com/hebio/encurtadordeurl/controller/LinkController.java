package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.repository.LinkRepository;
import br.com.hebio.encurtadordeurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping()
    public void generateCode(@RequestBody Link link) {
        link.setCode(linkService.generateCode());
        linkRepository.save(link);
    }

    @GetMapping("/{id}")
    public Optional<Link> searchLink(@PathVariable("id") Long id) {
        return linkRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable("id") Long id) {
        linkRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateLink(@RequestBody Link link, @PathVariable("id") Long id) {
        link.setId(id);
        linkRepository.save(link);
    }
}
