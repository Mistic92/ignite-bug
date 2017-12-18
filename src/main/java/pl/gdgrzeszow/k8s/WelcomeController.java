package pl.gdgrzeszow.k8s;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    SampleRepo sampleRepo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity welcomeSay() {
        final SampleEntity sampleEntity = sampleRepo.getSampleEntity();
        return ResponseEntity.ok(OffsetDateTime.now().toString() + " " + sampleEntity.toString());
    }
}

