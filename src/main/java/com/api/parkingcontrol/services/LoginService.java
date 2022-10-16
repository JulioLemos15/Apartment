package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.LoginModel;
import com.api.parkingcontrol.repositories.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    final LoginRepository loginRepository;
    private PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<LoginModel> findAll() {
        return loginRepository.findAll();
    }

    public Object save(LoginModel loginModel) {
        String enconder = this.passwordEncoder.encode(loginModel.getPassword());
        loginModel.setPassword(enconder);
        return loginRepository.save(loginModel);
    }

    public Optional<LoginModel> findById(Long id) {
        return loginRepository.findById(id);
    }

    public Boolean validPassword(LoginModel loginModel) {
        String password = loginRepository.getById(loginModel.getId()).getPassword();
        Boolean valid = passwordEncoder.matches(loginModel.getPassword(), password);
        return valid;
    }
}
