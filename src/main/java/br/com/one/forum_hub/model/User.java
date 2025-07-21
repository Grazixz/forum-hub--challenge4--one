package br.com.one.forum_hub.model;

import br.com.one.forum_hub.DTO.DataUserLogin;
import br.com.one.forum_hub.DTO.DataUserUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "userT")
    private List<Topic> topics;
    @OneToMany(mappedBy = "userR")
    private List<ResponseT> responses;
    private boolean active;

    public User(DataUserLogin data, String encoder) {
        this.email = data.email();
        this.password = encoder;
        this.nickname = data.nickname();
        this.creationDate = LocalDateTime.now();
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void update(DataUserUpdate data) {
        if (data.nickname() != null) this.nickname = data.nickname();
    }

    public void disable() {
        this.active = !this.active;
    }
}
