package br.com.ecogestor.shared.config;

import br.com.ecogestor.login.entity.Role;
import br.com.ecogestor.login.entity.Usuario;
import br.com.ecogestor.login.repository.RoleRepository;
import br.com.ecogestor.login.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AdminUserConfig(RoleRepository roleRepository,
                           UsuarioRepository usuarioRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName(Role.Values.ADMIN.name());
            roleAdmin = roleRepository.save(roleAdmin);
        }

        var roleBasic = roleRepository.findByName(Role.Values.BASIC.name());

        if (roleBasic == null) {
            roleBasic = new Role();
            roleBasic.setName(Role.Values.BASIC.name());
            roleRepository.save(roleBasic);
        }

        var usuarioAdmin = usuarioRepository.procurarUsuarioPorEmail("alvesluanpc@gmail.com");

        var finalRoleAdmin = roleAdmin;
        usuarioAdmin.ifPresentOrElse(
                Usuario -> {
                    System.out.println("admin já existe");
                },
                () -> {
                    var usuario= new Usuario();
                    usuario.setEmail("alvesluanpc@gmail.com");
                    usuario.setSenha(bCryptPasswordEncoder.encode("123456"));
                    usuario.setRole(Set.of(finalRoleAdmin));
                    usuarioRepository.save(usuario);
                }
        );

    }


}
