'use server'
import {
    APPOINTMENT_COLLECTION_ID,
    DATABASE_ID,
    databases,
} from "@/lib/appwrite.config";
import {ID} from "node-appwrite";
import {parseStringify} from "@/lib/utils";

export const createAppointment = async (appointment: {
    userId: string;
    patient: string;
    primaryPhysician: string;
    schedule: Date;
    reason: string;
    note: string | undefined;
    status: "pending" | "scheduled" | "cancelled"
}) => {
    try {
        const newAppointment= await databases.createDocument(
            DATABASE_ID!,
            APPOINTMENT_COLLECTION_ID!,
            ID.unique(),
            appointment
        )
        return parseStringify(newAppointment);
    } catch (error) {
        console.log(error);
    }
};