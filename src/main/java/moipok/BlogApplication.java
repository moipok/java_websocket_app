package moipok;

import moipok.controller.MainController;
import moipok.models.Cube;
import moipok.repository.CubeRepository;
import moipok.save.SaveImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

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
//		cubeRepository.deleteAll();
		int a = cubeRepository.findAll().size();
//		a = 0l;
		while (a < 256l)
		{
			cubeRepository.save(new Cube((long)(a), "#000000"));
			a = a + 1;
		}
	}
}
