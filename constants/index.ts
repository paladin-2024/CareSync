export const GenderOptions = ['male', 'female', 'other']

export const PatientFormDefaultValues = {
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    birthDate: new Date(Date.now()),
    gender: "male" as Gender,
    address: "",
    occupation: "",
    emergencyContactName: "",
    emergencyContactNumber: "",
    primaryPhysician: "",
    insuranceProvider: "",
    insurancePolicyNumber: "",
    allergies: "",
    currentMedication: "",
    familyMedicalHistory: "",
    pastMedicalHistory: "",
    identificationType: "Birth Certificate",
    identificationNumber: "",
    identificationDocument: [],
    treatmentConsent: false,
    disclosureConsent: false,
    privacyConsent: false,
};

export const IdentificationTypes = [
    "Birth Certificate",
    "Driver's License",
    "Medical Insurance Card/Policy",
    "Military ID Card",
    "National Identity Card",
    "Passport",
    "Social Security Card",
    "Student ID Card",
    "Voter ID Card",
];

export const Doctors = [
    {
        image: "/assets/images/dr-green.png",
        name: "Robert Kalyesubula",
    },
    {
        image: "/assets/images/dr-cameron.png",
        name: "Sabrina Kitaka",
    },
    {
        image: "/assets/images/dr-livingston.png",
        name: "Michael Bukenya",
    },
    {
        image: "/assets/images/dr-peter.png",
        name: "Rose Clarke Nanyonga",
    },
    {
        image: "/assets/images/dr-powell.png",
        name: "Leevan Tibaijuka",
    },
    {
        image: "/assets/images/dr-remirez.png",
        name: "Abraham Omoding",
    },
    {
        image: "/assets/images/dr-lee.png",
        name: "Joel Kiryabwire",
    },
    {
        image: "/assets/images/dr-cruz.png",
        name: "Kayima James",
    },
    {
        image: "/assets/images/dr-sharma.png",
        name: "Esther Nakandha",
    },
];

export const StatusIcon = {
    scheduled: "/assets/icons/check.svg",
    pending: "/assets/icons/pending.svg",
    cancelled: "/assets/icons/cancelled.svg",
};