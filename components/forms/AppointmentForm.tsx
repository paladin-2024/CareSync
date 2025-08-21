"use client"

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"
import {Form} from "@/components/ui/form"
import CustomFormField from "@/components/CustomFormField";
import SubmitButton from "@/components/SubmitButton";
import {useState} from "react";
import {useRouter} from "next/navigation"
import {UserFormValidation} from "@/lib/validation";
import {createUser} from "@/lib/actions/patients.actions";
import {FormFieldType} from "@/components/forms/PatientForm";



const AppointmentForm=()=> {

    const router = useRouter();

    const [isLoading, setIsLoading] = useState(false)

    const form = useForm<z.infer<typeof UserFormValidation>>({
        resolver: zodResolver(UserFormValidation),
        defaultValues: {
            name: "",
            email:"",
            phone:"",
        },
    })

    async function onSubmit({name,email,phone}: z.infer<typeof UserFormValidation>) {
        setIsLoading(true);

        try {
            const userData = {name, email, phone};
            const user = await createUser(userData);
            if (user) router.push(`/patients/${user.$id}/register`)
        } catch (error){
            console.log(error)
        }
    }
    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6 flex-1">
                <section className="mb-12 space-y-4">
                    <h1 className="header">Hi there 👋</h1>
                    <p className="text-dark-700">Request a new appointment in 15 seconds</p>
                </section>

                <CustomFormField
                    fieldType={FormFieldType.INPUT}
                    control={form.control}
                    name="doctor"
                    label="Doctor"
                    placeholder="Nzabanita"
                    iconSrc="/assets/icons/user.svg"
                    iconAlt="user"
                />

                <CustomFormField
                    fieldType={FormFieldType.INPUT}
                    control={form.control}
                    name="email"
                    label="Email"
                    placeholder="nzab@gmail.com"
                    iconSrc="/assets/icons/email.svg"
                    iconAlt="email"
                />

                <CustomFormField
                    fieldType={FormFieldType.PHONE_INPUT}
                    control={form.control}
                    name="phone"
                    label="Phone number"
                    placeholder="+256 700000000"
                />

                <SubmitButton isLoading={isLoading}>
                    Get Started
                </SubmitButton>
            </form>
        </Form>
    )
}
export default AppointmentForm
