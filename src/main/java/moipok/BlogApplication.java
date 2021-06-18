package moipok;

import moipok.models.Cube;
import moipok.repository.CubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}

	private CubeRepository cubeRepository;

	@Autowired
	public BlogApplication(CubeRepository cubeRepository) {
		this.cubeRepository = cubeRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Long a = cubeRepository.count();
		while (a < 256)
		{
			cubeRepository.save(new Cube("#000000"));
			a++;
		}
		while (a > 256)
		{
			cubeRepository.deleteById(a - 1);
			a--;
		}
	}
}
