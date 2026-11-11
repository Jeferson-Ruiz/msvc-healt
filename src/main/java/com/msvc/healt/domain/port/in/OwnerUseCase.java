package com.msvc.healt.domain.port.in;

import java.util.List;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Owner;

public interface OwnerUseCase {

    Owner create(Owner owner);

    List<Owner> searchAll();

    Owner searchByDocumentNumber(String DocumentNumber);

    List<Owner> searchByName(String number);

    void updateName(Long id, NormalizedText name);

    void updateLastName(Long id, NormalizedText lastName);

    void updateEmail(Long id, Email email);

    void updatePhone(Long id, NormalizedNumber number);

    void updateStatus(Long id, boolean status);
}
