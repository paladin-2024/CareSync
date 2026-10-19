"use server"
import {backendFetch, backendUpload} from "@/lib/backend-api";
import {parseStringify} from "@/lib/utils";

// Backend responses use `id`; existing forms expect Appwrite-style `$id`.
const withAppwriteId = <T extends { id: string }>(doc: T) => ({...doc, $id: doc.id});

export const createUser = async (user: CreateUserParams) => {
    const newUser = await backendFetch<{ id: string; name: string; email: string; phone: string }>(
        "/api/users",
        {method: "POST", body: JSON.stringify(user)}
    );
    return parseStringify(withAppwriteId(newUser));
};

export const getUser = async (userId: string) => {
    const user = await backendFetch<{ id: string; name: string; email: string; phone: string }>(
        `/api/users/${userId}`
    );
    return parseStringify(withAppwriteId(user));
};

type RegisterPatientInput = RegisterUserParams & { treatmentConsent?: boolean; disclosureConsent?: boolean };

export const registerPatient = async ({identificationDocument, ...patient}: RegisterPatientInput) => {
    const payload = {
        userId: patient.userId,
        name: patient.name,
        email: patient.email,
        phone: patient.phone,
        birthDate: new Date(patient.birthDate).toISOString().slice(0, 10),
        gender: patient.gender.toUpperCase(),
        address: patient.address,
        occupation: patient.occupation,
        emergencyContactName: patient.emergencyContactName,
        emergencyContactNumber: patient.emergencyContactNumber,
        primaryPhysician: patient.primaryPhysician,
        insuranceProvider: patient.insuranceProvider,
        insurancePolicyNumber: patient.insurancePolicyNumber,
        allergies: patient.allergies,
        currentMedication: patient.currentMedication,
        familyMedicalHistory: patient.familyMedicalHistory,
        pastMedicalHistory: patient.pastMedicalHistory,
        identificationType: patient.identificationType,
        identificationNumber: patient.identificationNumber,
        identificationDocumentUrl: null,
        treatmentConsent: patient.treatmentConsent ?? false,
        disclosureConsent: patient.disclosureConsent ?? false,
        privacyConsent: patient.privacyConsent,
    };

    const newPatient = await backendFetch<{ id: string }>(
        "/api/patients",
        {method: "POST", body: JSON.stringify(payload)}
    );

    const file = identificationDocument?.get("identificationDocument") as Blob | null;
    if (file) {
        const uploadForm = new FormData();
        uploadForm.append("file", file);
        const withDocument = await backendUpload<{ id: string }>(
            `/api/patients/${newPatient.id}/document`,
            uploadForm
        );
        return parseStringify(withAppwriteId(withDocument));
    }

    return parseStringify(withAppwriteId(newPatient));
};

export const getPatient = async (patientId: string) => {
    const patient = await backendFetch<{ id: string; userId: string }>(`/api/patients/${patientId}`);
    return parseStringify(withAppwriteId(patient));
};
