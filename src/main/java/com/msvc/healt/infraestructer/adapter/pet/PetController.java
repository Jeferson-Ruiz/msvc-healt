package com.msvc.healt.infraestructer.adapter.pet;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.models.Sex;
import com.msvc.healt.domain.port.in.PetUseCase;
import jakarta.validation.Valid;
import com.msvc.healt.domain.port.in.RegisterPetUseCase;
import com.msvc.healt.domain.port.in.RegisterPetWithOwnerUseCase;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetRequestDto;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetWithOwnerRequestDto;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetResponseDto;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.mapper.PetMapper;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.mapper.PetWithOwnerMapper;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetUseCase petUseCase;
    private final RegisterPetUseCase registerPetUseCase;
    private final RegisterPetWithOwnerUseCase registerPetWithOwnerUseCase;

    public PetController(PetUseCase petUseCase, RegisterPetUseCase registerPetUseCase,
            RegisterPetWithOwnerUseCase registerPetWithOwnerUseCase) {
        this.petUseCase = petUseCase;
        this.registerPetUseCase = registerPetUseCase;
        this.registerPetWithOwnerUseCase = registerPetWithOwnerUseCase;
    }

    @PostMapping
    public ResponseEntity<PetResponseDto> register(@RequestBody @Valid PetRequestDto petRequestDto) {
        Pet pet = registerPetUseCase.create(PetMapper.toModel(petRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(pet));
    }

    @PostMapping("/with-owner")
    public ResponseEntity<PetResponseDto> registerWithOwner(@RequestBody @Valid PetWithOwnerRequestDto petWithOwnerRequestDto) {
        Pet pet = registerPetWithOwnerUseCase.create(PetWithOwnerMapper.toModel(petWithOwnerRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(pet));
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDto>> getAll() {
        return ResponseEntity.ok(petUseCase.searchAll().stream().map(PetMapper::toDto).toList());
    }

    @GetMapping("/species")
    public ResponseEntity<List<PetResponseDto>> getAllBySpecies(@RequestParam("species") String species) {
        return ResponseEntity.ok(petUseCase.searchAllByspecies(species).stream().map(PetMapper::toDto).toList());
    }

    @GetMapping("/name")
    public ResponseEntity<List<PetResponseDto>> getAllByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(petUseCase.searchAllByName(name).stream().map(PetMapper::toDto).toList());
    }

    @GetMapping("/sex")
    public ResponseEntity<List<PetResponseDto>> getAllBySex(@RequestParam("sex") String sex) {
        return ResponseEntity.ok(petUseCase.searchAllBySex(sex).stream().map(PetMapper::toDto).toList());
    }

    @GetMapping("/age")
    public ResponseEntity<List<PetResponseDto>> getAllByAge(@RequestParam("age") int age) {
        return ResponseEntity.ok(petUseCase.searchAllByAge(age).stream().map(PetMapper::toDto).toList());
    }

    @GetMapping("/owner-document")
    public ResponseEntity<List<PetResponseDto>> getAllByOwnerDocumentNumber(@RequestParam("document") String document) {
        return ResponseEntity.ok(
                petUseCase.searchByOwnerDocumentNumber(document).stream().map(PetMapper::toDto).toList());
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable("id") Long id, @RequestParam("name") String name) {
        petUseCase.updateName(id, new NormalizedText(name));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-sex/{id}")
    public ResponseEntity<Void> updateSex(@PathVariable("id") Long id, @RequestParam("sex") String sex) {
        petUseCase.updateSex(id, Sex.valueOf(sex));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-date-born/{id}")
    public ResponseEntity<Void> updateDateBorn(@PathVariable("id") Long id, @RequestParam("dateBorn") String dateBorn) {
        petUseCase.updateDateBorn(id, LocalDate.parse(dateBorn));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-owner/{id}")
    public ResponseEntity<Void> updateOwner(@PathVariable("id") Long id, @RequestParam("ownerNumber") String number) {
        petUseCase.updateOwner(id, number);
        return ResponseEntity.noContent().build();
    }
}
