package fr.kayp.bol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kayp.bol.entity.TemplateBl;
import fr.kayp.bol.repository.ProofOfAuthenticityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.security.MessageDigest;

@RestController
@RequestMapping("/v2")
public class TempBLController {
    final ProofOfAuthenticityRepository proofOfAuthenticityRepository;

    public TempBLController(final ProofOfAuthenticityRepository proofOfAuthenticityRepository) {
        this.proofOfAuthenticityRepository = proofOfAuthenticityRepository;
    }

    @PostMapping(value = "/createTempBL")
    public ResponseEntity<String> createTempBLControllerPost(@RequestBody TemplateBl templateBl) throws Exception {
        String jsonEbl = new ObjectMapper().writeValueAsString(templateBl);

        /* Hashing ici */
        //MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        //messageDigest.update(jsonEbl.getBytes());
        //String stringHash = new String(messageDigest.digest());

        String nftId = proofOfAuthenticityRepository.createProofOfAuthenticity(jsonEbl);

        return new ResponseEntity<>(nftId, HttpStatus.CREATED);
    }
}