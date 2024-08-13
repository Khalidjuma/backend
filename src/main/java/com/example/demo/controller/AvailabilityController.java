package  com.example.demo.controller;
import com.example.demo.Services.AvailabilityService;
import com.example.demo.dto.AvailabilityRequest;
import com.example.demo.dto.AvailabilityResponse;
import com.example.demo.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<AvailabilityResponse>> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailabilities();
        List<AvailabilityResponse> response = availabilities.stream()
                .map(AvailabilityResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> getAvailabilityById(@PathVariable Long id) {
        Availability availability = availabilityService.getAvailabilityById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        AvailabilityResponse response = new AvailabilityResponse(availability);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AvailabilityResponse> createAvailability(@RequestBody AvailabilityRequest availabilityRequest) {
        Availability savedAvailability = availabilityService.saveAvailability(availabilityRequest);
        AvailabilityResponse response = new AvailabilityResponse(savedAvailability);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> updateAvailability(@PathVariable Long id, @RequestBody AvailabilityRequest updatedAvailability) {
        Availability updated = availabilityService.updateAvailability(id, updatedAvailability);
        AvailabilityResponse response = new AvailabilityResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.ok().build();
    }
}
