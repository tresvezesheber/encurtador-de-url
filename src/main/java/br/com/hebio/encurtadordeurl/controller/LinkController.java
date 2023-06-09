package br.com.hebio.encurtadordeurl.controller;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping()
    public ResponseEntity<Iterable<Link>> pageableListLinks(@RequestParam(required = false) Integer size) {
        return ResponseEntity.ok().body(linkService.pageableListLinks(size));
    }

    @PostMapping()
    public ResponseEntity<Void> saveLink(@RequestBody Link link) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(linkService.saveLink(link)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchLink(@PathVariable("id") Long id) {
        Optional<Link> link = linkService.searchLink(id);
        return ResponseEntity.ok().body(link);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable("id") Long id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLink(@RequestBody Link link, @PathVariable("id") Long id) {
        link.setId(id);
        linkService.updateLink(link);
        return ResponseEntity.noContent().build();
    }
}