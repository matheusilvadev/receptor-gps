package tech.astrocode.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.astrocode.poi.model.PointOfInterest;
import tech.astrocode.poi.repository.PoiRepository;

@SpringBootApplication
public class PoiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

	@Autowired
	private PoiRepository poiRepository;

	@Override
	public void run(String... args) throws Exception {
		poiRepository.save(new PointOfInterest("Lanchonete", 27L, 12L ));
		poiRepository.save(new PointOfInterest("Posto", 15L, 18L ));
		poiRepository.save(new PointOfInterest("Joalheiria", 15L, 12L ));
		poiRepository.save(new PointOfInterest("Floricultura", 19L, 21L ));
		poiRepository.save(new PointOfInterest("PUB", 12L, 8L ));
		poiRepository.save(new PointOfInterest("Supermercado", 23L, 6L ));
		poiRepository.save(new PointOfInterest("Churrascari", 28L, 2L ));

	}
}
