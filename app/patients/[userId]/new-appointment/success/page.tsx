import Image from "next/image";
import Link from "next/link";
import {Doctors} from "@/constants";
import {getAppointment} from "@/lib/actions/appointment.actions";
import {formatDateTime} from "@/lib/utils";
import {Button} from "@/components/ui/button";

const RequestSuccess = async ({params: {userId}, searchParams}: SearchParamProps) => {
    const appointmentId = (searchParams?.appointmentId as string) ?? "";
    const appointment = await getAppointment(appointmentId);
    const doctor = Doctors.find((doc) => doc.name === appointment.primaryPhysician);

    return (
        <div className="flex h-screen max-h-screen px-[5%]">
            <div className="success-img">
                <Link href="/">
                    <Image
                        src="/assets/icons/logo-full.svg"
                        height={1000}
                        width={1000}
                        alt="logo"
                        className="h-10 w-fit"
                    />
                </Link>

                <section className="flex flex-col items-center text-center">
                    <Image
                        src="/assets/icons/check-circle.svg"
                        height={120}
                        width={120}
                        alt="request submitted"
                    />
                    <h1 className="header mb-6 max-w-[600px]">Appointment requested</h1>
                    <p className="text-dark-700">
                        We&apos;ve sent your request and will confirm it shortly.
                    </p>
                </section>

                <section className="request-details">
                    {doctor && (
                        <div className="flex items-center gap-3">
                            <Image
                                src={doctor.image}
                                alt={doctor.name}
                                width={100}
                                height={100}
                                className="size-10 rounded-full border border-dark-500"
                            />
                            <p className="whitespace-nowrap">Dr. {doctor.name}</p>
                        </div>
                    )}
                    <div className="flex gap-2">
                        <Image
                            src="/assets/icons/calendar.svg"
                            height={24}
                            width={24}
                            alt="appointment date"
                        />
                        <p>{formatDateTime(appointment.schedule).dateTime}</p>
                    </div>
                </section>

                <Button variant="outline" className="shad-primary-btn" asChild>
                    <Link href={`/patients/${userId}/new-appointment`}>New Appointment</Link>
                </Button>

                <p className="copyright">© 2025 CareSync</p>
            </div>
        </div>
    );
};
export default RequestSuccess;
