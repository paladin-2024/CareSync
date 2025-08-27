"use server"
import {ID, Query} from "node-appwrite";
import {
    BUCKET_ID,
    DATABASE_ID,
    databases,
    ENDPOINT,
    PATIENT_COLLECTION_ID, PROJECT_ID,
    storage,
    users
} from "@/lib/appwrite.config";
import {parseStringify} from "@/lib/utils";
import {InputFile} from "node-appwrite/file";

export const createUser =async (user:CreateUserParams)=>{
    try{
        const newUser = await users.create(
            ID.unique(),
            user.email,
            user.phone,
            undefined,
            user.name
        )
        console.log({newUser})
        return parseStringify(newUser);
    }catch (error: any){
        if(error && error?.code === 409){
            const documents =await users.list([
                Query.equal('email', user.email),
            ])

            return documents?.users?.[0];
        }
        throw error;
    }
};

export const getUser = async (userId:string)=>{
    try{
        const user = await users.get(userId);
        return parseStringify(user);
    }catch (error){
        console.log(error)
        throw error;
    }
};

export const getPatient = async (userId:string)=>{
    try{
        const patients = await databases.listDocuments(
            DATABASE_ID!,
            PATIENT_COLLECTION_ID!,
            [Query.equal('userId', userId)]
        );
        return parseStringify(patients.documents[0]);
    }catch (error){
        console.log(error)
        throw error;
    }
}

export const registerPatient = async ({identificationDocument, ...patient}: RegisterUserParams)=>{
    try{
        let file;

        if(identificationDocument){
            const blob = identificationDocument.get('blobFile') as Blob | null;
            const fileName = identificationDocument.get('fileName') as string | null;

            if (blob && fileName) {
                const arrayBuffer = await blob.arrayBuffer();
                const inputFile = InputFile.fromBuffer(
                    Buffer.from(arrayBuffer),
                    fileName,
                );
                file = await storage.createFile(BUCKET_ID!, ID.unique(), inputFile);
            }
        }

        const newPatient= await databases.createDocument(
            DATABASE_ID!,
            PATIENT_COLLECTION_ID!,
            ID.unique(),
            {
                identificationDocumentId: file?.$id || null,
                identificationDocumentUrl: file
                    ? `${ENDPOINT}/storage/buckets/${BUCKET_ID}/files/${file.$id}/view?project=${PROJECT_ID}`
                    : null,
                ...patient
            }
        )

        return parseStringify(newPatient);
    } catch (error){
        console.log(error);
        throw error;
    }
};