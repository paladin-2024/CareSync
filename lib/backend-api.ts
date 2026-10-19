const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";
const BACKEND_API_KEY = process.env.BACKEND_API_KEY;

export async function backendFetch<T>(path: string, options: RequestInit = {}): Promise<T> {
    const res = await fetch(`${BACKEND_URL}${path}`, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            ...(BACKEND_API_KEY ? { "X-API-Key": BACKEND_API_KEY } : {}),
            ...options.headers,
        },
        cache: "no-store",
    });

    if (!res.ok) {
        const body = await res.text();
        throw new Error(`Backend request failed (${res.status}): ${body}`);
    }

    if (res.status === 204) return undefined as T;
    return res.json() as Promise<T>;
}

// Separate from backendFetch because FormData needs fetch to set its own
// multipart Content-Type (with boundary); setting it manually breaks the upload.
export async function backendUpload<T>(path: string, formData: FormData): Promise<T> {
    const res = await fetch(`${process.env.BACKEND_API_URL ?? "http://localhost:8080"}${path}`, {
        method: "POST",
        body: formData,
        headers: BACKEND_API_KEY ? {"X-API-Key": BACKEND_API_KEY} : {},
        cache: "no-store",
    });

    if (!res.ok) {
        const body = await res.text();
        throw new Error(`Backend upload failed (${res.status}): ${body}`);
    }
    return res.json() as Promise<T>;
}
