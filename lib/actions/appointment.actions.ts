"use server"
import {backendFetch} from "@/lib/backend-api";
import {parseStringify} from "@/lib/utils";

const withAppwriteId = <T extends { id: string }>(doc: T) => ({...doc, $id: doc.id});

export const createAppointment = async (appointment: CreateAppointmentParams) => {
    const payload = {
        patientId: appointment.patient,
        userId: appointment.userId,
        primaryPhysician: appointment.primaryPhysician,
        reason: appointment.reason,
        schedule: new Date(appointment.schedule).toISOString(),
        status: appointment.status.toUpperCase(),
        note: appointment.note,
    };

    const newAppointment = await backendFetch<{ id: string }>(
        "/api/appointments",
        {method: "POST", body: JSON.stringify(payload)}
    );
    return parseStringify(withAppwriteId(newAppointment));
};

export const getAppointment = async (appointmentId: string) => {
    const appointment = await backendFetch<{ id: string }>(`/api/appointments/${appointmentId}`);
    return parseStringify(withAppwriteId(appointment));
};
