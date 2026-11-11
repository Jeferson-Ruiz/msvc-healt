package com.msvc.healt.infraestructer.adapter.owner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.port.in.OwnerUseCase;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerRequestDto;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.mapper.OwnerMapper;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerUseCase ownerUseCase;

    public OwnerController(OwnerUseCase ownerUseCase) {
        this.ownerUseCase = ownerUseCase;
    }

    @PostMapping
    public ResponseEntity<?> save(OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerUseCase.create(OwnerMapper.toModel(ownerRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(OwnerMapper.toDto(owner));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ownerUseCase.searchAll().stream().map(OwnerMapper::toDto));
    }

    @GetMapping("/document")
    public ResponseEntity<?> getByDocumentNumber(@RequestParam("document") String document) {
        return ResponseEntity.ok(ownerUseCase.searchByDocumentNumber(document));
    }

    @GetMapping("/name")
    public ResponseEntity<?> getAllByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(ownerUseCase.searchByName(name));
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<?> updateName(Long id, NormalizedText name) {
        ownerUseCase.updateName(id, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-lastname/{id}")
    public ResponseEntity<?> updateLastName(Long id, NormalizedText lastName) {
        ownerUseCase.updateLastName(id, lastName);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-email/{id}")
    public ResponseEntity<?> updateEmail(Long id, Email email) {
        ownerUseCase.updateEmail(id, email);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-phone/{id}")
    public ResponseEntity<?> updatePhone(Long id, NormalizedNumber phone) {
        ownerUseCase.updatePhone(id, phone);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<?> updatePhone(Long id, boolean status) {
        ownerUseCase.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
