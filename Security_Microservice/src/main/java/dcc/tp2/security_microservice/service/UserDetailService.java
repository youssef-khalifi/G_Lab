package dcc.tp2.security_microservice.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailService implements UserDetailsService {
    private RestTemplate restTemplate;
    @Value("${gateway.service.url}")
    private String gatewayServiceUrl;

    public UserDetailService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public UserDetails loadUserByUsername(String combinedUsername) throws UsernameNotFoundException {

        String[] parts = combinedUsername.split(":");
        if (parts.length != 2) {
            throw new UsernameNotFoundException("Invalid username format. Expected format: email:type");
        }

        String email = parts[0];
        String userType = parts[1];
        System.out.println("DANS USER DETAIL ");
        Map<String, String> user_infos = new HashMap<>();

        if (userType.equals("Enseignant")){
            String url = UriComponentsBuilder.fromHttpUrl(gatewayServiceUrl)
                    .path("/ENSEIGNANT-SERVICE/Enseignants/email/")
                    .path(email)
                    .toUriString();
            user_infos = restTemplate.getForObject(url, HashMap.class);
        }

        if (userType.equals("Chercheur")){

            String url = UriComponentsBuilder.fromHttpUrl(gatewayServiceUrl)
                    .path("/CHERCHEUR-SERVICE/Chercheurs/email/")
                    .path(email)
                    .toUriString();

            user_infos = restTemplate.getForObject(url, HashMap.class);
        }


        if (user_infos.isEmpty()){
            new UsernameNotFoundException("User not found with username: " + email);
            System.out.println(" AUcun user");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user_infos.get("scope")));

        return new User(user_infos.get("email"), "{noop}"+user_infos.get("password"), authorities);
    }
}
