"use client"

import { zodResolver } from "@hookform/resolvers/zod"
import { Resolver, useForm } from "react-hook-form"
import { z } from "zod"
import {Form} from "@/components/ui/form"
import CustomFormField from "@/components/CustomFormField";
import SubmitButton from "@/components/SubmitButton";
import {useState} from "react";
import {useRouter} from "next/navigation";
import {CreateAppointmentSchema} from "@/lib/validation";
import {createAppointment} from "@/lib/actions/appointment.actions";
import {FormFieldType} from "@/components/forms/PatientForm";
import {Doctors} from "@/constants";
import {SelectItem} from "@/components/ui/select";
import Image from "next/image";

const AppointmentForm = ({patientId, userId}: {patientId: string, userId: string}) => {

    const router = useRouter();
    const [isLoading, setIsLoading] = useState(false)

    const form = useForm<z.infer<typeof CreateAppointmentSchema>>({
        // shortcut: zod v4 + @hookform/resolvers v5 disagree on Resolver's generic shape; cast until a resolvers upgrade fixes it.
        resolver: zodResolver(CreateAppointmentSchema) as unknown as Resolver<z.infer<typeof CreateAppointmentSchema>>,
        defaultValues: {
            primaryPhysician: "",
            schedule: new Date(),
            reason: "",
            note: "",
        },
    })

    async function onSubmit(values: z.infer<typeof CreateAppointmentSchema>) {
        setIsLoading(true);
        try {
            const appointment = await createAppointment({
                userId,
                patient: patientId,
                primaryPhysician: values.primaryPhysician,
                reason: values.reason,
                schedule: values.schedule,
                status: "pending",
                note: values.note,
            });
            router.push(`/patients/${patientId}/new-appointment/success?appointmentId=${appointment.$id}`);
        } catch (error) {
            console.log(error)
            setIsLoading(false);
        }
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6 flex-1">
                <section className="mb-12 space-y-4">
                    <h1 className="header">New Appointment 👋</h1>
                    <p className="text-dark-700">Request a new appointment.</p>
                </section>

                <CustomFormField
                    fieldType={FormFieldType.SELECT}
                    control={form.control}
                    name="primaryPhysician"
                    label="Primary Physician"
                    placeholder="Select a Physician">
                    {Doctors.map((doctor)=>(
                        <SelectItem key={doctor.name} value={doctor.name}>
                            <div className="flex cursor-pointer items-center gap-2">
                                <Image
                                    src={doctor.image}
                                    width={32}
                                    height={32}
                                    alt={doctor.name}
                                    className="rounded-full border border-dark-500"
                                />
                                <p>{doctor.name}</p>
                            </div>
                        </SelectItem>
                    ))}
                </CustomFormField>

                <CustomFormField
                    fieldType={FormFieldType.DATE_PICKER}
                    control={form.control}
                    name="schedule"
                    label="Expected appointment date"
                    showTimeSelect
                    dateFormat="MM/dd/yyyy - h:mm aa"
                    placeholder="Select a date and time"
                />

                <CustomFormField
                    fieldType={FormFieldType.TEXTAREA}
                    control={form.control}
                    name="reason"
                    label="Reason for appointment"
                    placeholder="Annual checkup, follow-up, etc."
                />

                <CustomFormField
                    fieldType={FormFieldType.TEXTAREA}
                    control={form.control}
                    name="note"
                    label="Additional notes (optional)"
                    placeholder="Anything the physician should know beforehand"
                />

                <SubmitButton isLoading={isLoading}>
                    Request Appointment
                </SubmitButton>
            </form>
        </Form>
    )
}
export default AppointmentForm
