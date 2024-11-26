package tech.astrocode.poi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.astrocode.poi.dto.CreatePOI;
import tech.astrocode.poi.model.PointOfInterest;
import tech.astrocode.poi.repository.PoiRepository;

import java.util.List;


@RestController
public class PoiController {

    private final PoiRepository poiRepository;

    public PoiController(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @PostMapping("/points-of-interest")
    public ResponseEntity<Void> createPOI(@RequestBody CreatePOI poi){

        poiRepository.save(new PointOfInterest(poi.name(), poi.x(), poi.y()));

        return ResponseEntity.ok().build();
    }

    @GetMapping("points-of-interest")
    public ResponseEntity<Page<PointOfInterest>> listPOIs(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        var body = poiRepository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(body);
    }

    @GetMapping("near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam("x") Long x,
                                                        @RequestParam("y") Long y,
                                                        @RequestParam("dmax") Long dmax){

        var xMax = x + dmax;
        var xMin = x - dmax;
        var yMax = y + dmax;
        var yMin = y - dmax;

        var response = poiRepository.findByNearMe(xMin , xMax, yMin, yMax)
                .stream()
                .filter(p -> distanceBetweenPoints(x, y, p.getX(), p.getY())  <= dmax)
                .toList();

        return ResponseEntity.ok(response);
    }

    private double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2){
        return Math.sqrt(Math.pow(x2 -x1, 2) + Math.pow(y2 - y1, 2));
    }


}
