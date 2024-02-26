package dev.martsin.evtest.controllers;

import dev.martsin.evtest.dto.ChargingStationRequest;
import dev.martsin.evtest.dto.ChargingStationResponse;
import dev.martsin.evtest.services.ChargingStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/charging-stations")
@RequiredArgsConstructor
public class ChargingStationController {
    private final ChargingStationService chargingStationService;
    @GetMapping
    public ResponseEntity<List<ChargingStationResponse>> findAll(){
        return ResponseEntity.ok(chargingStationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStationResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(chargingStationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ChargingStationResponse> save(@RequestBody @Validated ChargingStationRequest request){
        return ResponseEntity.ok(chargingStationService.save(request));
    }

}
