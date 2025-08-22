# CareSync - Healthcare Management Platform

CareSync is a modern, web-based healthcare platform designed to streamline patient registration and appointment management. It provides a user-friendly interface for patients to manage their health information and schedule appointments with healthcare providers.

## ✨ Features

Based on the current implementation, CareSync includes the following core features:

### Patient Onboarding
- **Comprehensive Registration Form:** A detailed form for new patients to register their personal, medical, and insurance information.
- **User Data Pre-filling:** Automatically populates form fields with existing user data (name, email, phone) for a smoother experience.
- **Document Upload:** Securely upload identification documents as part of the verification process.
- **Consent Management:** Captures patient consent for treatment, data disclosure, and privacy policies.

### Appointment Management
- **New Appointment Requests:** Patients can easily request new appointments with their chosen primary physician.
- **Dynamic Appointment Forms:** The form adapts based on the action being performed (creating, scheduling, or canceling an appointment).
- **Detailed Appointment Information:** Capture the reason for the visit, additional notes, and the desired appointment date and time.
- **Appointment Cancellation:** A simple flow for canceling existing appointments with a required reason for documentation.

## 🛠️ Tech Stack

This project is built with a modern, robust, and scalable technology stack:

- **Framework:** [Next.js](https://nextjs.org/) (App Router)
- **Language:** [TypeScript](https://www.typescriptlang.org/)
- **UI:** [React](https://react.dev/)
- **Styling:** [Tailwind CSS](https://tailwindcss.com/)
- **UI Components:** [shadcn/ui](https://ui.shadcn.com/)
- **Form Management:** [React Hook Form](https://react-hook-form.com/)
- **Schema Validation:** [Zod](https://zod.dev/)
- **Backend/Database:** [Appwrite](https://appwrite.io/) (inferred from server actions and data models)

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Make sure you have Node.js (v18 or later) and a package manager (npm, yarn, or pnpm) installed.

### Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/your-username/caresync.git
    cd caresync
    ```

2.  Install NPM packages:
    ```bash
    npm install
    ```

3.  Set up your environment variables. Create a `.env.local` file in the root of your project and add the necessary configuration for your backend (e.g., Appwrite).
    ```env
    # .env.local.example
    
    NEXT_PUBLIC_APPWRITE_PROJECT_ID=...
    NEXT_PUBLIC_APPWRITE_API_ENDPOINT=...
    NEXT_PUBLIC_APPWRITE_DATABASE_ID=...
    NEXT_PUBLIC_APPWRITE_PATIENT_COLLECTION_ID=...
    NEXT_PUBLIC_APPWRITE_DOCTOR_COLLECTION_ID=...
    NEXT_PUBLIC_APPWRITE_APPOINTMENT_COLLECTION_ID=...
    NEXT_PUBLIC_APPWRITE_BUCKET_ID=...
    NEXT_PUBLIC_APPWRITE_API_KEY=...
    ```

4.  Run the development server:
    ```bash
    npm run dev
    ```

Open http://localhost:3000 with your browser to see the result.

## 📂 Project Structure

The project follows a standard Next.js App Router structure. Key directories include:

-   `/app`: Contains all the routes, pages, and layouts.
-   `/components`: Shared React components used across the application.
    -   `/forms`: Contains all form components like `RegisterForm.tsx` and `AppointmentForm.tsx`.
    -   `/ui`: Contains the UI components from shadcn/ui.
-   `/lib`: Contains library functions, server actions, and utilities.
    -   `/actions`: Server-side functions (Next.js Server Actions) for interacting with the backend.
    -   `/validation`: Zod schemas for form validation.
-   `/constants`: Contains static data and constants used throughout the application (e.g., doctor lists, form options).
-   `/public`: Static assets like images and icons.