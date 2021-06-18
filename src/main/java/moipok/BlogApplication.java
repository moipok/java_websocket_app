package moipok;

import moipok.controller.MainController;
import moipok.models.Cube;
import moipok.repository.CubeRepository;
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
		Long a = cubeRepository.count();
//		a = 0l;
		while (a < 255l)
		{
			cubeRepository.save(new Cube(a, "#000000"));
			a = a + 1l;
		}

		List<Cube> list = cubeRepository.findByOrderByIdDesc();
		Long i = 0l;
		while (true)
		{
			if (i > 255l)
				i = 0l;
			Random rnd = new Random();
			Integer number = rnd.nextInt(255);
			String collor = "#" + Integer.toHexString(rnd.nextInt(255))+ Integer.toHexString(rnd.nextInt(255))+ Integer.toHexString(rnd.nextInt(255));
			Cube cube = list.get(i.intValue());
			cube.setColor(collor);
			cubeRepository.save(cube);
			Thread.sleep(10000);
			i++;
		}
	}
}
