'use client'
import {FormControl, FormDescription, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Input} from "@/components/ui/input";
import {Control} from "react-hook-form";
import {FormFieldType} from "@/components/forms/PatientForm";
import Image from "next/image";
import PhoneInput from "react-phone-number-input"
import "react-phone-number-input/style.css"
import {E164Number} from "libphonenumber-js/core"
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


interface CustomProps{
    control:Control<any>,
    fieldType:FormFieldType,
    name:string,
    label?:string,
    placeholder?:string,
    iconSrc?:string,
    iconAlt?:string,
    disabled?:string,
    dateFormat?:string,
    showTimeSelect?:boolean,
    children?:React.ReactNode,
    renderSkeleton?:(field: any)=> React.ReactNode,
}

const RenderField = ({field, props}:{field:any,props:CustomProps}) =>{
    const {fieldType,iconSrc,iconAlt,placeholder,showTimeSelect, dateFormat,renderSkeleton} = props;
    switch(fieldType){
        case FormFieldType.INPUT:
            return (
                <div className="flex rounded-md border border-dark-500 bg-dark-400">
                    {iconSrc && (
                       <Image
                           src={iconSrc}
                           height={24}
                           width={24}
                           alt={iconAlt||'icon'}
                           className='ml-2'
                       />
                    )}
                    <FormControl>
                        <Input
                            {...field}
                            placeholder={placeholder}
                            className="sha-input border-0"
                        />

                    </FormControl>
                </div>
            )
        case FormFieldType.PHONE_INPUT:
            return (
                <FormControl>
                    <PhoneInput
                        defaultCountry="UG"
                        placeholder={placeholder}
                        international
                        withCountryCallingCode
                       value={field.value as E164Number | undefined}
                        onChange={field.onChange}
                        className="input-phone"
                    />
                </FormControl>
            )

        case FormFieldType.TEXTAREA:
        case FormFieldType.CHECKBOX:
        case FormFieldType.DATE_PICKER:
            return(
                <div className="flex rounded-md border border-dark-500 bg-dark-400">
                    <Image
                        src="/assets/icons/calendar.svg"
                        height={24}
                        width={24}
                        alt="calendar"
                        className='ml-2'
                    />
                    <FormControl>
                        <DatePicker
                            selected={field.value}
                            onChange={(date) => field.onChange(date)}
                            showTimeSelect={showTimeSelect ?? false}
                            dateFormat={dateFormat ?? "MM/dd/yyyy"}
                            timeInputLabel="Time:"
                            wrapperClassName="date-picker"
                        />
                    </FormControl>
                </div>
            )
        case FormFieldType.SELECT:
            break;
        case FormFieldType.SKELETON:
            return (
                renderSkeleton ? renderSkeleton(field) : null
            )

    }
}

const CustomFormField = (props: CustomProps) => {
    const {control,fieldType,name,label }=props;
    return (
        <FormField
            control={control}
            name={name}
            render={({ field }) => (
                <FormItem className="flex-1">
                    {fieldType !== FormFieldType.CHECKBOX && label &&(
                        <FormLabel>{label}</FormLabel>
                    )}

                    <RenderField field={field} props={props}/>

                    <FormMessage className="shad-error"/>

                </FormItem>
            )}
        />



    );
}
export default CustomFormField
