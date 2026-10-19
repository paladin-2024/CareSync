create table users (
    id uuid primary key default gen_random_uuid(),
    name varchar(50) not null,
    email varchar(255) not null unique,
    phone varchar(20) not null,
    created_at timestamp not null default now()
);

create table patients (
    id uuid primary key default gen_random_uuid(),
    user_id uuid not null references users(id),
    name varchar(50) not null,
    email varchar(255) not null,
    phone varchar(20) not null,
    birth_date date not null,
    gender varchar(10) not null,
    address varchar(500) not null,
    occupation varchar(500) not null,
    emergency_contact_name varchar(50) not null,
    emergency_contact_number varchar(20) not null,
    primary_physician varchar(100) not null,
    insurance_provider varchar(50) not null,
    insurance_policy_number varchar(50) not null,
    allergies varchar(1000),
    current_medication varchar(1000),
    family_medical_history varchar(1000),
    past_medical_history varchar(1000),
    identification_type varchar(100),
    identification_number varchar(100),
    identification_document_url varchar(500),
    privacy_consent boolean not null default false,
    treatment_consent boolean not null default false,
    disclosure_consent boolean not null default false,
    created_at timestamp not null default now()
);

create table appointments (
    id uuid primary key default gen_random_uuid(),
    patient_id uuid not null references patients(id),
    user_id uuid not null references users(id),
    primary_physician varchar(100) not null,
    reason varchar(500) not null,
    schedule timestamp not null,
    status varchar(10) not null,
    note varchar(500),
    cancellation_reason varchar(500),
    created_at timestamp not null default now()
);

create index idx_patients_user_id on patients(user_id);
create index idx_appointments_patient_id on appointments(patient_id);
create index idx_appointments_user_id on appointments(user_id);
