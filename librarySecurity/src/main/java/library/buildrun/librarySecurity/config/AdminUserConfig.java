package library.buildrun.librarySecurity.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.transaction.Transactional;
import library.buildrun.librarySecurity.entities.Roles;
import library.buildrun.librarySecurity.entities.User;
import library.buildrun.librarySecurity.repositories.RolesRepository;
import library.buildrun.librarySecurity.repositories.UserRepository;


@Configuration
public class AdminUserConfig implements CommandLineRunner {

	private RolesRepository rolesRepository;
	
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AdminUserConfig(RolesRepository rolesRepository,
						UserRepository userRepository,
						BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.rolesRepository = rolesRepository;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		var roleAdmin = rolesRepository.findByName(Roles.Values.admin.name());
		
		var userAdmin = userRepository.findByUsername("admin");
		
		userAdmin.ifPresentOrElse(
				user ->{ 
					System.out.println("Admin exists");
					},
					() -> {
					var user = new User();
					user.setUsername("admin");
					user.setPassword(bCryptPasswordEncoder.encode("123"));
					user.setRoles(Set.of(roleAdmin));
					userRepository.save(user);
				});
	}

}
