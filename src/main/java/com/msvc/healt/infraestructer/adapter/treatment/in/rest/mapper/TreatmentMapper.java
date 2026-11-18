package com.msvc.healt.infraestructer.adapter.treatment.in.rest.mapper;

import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.models.Treatment;
import com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto.TreatmentRequestDto;
import com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto.TreatmentResponseDto;

public class TreatmentMapper {

    public static Treatment toModel(TreatmentRequestDto dto) {
        Consultation consultation = new Consultation();
        consultation.setIdConsultation(dto.consultationId());
        return new Treatment(
                null,
                null,
                dto.dateInit(),
                dto.dateEnd(),
                dto.observations(),
                consultation);
    }

    public static TreatmentResponseDto toDto(Treatment treatment) {
        return new TreatmentResponseDto(
                treatment.getIdTreatment(),
                treatment.getRegistrationDate(),
                treatment.getDateInit(),
                treatment.getDateEnd(),
                treatment.getObservations(),
                treatment.getConsultation() == null ? null : treatment.getConsultation().getIdConsultation());
    }
}
