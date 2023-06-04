package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.repository.LinkRepository;
import br.com.hebio.encurtadordeurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkService linkService;

    @GetMapping()
    public ResponseEntity<Iterable<Link>> listLinks() {
        Iterable<Link> links = linkRepository.findAll();
        return ResponseEntity.ok().body(links);
    }

    @PostMapping()
    public ResponseEntity<Void> generateCode(@RequestBody Link link) {
        link.setCode(linkService.generateCode());
        link = linkRepository.save(link);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(link.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchLink(@PathVariable("id") Long id) {
        Optional<Link> link = linkRepository.findById(id);
        if (!link.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(link);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable("id") Long id) {
        try {
            linkRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLink(@RequestBody Link link, @PathVariable("id") Long id) {
        Optional<Link> linkAux = linkRepository.findById(id);
        linkAux.get().setUrl(link.getUrl());
        linkRepository.save(linkAux.get());
        return ResponseEntity.noContent().build();
    }
}