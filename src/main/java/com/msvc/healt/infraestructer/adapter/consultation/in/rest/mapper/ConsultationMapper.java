package com.msvc.healt.infraestructer.adapter.consultation.in.rest.mapper;

import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto.ConsultationRequestDto;
import com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto.ConsultationResponseDto;

public class ConsultationMapper {

    public static Consultation toModel(ConsultationRequestDto dto) {
        Consultation consultation = new Consultation();
        consultation.setIdConsultation(null);
        consultation.setRegistratioDate(null);
        consultation.setDoctorId(dto.doctorId());
        consultation.setPersonCharge(new NormalizedText(dto.personCharge()));
        consultation.setDocumentNumber(new NormalizedNumber(dto.documentNumber()));
        consultation.setPetName(new NormalizedText(dto.petName()));
        consultation.setAge(dto.age());
        consultation.setSex(dto.sex());
        consultation.setReasonInquiry(dto.reasonInquiry());
        consultation.setDiagnosis(dto.diagnosis());
        consultation.setObservations(dto.observations());
        return consultation;
    }

    public static ConsultationResponseDto toDto(Consultation consultation) {
        return new ConsultationResponseDto(
                consultation.getIdConsultation(),
                consultation.getRegistratioDate(),
                consultation.getDoctorId(),
                value(consultation.getPersonCharge()),
                number(consultation.getDocumentNumber()),
                value(consultation.getPetName()),
                consultation.getAge(),
                consultation.getSex(),
                consultation.getReasonInquiry(),
                consultation.getDiagnosis(),
                consultation.getObservations(),
                consultation.getPet() == null ? null : consultation.getPet().getIdPet(),
                consultation.getOwner() == null ? null : consultation.getOwner().getId());
    }

    private static String value(NormalizedText text) {
        return text == null ? null : text.value();
    }

    private static String number(NormalizedNumber number) {
        return number == null ? null : number.value();
    }
}
