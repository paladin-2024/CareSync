# CareSync 🩺

<p align="center"><img src="public/assets/icons/logo-full.svg" alt="CareSync Logo" width="400"/></p>

> A modern, user-friendly healthcare management system designed to streamline patient appointment scheduling.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Next.js](https://img.shields.io/badge/Next.js-14-black?logo=next.js)](https://nextjs.org/) [![React](https://img.shields.io/badge/React-18-blue?logo=react)](https://reactjs.org/) [![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript)](https://www.typescriptlang.org/) [![Tailwind CSS](https://img.shields.io/badge/Tailwind%20CSS-3-cyan?logo=tailwind-css)](https://tailwindcss.com/)

---

## ✨ Overview

CareSync is a web application built with the latest technologies to provide a seamless experience for patients and administrators in managing healthcare appointments. The project focuses on a clean, intuitive user interface and a robust backend to handle patient data securely and efficiently.

The current focus is on building a comprehensive patient onboarding form with reusable and scalable components.

<p align="center"><img src="public/assets/images/onboarding-img.png" alt="CareSync Onboarding Screen" width="800"></p>

## 🚀 Key Features

-   **Dynamic Patient Form:** A multi-field form for patient registration including name, email, and phone number.
-   **Custom Form Fields:** A reusable `CustomFormField` component designed to handle various input types, making it easy to extend the form.
-   **International Phone Input:** Integrated `react-phone-number-input` for a user-friendly phone number field with country code selection (defaults to Uganda 🇺🇬).
-   **Schema-Based Validation:** Uses `Zod` to define a validation schema, ensuring data integrity before submission.
-   **Responsive Design:** Fully responsive layout that works on all devices, from mobile phones to desktops.
-   **Dark Mode:** Beautiful dark theme for a comfortable user experience in low-light environments, powered by `next-themes`.
-   **Secure Data Handling:** Ready for integration with a secure backend to manage patient information.
-   **Admin Dashboard:** A dedicated interface for healthcare providers to manage appointments and patient records (coming soon!).

## 🛠️ Tech Stack

-   **Framework:** Next.js 14 (with App Router)
-   **Language:** TypeScript
-   **Backend:** Appwrite (or your choice of BaaS/backend)
-   **Styling:** Tailwind CSS
-   **UI Components:** shadcn/ui
-   **Form Management:** React Hook Form
-   **Schema Validation:** Zod
-   **Phone Input:** react-phone-number-input
-   **Theming:** next-themes
-   **Fonts:** Custom Fonts (`Satoshi`, `Inter`)

## 🏗️ Form Architecture

The core of the patient onboarding experience is built upon a flexible and reusable form architecture.

-   **`PatientForm.tsx`**: This component acts as the main container for the form. It initializes `react-hook-form`, defines the `onSubmit` logic, and lays out the form fields.
-   **`CustomFormField.tsx`**: This is a generic wrapper component designed to reduce boilerplate. It takes a `fieldType` enum and dynamically renders the correct input control (e.g., text input, checkbox, date picker). It's the primary building block for creating new form fields.
-   **`lib/validation.ts`**: A centralized Zod schema provides strongly-typed, declarative validation rules for all form fields, ensuring data consistency.

## 📦 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have the following installed on your system:

-   Node.js (v18.x or later)
-   npm, yarn, or pnpm

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/paladin-2024/caresync.git
    cd caresync
    ```

2.  **Install dependencies:**
    ```bash
    npm install
    # or
    yarn install
    # or
    pnpm install
    ```

3.  **Set up environment variables:**
    Create a `.env.local` file in the root of the project and add any necessary environment variables.
    
    *Example for Appwrite:*
    ```env
    # .env.local
    NEXT_PUBLIC_ENDPOINT="https://cloud.appwrite.io/v1"
    NEXT_PUBLIC_PROJECT_ID="YOUR_PROJECT_ID"
    NEXT_PUBLIC_API_KEY="YOUR_API_KEY"
    NEXT_PUBLIC_DATABASE_ID="YOUR_DATABASE_ID"
    NEXT_PUBLIC_PATIENT_COLLECTION_ID="YOUR_PATIENT_COLLECTION_ID"
    ```

4.  **Run the development server:**
    ```bash
    npm run dev
    # or
    yarn dev
    # or
    pnpm dev
    ```

Open http://localhost:3000 with your browser to see the result.

## 📁 Project Structure

The project follows a standard Next.js App Router structure, with some conventions for organizing components and utilities.

```
caresync/
├── app/                      # Next.js App Router pages and layouts
│   ├── layout.tsx            # Root layout
│   └── page.tsx              # Home page
├── components/
│   ├── forms/                # Form-specific components (e.g., PatientForm.tsx)
│   ├── ui/                   # Reusable UI components from shadcn/ui
│   ├── CustomFormField.tsx   # Custom, reusable form field component
│   └── theme-provider.tsx    # Theme provider for dark/light mode
├── lib/
│   └── utils.ts              # Utility functions (e.g., cn for classnames)
├── public/
│   └── assets/
└── ...
```

## 🤝 Contributing

Contributions are welcome! If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

## 📄 License

Distributed under the MIT License.