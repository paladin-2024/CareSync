"use client"
import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import {convertFileToUrl} from "@/lib/utils";
import Image from "next/image";

type FileUploaderProps = {
    files: File[] | undefined;
    onChange: (files: File[]) => void;
}

export const FileUploader=({files, onChange}:FileUploaderProps)=> {
    const onDrop = useCallback((acceptedFiles:File[]) => {
        onChange(acceptedFiles);
    }, []);

    const {getRootProps, getInputProps} = useDropzone({onDrop});

    return (
        <div {...getRootProps()} className='file-upload'>
            <input {...getInputProps()} />
            {files && files?.length > 0 ? (
                <Image
                    src={convertFileToUrl(files[0])}
                    width={1000}
                    height={1000}
                    className="max-h-[400px] overflow-hidden object-cover"
                    alt="uploaded image"/>
            ) : (
                <>
                    <Image
                        src="/assets/icons/upload.svg"
                        width={40}
                        height={40}
                        alt="upload"
                    />
                    <div className="file_upload_label">
                        <p className="text-14-regular">
                            <span className="text-green-500">
                                Click to upload
                            </span> or drag amd drop
                        </p>
                        <p className="text-12-regular ">
                            SVG,PNG, JPG or GIF (max 800x400)
                        </p>
                    </div>
                </>
            )}
        </div>
    );
};