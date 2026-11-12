package com.msvc.healt.infraestructer.adapter.owner;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.port.in.OwnerUseCase;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerResponseDto;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.mapper.OwnerMapper;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerUseCase ownerUseCase;

    public OwnerController(OwnerUseCase ownerUseCase) {
        this.ownerUseCase = ownerUseCase;
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> getAll() {
        return ResponseEntity.ok(ownerUseCase.searchAll().stream().map(OwnerMapper::toDto).toList());
    }

    @GetMapping("/document")
    public ResponseEntity<OwnerResponseDto> getByDocumentNumber(@RequestParam("document") String document) {
        return ResponseEntity.ok(OwnerMapper.toDto(ownerUseCase.searchByDocumentNumber(document)));
    }

    @GetMapping("/name")
    public ResponseEntity<List<OwnerResponseDto>> getAllByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(ownerUseCase.searchByName(name).stream().map(OwnerMapper::toDto).toList());
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable("id") Long id, @RequestParam("name") String name) {
        ownerUseCase.updateName(id, new NormalizedText(name));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-lastname/{id}")
    public ResponseEntity<Void> updateLastName(@PathVariable("id") Long id, @RequestParam("lastName") String lastName) {
        ownerUseCase.updateLastName(id, new NormalizedText(lastName));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-email/{id}")
    public ResponseEntity<Void> updateEmail(@PathVariable("id") Long id, @RequestParam("email") String email) {
        ownerUseCase.updateEmail(id, new Email(email));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-phone/{id}")
    public ResponseEntity<Void> updatePhone(@PathVariable("id") Long id, @RequestParam("phone") String phone) {
        ownerUseCase.updatePhone(id, new NormalizedNumber(phone));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id, @RequestParam("status") boolean status) {
        ownerUseCase.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
