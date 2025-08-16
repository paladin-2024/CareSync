import * as sdk from 'node-appwrite'

export const{
    PROJECT_ID,API_KEY,DATABASE_ID,PATIENT_COLLECTION_ID,DOCTOR_COLLECTION_ID,
    APPOINTMENT_COLLECTION_ID,NEXT_PUBLIC_BUCKET_ID:BUCKET_ID,
    NEXT_PUBLIC_ENDPOINT:ENDPOINT
}=process.env;

const client = new sdk.Client();

client
    .setEndpoint('https://syd.cloud.appwrite.io/v1')
    .setProject('689e47780028d12b1d1c')
    .setKey('standard_315dff9346e44f57faca6ceb678cebae7b2cbc8012e21819bbbc3fa868415d0d378fd3e49815b79ae6bacc29b28e97ae26cacfe377a5e9dc9de73a18e0672b90c42bc623641d08234254168b156aced764c2b778979681dcf2bd91206e7f8f81a7315272e504a57e0a6758fefd3bb2d1eb1363a60329da382744e7bc73717fa3')

export const databases = new sdk.Databases(client);
export const storage = new sdk.Storage(client);
export const users = new sdk.Users(client);
export const messaging = new sdk.Messaging(client);