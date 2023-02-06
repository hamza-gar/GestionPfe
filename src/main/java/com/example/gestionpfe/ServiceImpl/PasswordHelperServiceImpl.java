package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.PasswordHelperDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.PasswordHelper;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.PasswordHelperRepository;
import com.example.gestionpfe.Security.SecurityConstants;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.PasswordHelperService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PasswordHelperServiceImpl implements PasswordHelperService {
    Logger logger = org.slf4j.LoggerFactory.getLogger(PasswordHelperServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    PasswordHelperRepository passwordHelperRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PasswordHelperDto createPasswordHelper(PasswordHelperDto passwordHelperDto) {
        Etudiant etudiant = etudiantRepository.findByEmail(passwordHelperDto.getEmail());
        Enseignant enseignant = enseignantRepository.findByEmail(passwordHelperDto.getEmail());
        if (etudiant == null && enseignant == null) {
            logger.error("User not found");
            throw new RuntimeException("User not found");
        }
        PasswordHelper passwordHelper = passwordHelperRepository.findByEmail(passwordHelperDto.getEmail());
        if (passwordHelper != null) {
            if (new Date().getTime() - passwordHelper.getTimeCreated().getTime() < 300000) {
                logger.error("You can't generate a new key before 5 minutes");
                throw new RuntimeException("You can't generate a new key before 5 minutes");
            }
            logger.info("PasswordHelper already exists.. Generating new key");

        } else {
            passwordHelper = new PasswordHelper();
            passwordHelper.setIdPasswordHelper(utils.generateUserId(32));
            passwordHelper.setEmail(passwordHelperDto.getEmail());
            passwordHelper.setIsEtudiant(etudiant != null);

        }

        passwordHelper.setKeyCode(utils.generateKey(6));
        passwordHelper.setTimeCreated(new Date());

        emailSender.ForgottenPassword(passwordHelper.getEmail(), passwordHelper.getKeyCode());
        PasswordHelper storedPasswordHelper = passwordHelperRepository.save(passwordHelper);

        return modelMapper.map(storedPasswordHelper, PasswordHelperDto.class);
    }

    @Override
    public Boolean checkKey(PasswordHelperDto passwordHelperDto) {
        PasswordHelper passwordHelper = passwordHelperRepository.findByKeyCodeAndEmail(passwordHelperDto.getKey(), passwordHelperDto.getEmail());
        if (passwordHelper == null) {
            logger.warn("Key not found");
            return false;
        }
        if (new Date().getTime() - passwordHelper.getTimeCreated().getTime() > 900000) {
            logger.warn("Key expired");
            return false;
        }

        logger.info("Key found");

        passwordHelperRepository.delete(passwordHelper);

        logger.info("Key deleted");

        return true;
    }

    @Override
    public Boolean updateUserPassword(PasswordHelperDto passwordHelperDto) {
        Etudiant etudiant = etudiantRepository.findByEmail(passwordHelperDto.getEmail());
        if (etudiant != null) {
            etudiant.setEncryptedPassword(bCryptPasswordEncoder.encode(passwordHelperDto.getKey()));
            etudiantRepository.save(etudiant);
            return true;
        }
        Enseignant enseignant = enseignantRepository.findByEmail(passwordHelperDto.getEmail());
        if (enseignant != null) {
            enseignant.setEncryptedPassword(bCryptPasswordEncoder.encode(passwordHelperDto.getKey()));
            enseignantRepository.save(enseignant);
            return true;
        }
        logger.error("Something went wrong !");
        return false;
    }

    @Override
    public String generateToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_PASSWORDHELPER))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
                .compact();

        return token;
    }

    @Override
    public Boolean checkExpiration(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.TOKEN_SECRET)
                .parseClaimsJws(jwtToken)
                .getBody();
        long expirationTime = claims.getExpiration().getTime();

        return System.currentTimeMillis() > expirationTime;
    }
}
