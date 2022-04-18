package com.Bank.Branch.Services;

import com.Bank.Branch.Dtos.CreateBankUserReqDto;
import com.Bank.Branch.Models.BankUser;
import com.Bank.Branch.Repositories.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BankUserService implements UserDetailsService {
    private final BankUserRepository bankUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankUser bankUser = bankUserRepository.findByUsername(username);
        if(bankUser == null){
            System.out.println("User not found in database");
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(bankUser.getUsername(), bankUser.getPin(), authorities);
    }

    public BankUser create(BankUser request){
        request.setPin(passwordEncoder.encode(request.getPin()));
        return bankUserRepository.save(request);
    }


    public BankUser get(Long id){
        return bankUserRepository.getById(id);
    }


    public List<BankUser> getAll(){
        return bankUserRepository.findAll();
    }

    public BankUser update(BankUser bankUser){
        Optional<BankUser> result = bankUserRepository.findById(bankUser.getId());
        if(result.isEmpty()){
            return null;
        }
        return bankUserRepository.save(bankUser);
    }

    public BankUser delete(Long id){
        Optional<BankUser> result = bankUserRepository.findById(id);
        if(result.isEmpty()){
            return null;
        }
        bankUserRepository.deleteById(id) ;
        return result.get();
    }

}
