package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.repository.LinkRepository;
import br.com.hebio.encurtadordeurl.service.LinkService;
import br.com.hebio.encurtadordeurl.service.exceptions.LinkNotFoundException;
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
        return ResponseEntity.ok().body(linkService.listLinks());
    }

    @PostMapping()
    public ResponseEntity<Void> saveLink(@RequestBody Link link) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(linkService.saveLink(link)).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchLink(@PathVariable("id") Long id) {
        Optional<Link> link = null;

        try {
            link = linkService.searchLink(id);
        } catch (LinkNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(link);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable("id") Long id) {
        try {
            linkService.deleteLink(id);
        } catch (LinkNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLink(@RequestBody Link link, @PathVariable("id") Long id) {
        link.setId(id);

        try {
            linkService.updateLink(link);
        } catch (LinkNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}