package br.com.hebio.encurtadordeurl.service;

import br.com.hebio.encurtadordeurl.model.Link;
import br.com.hebio.encurtadordeurl.repository.LinkRepository;
import br.com.hebio.encurtadordeurl.service.exceptions.LinkNotFoundException;
import br.com.hebio.encurtadordeurl.service.exceptions.URLAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

     public Iterable<Link> pageableListLinks(Integer size) {
        if (size == null) {
            return linkRepository.findAll();
        } else {
            PageRequest pageable = PageRequest.of(0, size);
            return linkRepository.findAll(pageable);
        }
    }

    public Optional<Link> searchLink(Long id) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isEmpty()) {
            throw new LinkNotFoundException("Link não encontrado.");
        }
        return link;
    }

    public void searchUrl(String url) {
        if (linkRepository.existsByUrl(url)) {
            throw new URLAlreadyExistsException("A URL já existe");
        }
    }

    public Long saveLink(Link link) {
        searchUrl(link.getUrl());
        link.setId(null);
        link.setCode(generateCode());
        return linkRepository.save(link).getId();
    }

    public void deleteLink(Long id) {
        try {
            linkRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LinkNotFoundException("Link não encontrado.");
        }
    }

    public void updateLink(Link link) {
        verifyExistence(link);
        linkRepository.save(link);
    }

    private void verifyExistence(Link link) {
        searchLink(link.getId());
    }

    private String generateCode() {
        String code = new String();
        String posssible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 5; i++) {
            code += posssible.charAt((int) Math.floor(Math.random() * posssible.length()));
        }
        return code;
    }
}
